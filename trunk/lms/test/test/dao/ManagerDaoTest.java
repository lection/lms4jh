package test.dao;


import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import lms.dao.IManagerDao;
import lms.model.Manager;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import test.commons.SystemConstant;


public class ManagerDaoTest{
	private IManagerDao managerDao;
	private ApplicationContext context;
	
	@Before
	public void setUp() throws Exception {
		context = new FileSystemXmlApplicationContext(SystemConstant.APPLICATION_CONTEXT_PATH);
		managerDao = (IManagerDao)context.getBean("managerDao");
	}
	
	@Test
	public void test1(){
		Manager m1 = new Manager();
		m1.setLoginName("test");
		m1.setPassword("test_password");
		m1.setName("good");
		m1.setExt_String("junit_test");
		try {
			managerDao.save(m1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		Manager m2 = null;
		try {
			m2 = managerDao.loadManager(m1.getLoginName());
		} catch (SQLException e) {
		}
		assert !m2.getLoginName().equals(m1.getLoginName());
		assert m2.getPassword().equals(m1.getPassword());
		try {
			managerDao.deleteByLoginName(m1.getLoginName());
		} catch (SQLException e) {
		}
		Manager m3 = null;
		try {
			m3 = managerDao.loadManager(m1.getLoginName());
		} catch (SQLException e) {
		}
		assert m3 == null;
	}
	
	@Test
	public void test2(){
		
	}

	@After
	public void setDown(){
		Connection conn = null;
		Statement stat = null;
		DataSource dataSource = (DataSource)context.getBean("dataSource");
		try {
			conn = dataSource.getConnection();
			stat = conn.createStatement();
			stat.executeUpdate("delete from t_manager where c_ext_str='junit_test'");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(stat != null)
					stat.close();
				if(conn != null)
					conn.close();
				((com.mchange.v2.c3p0.ComboPooledDataSource)dataSource).close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
