package lms.action;

import util.BookUploadMessageUtil;
import lms.carry.BookReceiver;
import lms.carry.BookSender;

public class BookPassAction {
	
	private BookReceiver receiver;
	private BookSender sender;
	private boolean flag = false;
	private String ip;
	private String port;
	
	public String pass_manager(){
		return "manager";
	}
	
	public String pass_startup(){
		receiver.startup();
		return "manager";
	}
	
	public String pass_shutdown(){
		receiver.shutdown();
		return "manager";
	}
	
	public String pass_send(){
		try{
			BookUploadMessageUtil.begin("图书传输开始,地址:"+ip+"，端口:"+port+"");
			sender.sender(ip, Integer.parseInt(port),flag);
			BookUploadMessageUtil.finished("图书传输顺利结束");
		}catch(NumberFormatException nfe){
			BookUploadMessageUtil.finished("端口输入错误。");
		}catch(Exception ex){
			BookUploadMessageUtil.finished("图书传输出现问题，停止传输。");
		}
		return null;
	}

	public BookReceiver getReceiver() {
		return receiver;
	}

	public void setReceiver(BookReceiver receiver) {
		this.receiver = receiver;
	}

	public BookSender getSender() {
		return sender;
	}

	public void setSender(BookSender sender) {
		this.sender = sender;
	}

	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}
	
}
