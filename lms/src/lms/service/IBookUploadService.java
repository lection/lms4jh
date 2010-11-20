package lms.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;

import lms.model.Book;
import lms.model.Type;

public interface IBookUploadService {
	void uploadBook(Book book,File bookFile,String postfix,File swf,File coverImg,String imgFix,Type[] types) throws Exception;
	void updateBook(Book book,File bookFile,String postfix,File swf,File coverImg,String imgFix,Type[] types) throws Exception;
	InputStream viewSWF(String fileName) throws FileNotFoundException;
	InputStream downloadBook(String fileName) throws FileNotFoundException;
}
