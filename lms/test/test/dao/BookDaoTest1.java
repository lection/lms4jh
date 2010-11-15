package test.dao;


import static org.junit.Assert.assertEquals;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import lms.dao.IBookDao;
import lms.dao.ITypeDao;
import lms.model.Book;
import lms.model.Type;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import test.commons.SystemConstant;

public class BookDaoTest1{
	private IBookDao bookDao;
	private ITypeDao typeDao;
	private ApplicationContext context;
	
	@Before
	public void setUp() throws Exception {
		context = new FileSystemXmlApplicationContext(SystemConstant.APPLICATION_CONTEXT_PATH);
		bookDao = (IBookDao)context.getBean("bookDao");
		typeDao = (ITypeDao)context.getBean("typeDao");
	}
	
	@Test
	public void testBook() throws Exception{
		Book book = new Book();
		book.setName("test_1");
		book.setFileName("test_file");
		int total1 = bookDao.getTotal();
		bookDao.save(book);
		int total2 = bookDao.getTotal();
		assertEquals(total1+1, total2);
		bookDao.deleteById(book.getId());
		int total3 = bookDao.getTotal();
		assertEquals(total1,total3);
	}
	
	@Test
	public void testBookType() throws SQLException{
		typeDao.save(new Type("book_type_1","book_type_desc"));
		typeDao.save(new Type("book_type_2","book_type_desc"));
		typeDao.save(new Type("book_type_3","book_type_desc"));
		typeDao.save(new Type("book_type_4","book_type_desc"));
		Type t1 = typeDao.loadType("book_type_1");
		Type t2 = typeDao.loadType("book_type_2");
		Type t3 = typeDao.loadType("book_type_3");
		Type t4 = typeDao.loadType("book_type_4");
		
		Book book = new Book();	
		book.setName("test_1");
		book.setFileName("test_file");
		book.getTypes().add(t1);
		book.getTypes().add(t2);
		book.getTypes().add(t3);
		bookDao.save(book);
		Book b1 = bookDao.getBookById(book.getId());
		assertEquals(b1.getTypes().size(), 3);
		assertEquals(b1.getTypes().get(0), t1);
		assertEquals(b1.getTypes().get(2), t3);
		
		List<Type> typeList = new ArrayList<Type>();
		typeList.add(t3);
		typeList.add(t4);
		b1.setTypes(typeList);
		b1.setAuthor("Eric");
		bookDao.update(b1);
		Book b2 = bookDao.getBookById(book.getId());
		assertEquals(b2.getAuthor(),"Eric");
		assertEquals("Types Size："+b2.getTypes().size(),b2.getTypes().size(),2);
		assertEquals(b2.getTypes().get(0), t3);
		assertEquals(b2.getTypes().get(1), t4);
		
		bookDao.delete(book);
	}
	
	@After
	public void setDown(){
		typeDao.deleteByName("book_type_1");
		typeDao.deleteByName("book_type_2");
		typeDao.deleteByName("book_type_3");
		typeDao.deleteByName("book_type_4");
	}
}
