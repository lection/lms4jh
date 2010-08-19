package lms.action;

import lms.dao.IManagerDao;

public class TestAction {
	private IManagerDao managerDao;

	public String execute(){
		System.out.println("struts------test------------go");
		System.out.println(managerDao);
		return "success";
	}

	public void setManagerDao(IManagerDao managerDao) {
		this.managerDao = managerDao;
	}
	
}
