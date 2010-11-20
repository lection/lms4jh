package util;

import java.io.File;

import lms.exception.FileConverException;

public interface ITypeConver {
	boolean conver(String postfix,File book,String fileId,String swfDir) throws FileConverException;
}
