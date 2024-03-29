package lms.action;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Properties;

import lms.model.Book;
import lms.model.LmsUser;
import lms.model.Manager;
import lms.model.Type;
import lms.service.IBookUploadService;

import org.apache.struts2.ServletActionContext;

import util.BookUploadMessageUtil;
import util.LogUtil;

import com.opensymphony.xwork2.ActionSupport;

public class BookFileAction extends ActionSupport{

	private static final long serialVersionUID = -5218468722274151299L;
	
	private Book book = new Book();
	private File bookFile;
	private File swf;
	private String bookFileFileName;
	private File coverImg;
	private String coverImgFileName;
	private int[] types = new int[0];
	private IBookUploadService bookUploadService;
	private Date date;
	
	public String save(){
		BookUploadMessageUtil.begin("文件上传结束,开始执行处理工作");
		Type[] types = new Type[this.types.length];
		for(int i=0;i<this.types.length;i++){
			types[i] = new Type();
			types[i].setId(this.types[i]);
		}
		book.setTypes(new ArrayList<Type>(Arrays.asList(types)));
		book.setBookDate(date);
		book.setCreatedDate(new Date());
		book.setCreatedBy(((Manager)ServletActionContext.getRequest().getSession().getAttribute(LmsUser.LOGIN_FLAG)).getLoginName());
		try {
			bookUploadService.uploadBook(book, bookFile, bookFileFileName
					, swf, coverImg, coverImgFileName, types,null);
			addActionMessage("图书 "+book.getName()+" 成功添加");
			LogUtil.userLog("添加图书"+book.getName());
			BookUploadMessageUtil.finished("成功添加图书 《"+book.getName()+"》");
		} catch (Exception e) {
//			e.printStackTrace();
			BookUploadMessageUtil.finished("转换失败:"+e.getMessage());
			addActionMessage(e.getMessage());
		}
		return null;
	}
	
	public String delete(){
		bookUploadService.delete(book.getId());
		return "lms_del";
	}
	
	public void validateSave(){
		if(types==null || types.length==0){
			addFieldError("types", "图书至少选择一个类型");
		}
	}

	public void setBookFile(File bookFile) {
		this.bookFile = bookFile;
	}

	public void setCoverImg(File coverImg) {
		this.coverImg = coverImg;
	}

	public Book getBook() {
		return book;
	}
	
	public void setBook(Book book) {
		this.book = book;
	}
	
	public void setTypes(int[] types) {
		this.types = types;
	}

	public void setBookUploadService(IBookUploadService bookUploadService) {
		this.bookUploadService = bookUploadService;
	}

	public void setSwf(File swf) {
		this.swf = swf;
	}

	public void setBookFileFileName(String bookFileFileName) {
		this.bookFileFileName = bookFileFileName.substring(bookFileFileName.lastIndexOf(".")+1);
	}

	public void setCoverImgFileName(String coverImgFileName) {
		this.coverImgFileName = coverImgFileName.substring(coverImgFileName.lastIndexOf(".")+1);
	}

	public void setDate(String date) {
		this.date = java.sql.Date.valueOf(date);
	}
}
