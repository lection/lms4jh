package util;

import java.io.File;
import java.util.Properties;

import lms.exception.FileConverException;
import lms.model.Book;

public interface ITypeConver {
	/**
	 * 
	 * @param postfix
	 * @param book
	 * @param fileId
	 * @param swfDir
	 * @param prop	其他可能需要的属性
	 * @throws FileConverException
	 */
	void conver(String postfix,File bookFile,String fileId,String swfDir,Book book,Properties prop) throws FileConverException;
}
