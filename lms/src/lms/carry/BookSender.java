package lms.carry;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.List;

import lms.dao.IBookDao;
import lms.model.Book;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class BookSender {
	
	private IBookDao bookDao;
	private String bookDir;
	private String swfDir;
	
	public void sender(String ip,int port) {
		List<Book> listBook = bookDao.listBook(bookDao.getTotal()-10, bookDao.getTotal()+1);
		Socket socket = null;
		ObjectOutputStream oos = null;
		InputStream is = null;
		
		try{
			for(Book book:listBook){
				socket = new Socket(ip,port);
				oos = new ObjectOutputStream(socket.getOutputStream());
				is = socket.getInputStream();
				oos.writeObject(book);
				File pdf = new File(bookDir,book.getFileName());
				if(pdf.exists()){
					oos.writeObject(pdf.length());
					sendBook(pdf,oos);
					oos.flush();
				}else{
					oos.writeObject(new Long(-1));
				}
				is.read();
				if(book.getSwf()!=null){
					File swf = null;
					for(int i=1;i<=book.getSegments();i++){
						swf = new File(new File(swfDir,book.getSwf()),i+".swf");
						if(swf.exists()){
							oos.writeObject(swf.length());
							sendBook(swf,oos);
							oos.flush();
						}else{
							oos.writeObject(new Long(-1));
						}
						is.read();
					}
				}
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			try {
				is.close();
			} catch (IOException e) {}
			try {
				oos.close();
			} catch (IOException e) {}
			try {
				socket.close();
			} catch (IOException e) {}
		}
	}
	
	private void sendBook(File file,OutputStream os) throws IOException{
		FileInputStream fis = new FileInputStream(file);
		int i = -1;
		byte[] buffer = new byte[8*1024];
		while((i=fis.read(buffer))!=-1){
			os.write(buffer, 0, i);
		}
		fis.close();
		file.delete();
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

	public static void main(String[] args) throws Exception{
		ApplicationContext context = new FileSystemXmlApplicationContext("E:\\workspace\\lms\\WebContent\\WEB-INF\\applicationContext.xml");
		BookSender sender = new BookSender();
		sender.setBookDao((IBookDao)context.getBean("bookDao"));
		sender.setBookDir("E:\\test\\book");
		sender.setSwfDir("E:\\test\\swf");
		sender.sender("127.0.0.1", 9527);
	}
}
