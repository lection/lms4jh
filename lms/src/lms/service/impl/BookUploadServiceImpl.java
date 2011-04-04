package lms.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.Properties;
import java.util.UUID;

import lms.dao.IBookDao;
import lms.exception.FileConverException;
import lms.model.Book;
import lms.model.Type;
import lms.service.IBookUploadService;

import org.apache.commons.io.FileUtils;

import util.ITypeConver;
/**
 * 该类已被BookUploadServiceSegmentsImpl取代
 * @author lection
 *
 */
public abstract class BookUploadServiceImpl implements IBookUploadService{
	
	protected String swfDir;
	protected String bookDir;
	protected String imgDir;
	protected ITypeConver conver;
	protected IBookDao bookDao;

	@Override
	public void uploadBook(Book book, File bookFile, String postfix, File swf,
			File coverImg,String imgFix,Type[] types,Properties prop) throws Exception{
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
				conver.conver(postfix,newBook,fileId,swfDir,book,null);
			}
			bookDao.save(book);
		}catch(Exception se){
			deleteFile(new File[]{newBook,newCoverImg,newSwf});
			throw se;
		}
	}
	
	protected void deleteFile(File[] files){
		for(File f:files){
			if(f != null){
				if(f.isDirectory()){
					File[] subFiles = f.listFiles();
					for(File sf:subFiles){
						sf.delete();
					}
				}
				f.delete();
			}
		}
	}

	@Override
	public InputStream downloadBook(String fileName) throws FileNotFoundException {
		return new FileInputStream(new File(bookDir,fileName));
	}

	@Override
	public InputStream viewSWF(String fileName,int seg) throws FileNotFoundException{
		return new FileInputStream(new File(new File(swfDir,fileName),seg+".swf"));
	}

	@Override
	//暂时不实现替换文件功能,只更新book信息。
	public void updateBook(Book book, File bookFile, String fileType, File swf,
			File coverImg,String imgFix, Type[] types,Properties prop) {
		
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
