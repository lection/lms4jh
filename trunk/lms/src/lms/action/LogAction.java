package lms.action;

import java.util.Date;

import lms.dao.ILmsLogDao;
import util.LmsPage;

public class LogAction {
	private ILmsLogDao lmsLogDao;
	private LmsPage page;
	private int pageNum = 1;
	private String userName;
	private Integer role;
	private Date startDate;
	private Date endDate;
	
	public String execute(){
		list();
		return "success";
	}
	
	public String log_list(){
		list();
		return "success";
	}
	
	public String log_find(){
		page = new LmsPage(pageNum,20,lmsLogDao.getTotal(userName, role, startDate, endDate));
		page.setContent(lmsLogDao.listLms(userName,role,startDate,endDate,page.getStart(), page.getPageSize()));
		return "success";
	}
	
	private void list(){
		page = new LmsPage(pageNum,20,lmsLogDao.getTotal());
		page.setContent(lmsLogDao.listLms(page.getStart(), page.getPageSize()));
	}
	
	public ILmsLogDao getLmsLogDao() {
		return lmsLogDao;
	}

	public void setLmsLogDao(ILmsLogDao lmsLogDao) {
		this.lmsLogDao = lmsLogDao;
	}

	public LmsPage getPage() {
		return page;
	}

	public void setPage(LmsPage page) {
		this.page = page;
	}

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Integer getRole() {
		return role;
	}

	public void setRole(Integer role) {
		this.role = role;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
}
