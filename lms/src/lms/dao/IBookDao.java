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
	//R 复杂的查询暂略
	int getTotal();
	Book getBookById(int id);
	List<Book> listBook(int start,int end);
	List<Book> listBook(int start,int end,Type type);
}
