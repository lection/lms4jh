package lms.action;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import util.BookUploadMessageUtil;

import com.opensymphony.xwork2.Action;

public class BookUploadMessageAction implements Action{

	private JSONArray json;
	
	@Override
	public String execute(){
		try{
			json = JSONArray.fromObject(BookUploadMessageUtil.getMessageMap());
		}catch(Throwable ex){
			ex.printStackTrace();
		}
		return SUCCESS;
	}

	public JSONArray getJson() {
		return json;
	}

	public void setJson(JSONArray json) {
		this.json = json;
	}

}
