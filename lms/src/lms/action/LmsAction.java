package lms.action;

import java.util.List;

import lms.dao.IBookDao;
import lms.dao.ITypeDao;
import lms.model.Book;
import lms.model.Type;
import util.LmsPage;
import util.LogUtil;

public class LmsAction {
	private IBookDao bookDao;
	private ITypeDao typeDao;
	private List<Type> listType;
	private LmsPage page;
	private int pageNum=1;
	private int pageSize = 12;
	private Integer typeId;
	private Book book;
	
	public String execute(){
		listType = typeDao.listType();
		page = new LmsPage(1,pageSize,bookDao.getTotal());
		page.setContent(bookDao.listBook(page.getStart(), page.getPageSize()));
		return "success";
	}
	
	public String listBook(){
		page = new LmsPage(pageNum,pageSize,bookDao.getTotal());
		page.setContent(bookDao.listBook(page.getStart(), page.getPageSize()));
		return "div";
	}
	
	public String findBook(){
		try{
			int total = 0;
			List<Book> list = null;
			if(book != null){
				total=bookDao.getTotal(book.getName(), book.getAuthor(), book.getCode(), book.getBookConcern(),book.getDesc(), typeId);
				page = new LmsPage(pageNum,pageSize,total);
				list = bookDao.listBook(book.getName(), book.getAuthor(), book.getCode(), book.getBookConcern(),book.getDesc(), typeId, page.getStart(), page.getPageSize()); 
			}else if(typeId != null){
				Type type = new Type();
				type.setId(typeId);
				total = bookDao.getTotal(type);
				page = new LmsPage(pageNum,pageSize,total);
				list = bookDao.listBook(page.getStart(),page.getPageSize(), type);
			}else{
				total=bookDao.getTotal();
				page = new LmsPage(pageNum,pageSize,total);
				list = bookDao.listBook(page.getStart(), page.getPageSize());
			}
			page.setContent(list);
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return "div";
	}
	
	public String listBookByType(){
		Type type = new Type();
		type.setId(typeId);
		page = new LmsPage(pageNum,pageSize,bookDao.getTotal(type));
		page.setContent(bookDao.listBook(page.getStart(), page.getPageSize(), type));
		return "div";
	}
	
	public String viewBookSeg(){
		book = bookDao.getBookById(book.getId());
		if(book == null){
			return "input";
		}
		LogUtil.userLog("阅读图书《"+book.getName()+"》");
		return "success";
	}

	public void setBookDao(IBookDao bookDao) {
		this.bookDao = bookDao;
	}

	public void setTypeDao(ITypeDao typeDao) {
		this.typeDao = typeDao;
	}

	public List<Type> getListType() {
		return listType;
	}

	public LmsPage getPage() {
		return page;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getTypeId() {
		return typeId;
	}

	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}
}
