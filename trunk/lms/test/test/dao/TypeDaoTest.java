package test.dao;


import static org.junit.Assert.*;

import java.util.List;

import lms.dao.ITypeDao;
import lms.model.Type;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import test.commons.SystemConstant;


public class TypeDaoTest{
	private ITypeDao typeDao;
	private ApplicationContext context;
	
	@Before
	public void setUp() throws Exception {
		context = new FileSystemXmlApplicationContext(SystemConstant.APPLICATION_CONTEXT_PATH);
		typeDao = (ITypeDao)context.getBean("typeDao");
	}
	
	@Test
	public void test1(){
		Type t1 = new Type();
		List<Type> list1 = typeDao.listType();
		t1.setName("test1");
		t1.setDesc("hahahahaha");
		typeDao.save(t1);
		Type t2 = typeDao.loadType("test1");
		Type t3 = typeDao.loadType(t2.getId());
		assertEquals("test1", t2.getName());
		assertEquals(t2.getName(), t3.getName());
		assertEquals("hahahahaha", t2.getDesc());
		assertEquals(t2.getDesc(), t3.getDesc());
		assertEquals(typeDao.listType().size(), list1.size()+1);
		t2.setName("updateName");
		typeDao.update(t2);
		t2 = typeDao.loadType(t2.getId());
		assertEquals("updateName",t2.getName());
		t2.setName("test3");
		typeDao.save(t2);
		assertEquals(typeDao.listType().size(), list1.size()+2);
		typeDao.deleteById(t2.getId());
		t2 = typeDao.loadType(t2.getId());
		assertEquals(typeDao.listType().size(), list1.size()+1);
		assertNull(t2);
		typeDao.deleteByName("test3");
		t2 = typeDao.loadType("test3");
		assertNull(t2);
		assertEquals(typeDao.listType().size(), list1.size());
	}

}
