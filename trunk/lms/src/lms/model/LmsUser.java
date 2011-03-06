package lms.model;

public interface LmsUser {
	public static final String LOGIN_FLAG = "LMS_LOGIN_FLAG";

	public static final int MANAGER = -1;
	public static final int AUDITING = 0;
	public static final int STUDENT = 1;
	public static final int SCHOOLFELLOW = 2;
	
	String getUserName();
	long getUserId();
	Integer getRole();
	
}
