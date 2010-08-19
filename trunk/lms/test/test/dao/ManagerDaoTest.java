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
		managerDao.save(m1);
		Manager m2 = managerDao.loadManager(m1.getLoginName());
		assert m2.getLoginName().equals(m1.getLoginName());
		assert m2.getPassword().equals(m1.getPassword());
		managerDao.deleteByLoginName(m1.getLoginName());
		Manager m3 = managerDao.loadManager(m1.getLoginName());
		assert m3 == null;
	}

	@After
	public void setDown(){
		Connection conn = null;
		Statement stat = null;
		try {
			conn = ((DataSource)context.getBean("dataSource")).getConnection();
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
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
