package lms.model;

public class Manager {
	private int id;
	private String loginName;
	private String password;
	private String name;
	private String contact;
	private int ext_int;
	private String ext_String;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public int getExt_int() {
		return ext_int;
	}
	public void setExt_int(int ext_int) {
		this.ext_int = ext_int;
	}
	public String getExt_String() {
		return ext_String;
	}
	public void setExt_String(String ext_String) {
		this.ext_String = ext_String;
	}
}
