package lms.action;

import lms.dao.ITypeDao;
import lms.model.Type;

import org.apache.struts2.ServletActionContext;

import util.LogUtil;

import com.opensymphony.xwork2.ActionSupport;

public class TypeAction extends ActionSupport{
	private static final long serialVersionUID = -6429856638056806694L;
	private ITypeDao typeDao;
	private Type type;
	
	public String type_manager(){
		ServletActionContext.getRequest().setAttribute("list", typeDao.listType());
		return "manager";
	}
	
	public String type_add(){
		return "add";
	}
	
	public String type_save(){
		typeDao.save(type);
		addActionMessage(type.getName()+" 分类 添加成功");
		LogUtil.userLog("添加分类"+type.getName());
		return type_manager();
	}
	
	public String type_delete(){
		type = typeDao.loadType(type.getId());
		typeDao.deleteById(type.getId());
		addActionMessage(type.getName()+" 分类 删除成功");
		LogUtil.userLog("删除分类"+type.getName());
		return type_manager();
	}
	
	public String type_edit(){
		type = typeDao.loadType(type.getId());
		return "edit";
	}
	
	public String type_update(){
		typeDao.update(type);
		addActionMessage(type.getName()+" 分类修改成功");
		LogUtil.userLog("修改分类"+type.getName());
		return type_manager();
	}
	
	public ITypeDao getTypeDao() {
		return typeDao;
	}
	public void setTypeDao(ITypeDao typeDao) {
		this.typeDao = typeDao;
	}
	public Type getType() {
		return type;
	}
	public void setType(Type type) {
		this.type = type;
	}
}
