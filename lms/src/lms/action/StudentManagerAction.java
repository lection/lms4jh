package lms.action;

import org.apache.struts2.ServletActionContext;

import lms.dao.ILmsLogDao;
import lms.dao.IStudentDao;
import lms.model.LmsUser;
import lms.model.Student;
import util.AuthCodeValidateUtil;
import util.LmsPage;
import util.LogUtil;

import com.opensymphony.xwork2.ActionSupport;

public class StudentManagerAction extends ActionSupport{

	private static final long serialVersionUID = 1L;
	private static final int STUDENT_MANAGER_PAGESIZE = 10;
	
	private IStudentDao studentDao;
	private ILmsLogDao lmsLogDao;
	private Student student;
	private LmsPage page;
	private int pageNum = 1;
	
	public String stu_manager(){
		listStudent();
		return "manager";
	}
	
	private void listStudent(){
		page = new LmsPage(pageNum,STUDENT_MANAGER_PAGESIZE,studentDao.getTotal());
		page.setContent(studentDao.listStudent(page.getStart(), page.getPageSize()));
	}
	
	public String stu_list(){
		listStudent();
		return "success";
	}
	public String stu_find(){
		page = new LmsPage(pageNum,STUDENT_MANAGER_PAGESIZE,studentDao.getTotal(student));
		page.setContent(studentDao.listStudent(student,page.getStart(), page.getPageSize()));
		return "success";
	}
	
	public String stu_add(){return "add";}
	
	public String stu_del(){
		studentDao.delete(student.getId());
		return "lms_del";
	}
	
	public String stu_change(){
		Student s = studentDao.loadStudent(student.getId());
		s.setRole(student.getRole());
		studentDao.update(s);
		return "change";
	}
	
	public String stu_save(){
		if(studentDao.loadStudent(student.getLoginName()) != null){
			addActionError("登录名已经被占用");
		}else{
			studentDao.save(student);
			addActionMessage("登录名为："+student.getLoginName()+" 的学员"+student.getName()+"添加成功");
			LogUtil.userLog("添加学生"+student.getLoginName());
		}
		return "add";
	}
	
	public String stu_register(){
		if(!AuthCodeValidateUtil.validate()){
			return "error";
		}
		if(studentDao.loadStudent(student.getLoginName()) != null){
			return "error";
		}else{
			student.setRole(LmsUser.AUDITING);
			studentDao.save(student);
			return "success";
		}
	}
	
	public String login(){
		if(!AuthCodeValidateUtil.validate()){
			addActionError("验证码错误");
			return "error";
		}
		Student s = studentDao.loadStudent(student.getLoginName());
		if(s == null || !s.getPassword().equals(student.getPassword())){
			addActionError("用户名密码错误");
			return "error";
		}
		ServletActionContext.getRequest().getSession().setAttribute(LmsUser.LOGIN_FLAG, s);
		LogUtil.userLog("登录系统");
		return "success";
	}
	
	public String logout(){
		ServletActionContext.getRequest().getSession().invalidate();
		LogUtil.userLog("退出系统");
		return "success";
	}
	
	public Student getStudent() {
		return student;
	}
	public void setStudent(Student student) {
		this.student = student;
	}

	public LmsPage getPage() {
		return page;
	}

	public void setPage(LmsPage page) {
		this.page = page;
	}

	public IStudentDao getStudentDao() {
		return studentDao;
	}

	public void setStudentDao(IStudentDao studentDao) {
		this.studentDao = studentDao;
	}

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public void setLmsLogDao(ILmsLogDao lmsLogDao) {
		this.lmsLogDao = lmsLogDao;
	}
	
}
