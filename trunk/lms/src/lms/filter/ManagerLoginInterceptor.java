package lms.filter;

import javax.servlet.http.HttpSession;

import lms.action.ManagerAction;

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
		if(session == null || 
				session.getAttribute(ManagerAction.LOGIN_FLAG) == null){
			return "login";
		}
		return inv.invoke();
	}

}
