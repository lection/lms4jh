package lms.dao;

import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

import lms.model.Book;
import lms.model.Type;

public interface IBookDao {
	//C
	void save(Book book);
	void save(Collection<Book> books);
	//D
	void delete(Book book);
	void deleteById(int id);
	void deleteByIdCol(Collection<Integer> ids);
	//U
	void update(Book book);
	//R 
	int getTotal();
	Book getBookById(int id);
	List<Book> listBook(int start,int size);
	int getTotal(Type type);
	List<Book> listBook(int start,int size,Type type);
	int getTotal(String name,String author,String code,String bookConcern,String desc,Integer typeId);
	List<Book> listBook(String name,String author,String code,String bookConcern,String desc,Integer typeId,int start,int size);
}
