package lms.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

import lms.dao.IBookDao;
import lms.model.Book;
import lms.service.IBookUploadService;

import com.opensymphony.xwork2.ActionSupport;

public class DownloadBookAction extends ActionSupport {
	private static final long serialVersionUID = 1208506144728436928L;
	private InputStream stream;
	private String fileDir;
	private Book book;
	private String fileName;
	private IBookDao bookDao;
	private IBookUploadService bookUploadService;
	
	public String execute() throws FileNotFoundException,UnsupportedEncodingException{
		book = bookDao.getBookById(book.getId());
		if(book.getStatue() == Book.CAN_DOWNLOAD){
//			stream = 
//				new FileInputStream(new File(fileDir,book.getFileName()));
			
			stream = bookUploadService.downloadBook(book.getFileName());
			fileName = book.getName()+book.getFileName().substring(
					book.getFileName().lastIndexOf('.'));
			fileName = new String(fileName.getBytes(),"ISO-8859-1");
			return SUCCESS;
		}else{
			return "fileNotFound";
		}
	}

	public InputStream getStream() {
		return stream;
	}

	public void setBook(Book book) {
		this.book = book;
	}
	
	public String getFileName(){
		return this.fileName;
	}
	
	public void setBookDao(IBookDao bookDao) {
		this.bookDao = bookDao;
	}

	public void setFileDir(String fileDir) {
		this.fileDir = fileDir;
	}

	public void setBookUploadService(IBookUploadService bookUploadService) {
		this.bookUploadService = bookUploadService;
	}
}