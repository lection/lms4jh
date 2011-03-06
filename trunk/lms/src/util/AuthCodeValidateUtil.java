package util;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;

public class AuthCodeValidateUtil {
	public static boolean validate(){
		String authCode = ServletActionContext.getRequest().getParameter("lms_authCode");
		String rand = (String)ServletActionContext.getRequest().getSession().getAttribute("rand");
		if(!StringUtils.isBlank(authCode)&&authCode.equals(rand)){
			return true;
		}
		return false;
	}
}
