package lms.action;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import lms.dao.ILmsLogDao;
import lms.dao.IManagerDao;
import lms.model.LmsLog;
import lms.model.LmsUser;
import lms.model.Manager;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import util.AuthCodeValidateUtil;

import com.opensymphony.xwork2.ActionSupport;
import static util.LogUtil.userLog;

public class ManagerAction extends ActionSupport{
	private static final long serialVersionUID = 7692682388105922418L;
	private IManagerDao managerDao;
	private ILmsLogDao lmsLogDao;
	private Manager manager;
	
	
	public String manager_login(){
		return "login";
	}
	
	public String manager_logout(){
		HttpSession session = ServletActionContext.getRequest().getSession(false);
		userLog("退出系统");
		if(session != null){
			session.invalidate();
		}
		addActionMessage("退出图书管理系统");
		return manager_login();
	}
	
	public String manager_auth(){
		if(!AuthCodeValidateUtil.validate()){
			addActionError("验证码错误");
			return manager_login();
		}
		Manager m = null;
//		System.out.println(manager.getLoginName());
		m = managerDao.loadManager(manager.getLoginName());
		if( m == null || !manager.getPassword().equals(m.getPassword())){
//			System.out.println(m);
			addActionError("用户名或密码错误");
			return manager_login();
		}
		manager = m;
		ServletActionContext.getRequest().getSession().setAttribute(LmsUser.LOGIN_FLAG, manager);
		userLog("登录系统");
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
		userLog("添加用户"+manager.getLoginName());
		return manager_manager();
	}
	
	public String manager_deleteMgr(){
		System.out.println(manager.getId());
		manager = managerDao.loadManager(manager.getId());
		managerDao.deleteById(manager.getId());
		addActionMessage(manager.getLoginName()+" "+manager.getName()+" 用户删除成功");
		userLog("删除用户"+manager.getLoginName());
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
		userLog("编辑用户"+manager.getLoginName());
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

	public ILmsLogDao getLmsLogDao() {
		return lmsLogDao;
	}

	public void setLmsLogDao(ILmsLogDao lmsLogDao) {
		this.lmsLogDao = lmsLogDao;
	}
}
