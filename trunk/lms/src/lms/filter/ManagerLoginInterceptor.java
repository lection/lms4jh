package lms.filter;

import javax.servlet.http.HttpSession;

import lms.model.LmsUser;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;

public class ManagerLoginInterceptor implements Interceptor {

	private static final long serialVersionUID = -6910597840527075283L;

	@Override
	public void destroy() {}

	@Override
	public void init() {}

	@Override
	public String intercept(ActionInvocation inv) throws Exception {
		HttpSession session = 
			ServletActionContext.getRequest().getSession(false);
		if(session == null){
			return "login";
		}
		LmsUser user = (LmsUser) session.getAttribute(LmsUser.LOGIN_FLAG);
		if(user == null || user.getRole() != LmsUser.MANAGER){
			return "login";
		}
		return inv.invoke();
	}

}
