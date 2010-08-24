package lms.action;

import java.sql.SQLException;

import lms.dao.IManagerDao;
import lms.model.Manager;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

public class ManagerAction extends ActionSupport{
	private static final long serialVersionUID = 7692682388105922418L;
	private IManagerDao managerDao;
	private Manager manager;
	
	
	public String manager_login(){
		System.out.println(managerDao);
		return "login";
	}
	
	public String manager_auth(){
		Manager m = null;
		try {
			m = managerDao.loadManager(manager.getLoginName());
		} catch (SQLException e) {}
		if( m == null || !manager.getPassword().equals(m.getPassword())){
			addActionError("loginError");
			return manager_login();
		}
		manager = m;
		ServletActionContext.getRequest().getSession().setAttribute("manager", manager);
		return "welcome";
	}

	public Manager getManager() {
		return manager;
	}

	public void setManager(Manager manager) {
		this.manager = manager;
	}

	public IManagerDao getManagerDao() {
		return managerDao;
	}

	public void setManagerDao(IManagerDao managerDao) {
		this.managerDao = managerDao;
	}
}
