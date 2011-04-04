package lms.service.impl;

import java.io.File;
import java.util.Properties;
import java.util.UUID;

import lms.model.Book;
import lms.model.Type;

import org.apache.commons.io.FileUtils;
import static util.BookUploadMessageUtil.*;

public class BookUploadServiceSegmentsImpl extends BookUploadServiceImpl {

	@Override
	public void uploadBook(Book book, File bookFile, String postfix, File swf,
			File coverImg, String imgFix, Type[] types,Properties prop) throws Exception {
		String fileId = UUID.randomUUID().toString();
		File newBook = null;
		File newCoverImg = null;
		File newSwf = null;
		try{
			if(bookFile != null){
				book.setFileName(fileId+"."+postfix);
				newBook = new File(bookDir,book.getFileName());
				FileUtils.copyFile(bookFile, newBook);
				message("图书文件成功接收，拷贝工作结束。");
			}else{
				message("没有上传图书文件");
			}
			
			if(coverImg != null){
				book.setCoverImg(fileId+"."+imgFix);
				newCoverImg = new File(imgDir,book.getCoverImg());
				FileUtils.copyFile(coverImg, newCoverImg);
				message("图书封面成功接收，拷贝工作结束。");
			}else{
				book.setCoverImg("default.jpg");
				message("没有上传图书封面,使用默认封面。");
			}
			book.setSwf(fileId);
			newSwf =  new File(swfDir,book.getSwf());
			newSwf.mkdir();
			if(swf != null){
				FileUtils.copyFile(swf, new File(newSwf,"1.swf"));
				book.setSegments(1);
				book.setSegmentSize(book.getPages());
				message("图书浏览文件接收成功。");
			}else if(newBook != null){
				message("开始执行文件转换，请稍等。");
				conver.conver(postfix,newBook,fileId,swfDir,book,prop);
			}else{
				book.setSwf(null);
				message("只有图书介绍信息，没有图书文件与浏览文件。");
			}
			bookDao.save(book);
			message("图书信息成功加入数据库。完成保存。");
		}catch(Exception se){
			se.printStackTrace();
			deleteFile(new File[]{newBook,newCoverImg,newSwf});
			throw se;
		}
	}

	@Override
	public void delete(int id) {
		Book book = bookDao.getBookById(id);
		bookDao.deleteById(id);
		deleteFile(new File[]{
				new File(swfDir,book.getSwf()),
				new File(bookDir,book.getFileName()),
				new File(imgDir,book.getCoverImg())
		});
	}
	
}
