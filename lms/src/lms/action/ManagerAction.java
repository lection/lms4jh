package lms.action;

import java.util.List;

import javax.servlet.http.HttpSession;

import lms.dao.IManagerDao;
import lms.model.Manager;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

public class ManagerAction extends ActionSupport{
	private static final long serialVersionUID = 7692682388105922418L;
	public static final String LOGIN_FLAG = "LMS_MANAGER_LOGIN";
	private IManagerDao managerDao;
	private Manager manager;
	
	
	public String manager_login(){
		
		return "login";
	}
	
	public String manager_logout(){
		HttpSession session = ServletActionContext.getRequest().getSession(false);
		if(session != null){
			session.invalidate();
		}
		addActionMessage("退出图书管理系统");
		return manager_login();
	}
	
	public String manager_auth(){
		Manager m = null;
//		System.out.println(manager.getLoginName());
		m = managerDao.loadManager(manager.getLoginName());
		if( m == null || !manager.getPassword().equals(m.getPassword())){
//			System.out.println(m);
			addActionError("用户名或密码错误");
			return manager_login();
		}
		manager = m;
		ServletActionContext.getRequest().getSession().setAttribute(LOGIN_FLAG, manager);
		return "welcome";
	}
	
	public String manager_manager(){
		List<Manager> list = managerDao.listManager();
		ServletActionContext.getRequest().setAttribute("managerList", list);
		return "manager";
	}
	
	public String manager_addManager(){
		managerDao.save(manager);
		addActionMessage("用户 "+manager.getLoginName()+" 添加成功");
		return manager_manager();
	}
	
	public String manager_deleteMgr(){
		System.out.println(manager.getId());
		manager = managerDao.loadManager(manager.getId());
		managerDao.deleteById(manager.getId());
		addActionMessage(manager.getLoginName()+" "+manager.getName()+" 用户删除成功");
		return manager_manager();
	}
	
	public String manager_editmanager(){
		manager = managerDao.loadManager(manager.getId());
		return "editmanager";
	}
	
	public String manager_updateMgr(){
		Manager m_update = managerDao.loadManager(manager.getId());
		m_update.setName(manager.getName());
		m_update.setContact(manager.getContact());
		if(!StringUtils.isBlank(manager.getPassword())){
			m_update.setPassword(manager.getPassword());
		}
		managerDao.update(m_update);
		addActionMessage(manager.getLoginName()+" 用户修改成功");
		return manager_manager();
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
