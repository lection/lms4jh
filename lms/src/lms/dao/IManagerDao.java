package lms.dao;

import java.sql.SQLException;
import java.util.List;

import lms.model.Manager;

public interface IManagerDao {
	void save(Manager manager) throws SQLException;
	void update(Manager manager) throws SQLException;
	void changePassword(String loginName,String password) throws SQLException;
	void changePassword(int id,String password) throws SQLException;
	void deleteByLoginName(String loginName) throws SQLException;
	void deleteById(int id) throws SQLException;
	List<Manager> listManager() throws SQLException; 
	Manager loadManager(int id) throws SQLException;
	Manager loadManager(String loginName) throws SQLException;
}
