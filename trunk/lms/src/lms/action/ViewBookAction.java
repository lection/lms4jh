package lms.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import lms.dao.IBookDao;
import lms.model.Book;

import com.opensymphony.xwork2.ActionSupport;

public class ViewBookAction extends ActionSupport {
	private static final long serialVersionUID = 1186714423595527381L;
	private InputStream paper;
	private String fileDir;
	private Book book;
	private IBookDao bookDao;
	
	public String execute() throws FileNotFoundException{
		book = bookDao.getBookById(book.getId());
		paper = new FileInputStream(
			new File(fileDir,book.getFileName()+".swf"));
		return SUCCESS;
	}

	public InputStream getPaper() {
		return paper;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public void setBookDao(IBookDao bookDao) {
		this.bookDao = bookDao;
	}

	public void setFileDir(String fileDir) {
		this.fileDir = fileDir;
	}
}
