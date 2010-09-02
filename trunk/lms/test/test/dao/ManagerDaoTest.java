package test.dao;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

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
		m1.setLoginName("test_123");
		m1.setPassword("test_password");
		m1.setName("good");
		m1.setExt_String("junit_test");
		managerDao.save(m1);
		Manager m2 = null;
		m2 = managerDao.loadManager(m1.getLoginName());
		
		assertEquals(m2.getLoginName(), m1.getLoginName());
		assertEquals(m2.getPassword(), m1.getPassword());
		
		m2.setPassword("123");
		managerDao.update(m2);
		m2 = managerDao.loadManager(m2.getId());
		assertEquals("123",m2.getPassword());
		
		managerDao.deleteByLoginName(m1.getLoginName());
		Manager m3 = null;
		m3 = managerDao.loadManager(m1.getLoginName());
		assertNull(m3);
	}
	
	@Test
	public void test2(){
		List<Manager> managerList = managerDao.listManager();
		int size = managerList.size();
		Manager m1 = new Manager();
		m1.setLoginName("test3");
		m1.setPassword("test_password");
		m1.setExt_String("junit_test");
		managerDao.save(m1);
		assertEquals(size+1, managerDao.listManager().size());
		managerDao.deleteByLoginName(m1.getLoginName());
		assertEquals(size, managerDao.listManager().size());
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
