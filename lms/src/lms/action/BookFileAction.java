package lms.action;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import lms.dao.IBookDao;
import lms.model.Book;
import lms.model.Type;

import com.opensymphony.xwork2.ActionSupport;

public class BookFileAction extends ActionSupport{
	private Book book = new Book();
	private File bookFile;
	private File coverImg;
	private IBookDao bookDao;
	private int[] types = new int[0];
	
	public String save(){
		try{
			Type[] types = new Type[this.types.length];
			for(int i=0;i<this.types.length;i++){
				types[i] = new Type();
				types[i].setId(this.types[i]);
			}
			book.setTypes(Arrays.asList(types));
			book.setFileName("");
			bookDao.save(book);
			
		}catch(Exception ex){
			addActionError("书籍保存失败");
			ex.printStackTrace();
			return "";
		}
		return SUCCESS;
	}
//	
//	public File getBookFile() {
//		return bookFile;
//	}
//	public void setBookFile(File bookFile) {
//		this.bookFile = bookFile;
//	}
//	public File getCoverImg() {
//		return coverImg;
//	}
//	public void setCoverImg(File coverImg) {
//		this.coverImg = coverImg;
//	}
	public Book getBook() {
		return book;
	}
	public void setBook(Book book) {
		this.book = book;
	}
	public void setBookDao(IBookDao bookDao) {
		this.bookDao = bookDao;
	}
	public void setTypes(int[] types) {
		this.types = types;
	}
}
