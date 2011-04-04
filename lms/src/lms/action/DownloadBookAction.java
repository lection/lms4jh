package lms.action;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

import lms.dao.IBookDao;
import lms.dao.IStudentDao;
import lms.model.Book;
import lms.model.LmsUser;
import lms.model.Student;
import lms.service.IBookUploadService;

import org.apache.struts2.ServletActionContext;

import util.LogUtil;

import com.mchange.v2.resourcepool.ResourcePool.Manager;
import com.opensymphony.xwork2.ActionSupport;

public class DownloadBookAction extends ActionSupport {
	private static final long serialVersionUID = 1208506144728436928L;
	private InputStream stream;
	private String fileDir;
	private Book book;
	private String fileName;
	private IBookDao bookDao;
	private IStudentDao studentDao;
	private IBookUploadService bookUploadService;
	
	public String execute() throws FileNotFoundException,UnsupportedEncodingException{
		book = bookDao.getBookById(book.getId());
		LmsUser user = (LmsUser)ServletActionContext.getRequest().getSession().getAttribute(LmsUser.LOGIN_FLAG);
		if(user.getRole() == LmsUser.AUDITING || user.getRole() == LmsUser.SCHOOLFELLOW){
			return "fileNotFound";
		}
		if(user.getRole() == LmsUser.STUDENT){
			Student student = (Student)user;
			if(student.getMaxDownloadCount() <= student.getDownloadCount()){
				return "fileNotFound";
			}else{
				student.setDownloadCount(student.getDownloadCount()+1);
				studentDao.updateDownloadCount(student.getId(), student.getDownloadCount());
			}
		}
		if(book.getStatue() == Book.CAN_DOWNLOAD || (user != null && user.getClass()==Manager.class)){
//			stream = 
//				new FileInputStream(new File(fileDir,book.getFileName()));
			
			stream = bookUploadService.downloadBook(book.getFileName());
			fileName = book.getName()+book.getFileName().substring(
					book.getFileName().lastIndexOf('.'));
			fileName = new String(fileName.getBytes(),"ISO-8859-1");
			LogUtil.userLog("下载图书《"+book.getName()+"》");
			return SUCCESS;
		}else{
			return "fileNotFound";
		}
	}

	public InputStream getStream() {
		return stream;
	}

	public void setBook(Book book) {
		this.book = book;
	}
	
	public String getFileName(){
		return this.fileName;
	}
	
	public void setBookDao(IBookDao bookDao) {
		this.bookDao = bookDao;
	}

	public void setFileDir(String fileDir) {
		this.fileDir = fileDir;
	}

	public void setBookUploadService(IBookUploadService bookUploadService) {
		this.bookUploadService = bookUploadService;
	}

	public void setStudentDao(IStudentDao studentDao) {
		this.studentDao = studentDao;
	}
}