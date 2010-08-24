package lms.dao;

import java.util.Collection;
import java.util.List;

import lms.model.Book;

public interface IBookDao {
	//C
	void save(Book book);
	void save(Collection<Integer> books);
	//D
	void delete(Book book);
	void deleteById(int id);
	void deleteByIdCol(Collection<Integer> ids);
	//U
	void update(Book book);
	//R 复杂的查询暂略
	Book getBookById(int id);
	List<Book> listBook(int page);
}
