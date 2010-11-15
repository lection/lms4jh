package lms.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import lms.dao.ITypeDao;
import lms.model.Type;

public class TypeDaoMysqlImpl extends JdbcDaoSupport implements ITypeDao {
	private RowMapper rowMapper = new RowMapper(){
		@Override
		public Object mapRow(ResultSet rs, int arg1) throws SQLException {
			Type type = new Type();
			type.setId(rs.getInt("c_id"));
			type.setName(rs.getString("c_name"));
			type.setDesc(rs.getString("c_desc"));
			return type;
		}
	};

	@Override
	public void deleteById(int id) {
		getJdbcTemplate().update("delete from t_type where c_id=?", new Object[]{id});
	}

	@Override
	public void deleteByName(String name) {
		getJdbcTemplate().update("delete from t_type where c_name=?", new Object[]{name});
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Type> listType() {
		return getJdbcTemplate().query("select c_id,c_name,c_desc from t_type",rowMapper);
	}

	@Override
	public Type loadType(int id) {
		try{
			return (Type)getJdbcTemplate().queryForObject("select c_id,c_name,c_desc from t_type where c_id=?", new Object[]{id},rowMapper);
		}catch(Exception ex){
			return null;
		}
	}

	@Override
	public Type loadType(String name) {
		try{
			return (Type)getJdbcTemplate().queryForObject("select c_id,c_name,c_desc from t_type where c_name=?", new Object[]{name},rowMapper);
		}catch(Exception ex){
			return null;
		}
	}

	@Override
	public void save(Type type) {
		getJdbcTemplate().update("insert into t_type (c_id,c_name,c_desc) values(null,?,?)",
				new Object[]{type.getName(),type.getDesc()});
	}

	@Override
	public void update(Type type) {
		getJdbcTemplate().update("update t_type set c_name=?,c_desc=? where c_id=?",
				new Object[]{type.getName(),type.getDesc(),type.getId()});
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Type> listType(int bookId) {
		try{
			return getJdbcTemplate().query(
					"select t.c_id,t.c_name,t.c_desc from t_type as t,t_book_type as bt where bt.c_book_id=? and bt.c_type_id=t.c_id"
					,new Object[]{bookId} ,rowMapper);
		}catch(Exception ex){
			return Collections.EMPTY_LIST;
		}
	}
 
}
