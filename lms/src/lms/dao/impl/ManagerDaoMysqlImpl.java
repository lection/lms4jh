package lms.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import lms.dao.IManagerDao;
import lms.model.Manager;

import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

public class ManagerDaoMysqlImpl extends JdbcDaoSupport implements IManagerDao {
	private ResultSetExtractor rsExtracotr = new ResultSetExtractor(){
		@Override
		public Object extractData(ResultSet rs) {
			Manager manager = null;
			try {
				manager = new Manager();
				manager.setId(rs.getInt("c_id"));
				manager.setLoginName(rs.getString("c_login_name"));
				manager.setPassword(rs.getString("c_password"));
				manager.setName(rs.getString("c_name"));
				manager.setContact(rs.getString("c_contact"));
				manager.setExt_int(rs.getInt("c_ext_int"));
				manager.setExt_String(rs.getString("c_ext_str"));
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return manager;
		}};
		
	private RowMapper rowMapper = new RowMapper(){
		@Override
		public Object mapRow(ResultSet rs, int row) throws SQLException {
			Manager manager = null;
			try {
				manager = new Manager();
				manager.setId(rs.getInt("c_id"));
				manager.setLoginName(rs.getString("c_login_name"));
				manager.setPassword(rs.getString("c_password"));
				manager.setName(rs.getString("c_name"));
				manager.setContact(rs.getString("c_contact"));
				manager.setExt_int(rs.getInt("c_ext_int"));
				manager.setExt_String(rs.getString("c_ext_str"));
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return manager;
		}
	};
	
	private Object[] getManagerAtrrs(Manager manager){
		return new Object[]{manager.getId(),manager.getLoginName(),manager.getPassword(),manager.getName(),
				manager.getContact(),manager.getExt_int(),manager.getExt_String()}; 
	}

	@Override
	public void save(Manager manager) {
		getJdbcTemplate().update("insert into t_manager(c_id,c_login_name,c_password,c_name,c_contact,c_ext_int,c_ext_str)" +
				" values(?,?,?,?,?,?,?)", getManagerAtrrs(manager));
	}

	@Override
	public void update(Manager manager) {
		// TODO Auto-generated method stub

	}

	@Override
	public void changePassword(String loginName, String password) {
		// TODO Auto-generated method stub

	}

	@Override
	public void changePassword(int id, String password) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteByLoginName(String loginName) {
		getJdbcTemplate().update("delete from t_manager where c_login_name=?", new Object[]{loginName});
	}

	@Override
	public void deleteById(int id) {
		getJdbcTemplate().update("delete from t_manager where c_id=?", new Object[]{id});
	}

	@Override
	public List<Manager> listManager() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Manager loadManager(int id) {
		return (Manager)getJdbcTemplate().query("select * from t_manager where c_id=?", new Object[]{id}, rsExtracotr);
	}

	@Override
	public Manager loadManager(String loginName) {
		return (Manager)getJdbcTemplate().query("select * from t_manager where c_login_name=?", new Object[]{loginName}, rsExtracotr);
	}

}