package util;

import java.util.Date;

import org.apache.struts2.ServletActionContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import lms.dao.ILmsLogDao;
import lms.model.LmsLog;
import lms.model.LmsUser;
import lms.model.Manager;

public class LogUtil {
	private static ILmsLogDao lmsLogDao;
	
	private static void init(){
		lmsLogDao = (ILmsLogDao)WebApplicationContextUtils.getWebApplicationContext(
				ServletActionContext.getServletContext()).getBean("lmsLogDao");
	}
	
	public static void userLog(String msg){
		if(lmsLogDao == null){
			init();
		}
		LmsUser user = (LmsUser)ServletActionContext.getRequest().getSession().getAttribute(LmsUser.LOGIN_FLAG);
		if(user != null){
			lmsLogDao.save(new LmsLog(user.getUserName(),user.getUserId(),user.getRole()
					,ServletActionContext.getRequest().getRemoteAddr(),msg,new Date()));;
		}
	}
	
	
}
