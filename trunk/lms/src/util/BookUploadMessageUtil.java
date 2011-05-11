package util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;

/**
 * 记录图书上传过程信息
 * @author lection
 *
 */
public class BookUploadMessageUtil {
	public static Map<String,List<LmsUploadMessage>> msgMap = new HashMap<String,List<LmsUploadMessage>>();
	public static final String FINISHED = "finished";
	public static final String BEGIN = "begin";
	public static final String MESSAGE = "message";
	public static final String NONE = "none";
	
	public static synchronized void begin(String msg){
		clean();
		buildMessage(BEGIN,msg);
	}
	
	public static synchronized void message(String msg){
		buildMessage(MESSAGE,msg);
	}
	
	public static synchronized void finished(String msg){
		buildMessage(FINISHED,msg);
	}
	
	public static void clean(){
		msgMap.remove(getSessionId());
	}
	
	public static void cleanAll(){
		msgMap.clear();
	}
	
	private static void buildMessage(String status,String message){
		String sessionId = getSessionId();
		List<LmsUploadMessage> list = msgMap.get(sessionId);
		if(list == null){
			list =  new ArrayList<LmsUploadMessage>();
			msgMap.put(sessionId, list);
		}
		list.add(new LmsUploadMessage(status,message));
	}
	
	private static String getSessionId(){
		return ServletActionContext.getRequest().getSession().getId();
	}
	
	public static synchronized List<LmsUploadMessage> getMessageMap(){
		List<LmsUploadMessage> result = msgMap.remove(getSessionId());
		return result;
	}
	
	public static class LmsUploadMessage{
		private String status;
		private String message;
		
		public LmsUploadMessage() {
		}

		public LmsUploadMessage(String status, String message) {
			this.status = status;
			this.message = message;
		}
		
		public String getStatus() {
			return status;
		}
		public void setStatus(String status) {
			this.status = status;
		}
		public String getMessage() {
			return message;
		}
		public void setMessage(String message) {
			this.message = message;
		}
	}
}
