package lms.action;

import java.util.List;

import lms.dao.IBookDao;
import lms.dao.ITypeDao;
import lms.model.Type;
import util.LmsPage;

public class LmsAction {
	private IBookDao bookDao;
	private ITypeDao typeDao;
	private List<Type> listType;
	private LmsPage page;
	private int pageNum=1;
	private int pageSize = 2;
	private int typeId;
	
	public String execute(){
		listType = typeDao.listType();
		page = new LmsPage(1,pageSize,bookDao.getTotal());
		page.setContent(bookDao.listBook(page.getStart(), page.getPageSize()));
		return "success";
	}
	
	public String listBook(){
//		listType = typeDao.listType();
		page = new LmsPage(pageNum,pageSize,bookDao.getTotal());
		page.setContent(bookDao.listBook(page.getStart(), page.getPageSize()));
		return "div";
	}
	
	public String listBookByType(){
		Type type = new Type();
		type.setId(typeId);
		page = new LmsPage(pageNum,pageSize,bookDao.getTotal(type));
		page.setContent(bookDao.listBook(page.getStart(), page.getPageSize(), type));
		return "div";
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

	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}
}
