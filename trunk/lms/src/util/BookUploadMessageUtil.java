package util;
/**
 * 记录图书上传过程信息
 * @author lection
 *
 */
public class BookUploadMessageUtil {
	public static void begin(String msg){
		System.out.println(msg);
	}
	
	public static void message(String msg){
		System.out.println(msg);
	}
	
	public static void finished(String msg){
		System.out.println(msg);
	}
}
