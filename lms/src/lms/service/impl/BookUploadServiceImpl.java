package lms.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.UUID;

import lms.dao.IBookDao;
import lms.exception.FileConverException;
import lms.model.Book;
import lms.model.Type;
import lms.service.IBookUploadService;

import org.apache.commons.io.FileUtils;

import util.ITypeConver;

public class BookUploadServiceImpl implements IBookUploadService{
	
	private String swfDir;
	private String bookDir;
	private String imgDir;
	private ITypeConver conver;
	private IBookDao bookDao;

	@Override
	public void uploadBook(Book book, File bookFile, String postfix, File swf,
			File coverImg,String imgFix,Type[] types) throws Exception{
		String fileId = UUID.randomUUID().toString();
		File newBook = null;
		File newCoverImg = null;
		File newSwf = null;
		try{
			if(bookFile != null){
				book.setFileName(fileId+"."+postfix);
				newBook = new File(bookDir,book.getFileName());
				FileUtils.copyFile(bookFile, newBook);
			}
			
			if(coverImg != null){
				book.setCoverImg(fileId+"."+imgFix);
				newCoverImg = new File(imgDir,book.getCoverImg());
				FileUtils.copyFile(coverImg, newCoverImg);
			}else{
				book.setCoverImg("default.jpg");
			}
			if(swf != null){
				book.setSwf(fileId+".swf");
				newSwf =  new File(swfDir,book.getSwf());
				FileUtils.copyFile(swf, newSwf);
			}else if(newBook != null){
				if(conver.conver(postfix,newBook,fileId,swfDir)){
					book.setSwf(fileId+".swf");
				}
			}
			bookDao.save(book);
		}catch(SQLException se){
			deleteFile(new File[]{newBook,newCoverImg,newSwf});
			throw se;
		}catch(IOException ioe){
			deleteFile(new File[]{newBook,newCoverImg,newSwf});
			throw new Exception("服务器文件复制出现故障，可能是文件路径配置错误，或者没有权限进行读写。\n\r",ioe);
		}catch(FileConverException fce){
			deleteFile(new File[]{newBook,newCoverImg,newSwf});
			throw fce;
		}
	}
	
	private void deleteFile(File[] files){
		for(File f:files){
			if(f != null)f.delete();
		}
	}

	@Override
	public InputStream downloadBook(String fileName) throws FileNotFoundException {
		return new FileInputStream(new File(bookDir,fileName));
	}

	@Override
	public InputStream viewSWF(String fileName) throws FileNotFoundException{
		return new FileInputStream(new File(swfDir,fileName));
	}

	@Override
	public void updateBook(Book book, File bookFile, String fileType, File swf,
			File coverImg,String imgFix, Type[] types) {
		
	}

	public String getSwfDir() {
		return swfDir;
	}

	public void setSwfDir(String swfDir) {
		this.swfDir = swfDir;
	}

	public String getBookDir() {
		return bookDir;
	}

	public void setBookDir(String bookDir) {
		this.bookDir = bookDir;
	}

	public String getImgDir() {
		return imgDir;
	}

	public void setImgDir(String imgDir) {
		this.imgDir = imgDir;
	}

	public void setConver(ITypeConver conver) {
		this.conver = conver;
	}

	public void setBookDao(IBookDao bookDao) {
		this.bookDao = bookDao;
	}
}
