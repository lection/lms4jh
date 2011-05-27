package lms.action;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import lms.dao.IBookDao;
import lms.dao.ITypeDao;
import lms.model.Book;
import lms.model.Type;

import org.apache.struts2.ServletActionContext;

import util.BookUploadMessageUtil;
import util.LmsPage;

import com.opensymphony.xwork2.ActionSupport;

public class BookManagerAction extends ActionSupport{
	
	private static final long serialVersionUID = -108259628369491316L;
	private static final int BOOK_MANAGER_PAGESIZE = 10;
	
	private IBookDao bookDao;
	private ITypeDao typeDao;
	private Book book;
	private List<Type> types;
	private LmsPage page;
	private Integer typeId;
	private int pageNum = 1;
	
	public String book_manager(){
//		types = typeDao.listType();
//		page = new LmsPage(1,bookManagerPageSize,bookDao.getTotal());
//		page.setContent(bookDao.listBook(page.getStart(), page.getPageSize()));
		listBook(1);
		return "manager";
	}
	
	private void listBook(int pageNum){
		types = typeDao.listType();
		page = new LmsPage(pageNum,BOOK_MANAGER_PAGESIZE,bookDao.getTotal());
		page.setContent(bookDao.listBook(page.getStart(), page.getPageSize()));
	}
	
	public String book_list(){
		listBook(pageNum);
		return "list_div";
	}
	
	public String book_find(){
		types = typeDao.listType();
		try{
			int total = 0;
			List<Book> list = null;
			if(book != null){
				total=bookDao.getTotal(book.getName(), book.getAuthor(), book.getCode(), book.getBookConcern(),book.getDesc(), typeId);
				page = new LmsPage(pageNum,BOOK_MANAGER_PAGESIZE,total);
				list = bookDao.listBook(book.getName(), book.getAuthor(), book.getCode(), book.getBookConcern(),book.getDesc(), typeId, page.getStart(), page.getPageSize()); 
			}else if(typeId != null){
				Type type = new Type();
				type.setId(typeId);
				total = bookDao.getTotal(type);
				page = new LmsPage(pageNum,BOOK_MANAGER_PAGESIZE,total);
				list = bookDao.listBook(page.getStart(),page.getPageSize(), type);
			}else{
				total=bookDao.getTotal();
				page = new LmsPage(pageNum,BOOK_MANAGER_PAGESIZE,total);
				list = bookDao.listBook(page.getStart(), page.getPageSize());
			}
			page.setContent(list);
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return "list_div";
	}
	
	public String book_add(){
		BookUploadMessageUtil.clean();
		types = typeDao.listType();
		return "add";
	}
	public String book_edit(){
		types = typeDao.listType();
		book = bookDao.getBookById(book.getId());
		return "edit";
	}
	
	public String book_book(){
		book = bookDao.getBookById(book.getId());
		return "book";
	}
	
	public String book_update(){
		Book b = bookDao.getBookById(book.getId());
		b.setName(book.getName());
		b.setAuthor(book.getAuthor());
		b.setTranslator(book.getTranslator());
		b.setBookConcern(book.getBookConcern());
		b.setBookDate(book.getBookDate());
		b.setCode(book.getCode());
		b.setDesc(book.getDesc());
		b.setFullPinYin(book.getFullPinYin());
		b.setPinYin(book.getPinYin());
		b.setStatue(book.getStatue());
		String[] typeValues = ServletActionContext.getRequest().getParameterValues("types");
		if(typeValues != null){
			Type[] newTypes = new Type[typeValues.length];
			for(int i=0;i<typeValues.length;i++){
				newTypes[i]=typeDao.loadType(Integer.parseInt(typeValues[i]));
			}
			b.setTypes(Arrays.asList(newTypes));
		}
		bookDao.update(b);
		book = b;
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

	public Integer getTypeId() {
		return typeId;
	}

	public void setTypeId(Integer typeId) {
		if
			(typeId<1)this.typeId=null;
		else
			this.typeId = typeId;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}
}
