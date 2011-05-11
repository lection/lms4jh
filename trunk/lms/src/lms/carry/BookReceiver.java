package lms.carry;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.Collection;
import java.util.List;

import lms.dao.IBookDao;
import lms.model.Book;
import lms.model.Type;

public class BookReceiver {
	
	private IBookDao bookDao;
	private String bookDir;
	private String swfDir;
	private int port;
	
	private ServerSocket server;
	
	public void startup(){
		try {
			server = new ServerSocket(port);
			while(true){
				new ReceiverThread(server.accept()).start();
			}
		} catch (SocketException se) {
			se.printStackTrace();
		} catch (IOException ie) {
			ie.printStackTrace();
		}
	}
	
	public void shutdown(){
		try {
			server.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private class ReceiverThread extends Thread{
		
		private Socket socket;
		
		public ReceiverThread(Socket socket){
			this.socket = socket;
		}
		
		public void run(){
			ObjectInputStream ois = null;
			OutputStream os = null;
			try {
				System.out.println("开始接受");
				ois = new ObjectInputStream(socket.getInputStream());
				os = socket.getOutputStream();
				Book book = (Book)ois.readObject();
				Long size = (Long)ois.readObject();
				System.out.println("文件大小:"+size);
				if(size != -1){
					copyFile(size,new File(bookDir,book.getFileName()),ois);
				}
				os.write(1);
				os.flush();
				if(book.getSwf() != null){
					File swfDirFile = new File(swfDir,book.getSwf());
					if(!swfDirFile.exists()){
						swfDirFile.mkdir();
					}
					for(int i=1;i<=book.getSegments();i++){
						size = (Long)ois.readObject();
						copyFile(size,new File(swfDirFile,i+".swf"),ois);
						os.write(1);
						os.flush();
					}
				}
				bookDao.save(book);
				
			} catch (Exception e) {
				e.printStackTrace();
			}finally{
				try {
					ois.close();
				} catch (IOException e) {}
				try {
					os.close();
				} catch (IOException e) {}
				try {
					socket.close();
				} catch (IOException e) {}
			}
		}
		
		private void copyFile(Long size,File file,InputStream is) throws IOException{
			if(size == -1)return;
			byte[] buffer = new byte[8192];
			int i = -1;
			int count = 0;
			FileOutputStream fos = new FileOutputStream(file);
			while((i=is.read(buffer,0,(int)((count+8192)>size?(size-count):8192))) != -1){
				fos.write(buffer,0,i);
				count += i;
				if(count == size){
//					System.out.println(count);
					break;
				}
			}
			fos.close();
		}
		
//		private String readLine(InputStream is) throws IOException{
//			StringBuilder sb = new StringBuilder();
//			int flag = 0;
//			int data = -1;
//			while((data=is.read())!=-1){
//				char word = (char)data;
//				if(word != '\n'){
//					if(flag > 0){
//						for(int i=0;i<flag;i++){
//							sb.append('\n');
//						}
//					}
//					sb.append(word);
//				}else{
//					flag++;
//					if(flag==3){
//						break;
//					}
//				}
//			}
//			return sb.toString();
//		}
	}
	
	
	public IBookDao getBookDao() {
		return bookDao;
	}

	public void setBookDao(IBookDao bookDao) {
		this.bookDao = bookDao;
	}

	public String getBookDir() {
		return bookDir;
	}

	public void setBookDir(String bookDir) {
		this.bookDir = bookDir;
	}

	public String getSwfDir() {
		return swfDir;
	}

	public void setSwfDir(String swfDir) {
		this.swfDir = swfDir;
	}
	
	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public static void main(String[] args){
		BookReceiver receiver = new BookReceiver();
		receiver.setPort(9527);
		receiver.setBookDao(new IBookDao(){
			public void delete(Book book) {}
			public void deleteById(int id) {}
			public void deleteByIdCol(Collection<Integer> ids) {}
			public Book getBookById(int id) {return null;}
			public int getTotal() {return 0;}
			public int getTotal(Type type) {return 0;}
			public int getTotal(String name, String author, String code,String bookConcern, String desc, Integer typeId) {return 0;}
			public List<Book> listBook(int start, int size) {return null;}
			public List<Book> listBook(int start, int size, Type type) {return null;}
			public List<Book> listBook(String name, String author, String code,	String bookConcern, String desc, Integer typeId, int start,int size) {return null;}
			public void save(Book book) {
				System.out.println(book.getName());
			}
			public void save(Collection<Book> books) {}
			public void update(Book book) {}});
		receiver.setBookDir("E:\\test\\bookreceiver\\book");
		receiver.setSwfDir("E:\\test\\bookreceiver\\swf");
		receiver.startup();
	}
}
