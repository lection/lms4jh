package lms.model;

import java.util.Date;

public class LmsLog {
	private Long id;
	private String loginName;
	private Long userId;
	private Integer role;
	private	String ip;
	private String handle;
	private Date date;
	
	public LmsLog() {
		super();
	}

	public LmsLog(String loginName, long userId, int role, String ip,
			String handle, Date date) {
		super();
		this.loginName = loginName;
		this.userId = userId;
		this.role = role;
		this.ip = ip;
		this.handle = handle;
		this.date = date;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Integer getRole() {
		return role;
	}

	public void setRole(Integer role) {
		this.role = role;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getHandle() {
		return handle;
	}

	public void setHandle(String handle) {
		this.handle = handle;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

}
