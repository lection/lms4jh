package lms.dao;

import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

import lms.model.Book;
import lms.model.Type;

public interface IBookDao {
	//C
	void save(Book book) throws SQLException;
	void save(Collection<Book> books) throws SQLException;
	//D
	void delete(Book book) throws SQLException;
	void deleteById(int id) throws SQLException;
	void deleteByIdCol(Collection<Integer> ids) throws SQLException;
	//U
	void update(Book book) throws SQLException;
	//R 
	int getTotal();
	Book getBookById(int id);
	List<Book> listBook(int start,int size);
	int getTotal(Type type);
	List<Book> listBook(int start,int size,Type type);
	int getTotal(String name,String author,String code,String bookConcern,Integer typeId);
	List<Book> listBook(String name,String author,String code,String bookConcern,Integer typeId,int start,int size);
}
