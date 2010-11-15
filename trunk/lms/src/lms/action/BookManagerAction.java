package lms.action;

import java.util.List;

import lms.dao.IBookDao;
import lms.dao.ITypeDao;
import lms.model.Book;
import lms.model.Type;
import util.LmsPage;

import com.opensymphony.xwork2.ActionSupport;

public class BookManagerAction extends ActionSupport{
	
	private static final long serialVersionUID = -108259628369491316L;
	
	private IBookDao bookDao;
	private ITypeDao typeDao;
	private Book book;
	private List<Type> types;
	private LmsPage page;
	private int bookManagerPageSize = 10;
	
	public String book_manager(){
		types = typeDao.listType();
		page = new LmsPage(1,bookManagerPageSize,bookDao.getTotal());
		page.setContent(bookDao.listBook(page.getStart(), page.getPageSize()));
		return "manager";
	}
	
	public String book_add(){
		types = typeDao.listType();
		return "add";
	}
	
	public String book_book(){
		book = bookDao.getBookById(book.getId());
		return "book";
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public IBookDao getBookDao() {
		return bookDao;
	}

	public void setBookDao(IBookDao bookDao) {
		this.bookDao = bookDao;
	}

	public ITypeDao getTypeDao() {
		return typeDao;
	}

	public void setTypeDao(ITypeDao typeDao) {
		this.typeDao = typeDao;
	}

	public List<Type> getTypes() {
		return types;
	}

	public LmsPage getPage() {
		return page;
	}
}
