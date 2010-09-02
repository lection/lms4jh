package lms.dao;

import java.util.List;

import lms.model.Manager;

public interface IManagerDao {
	void save(Manager manager);
	void update(Manager manager);
	void changePassword(String loginName,String password);
	void changePassword(int id,String password);
	void deleteByLoginName(String loginName);
	void deleteById(int id);
	List<Manager> listManager(); 
	Manager loadManager(int id);
	Manager loadManager(String loginName);
}
