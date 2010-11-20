package lms.exception;

public class FileConverException extends Exception{
	public FileConverException(String postfix,Exception e){
		super(postfix+"文件类型转换失败，可能是上传文件格式错误，或者服务器端转换程序出现故障。",e);
	}
}
