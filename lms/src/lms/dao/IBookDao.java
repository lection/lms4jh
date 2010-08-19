package lms.dao;

import java.util.Collection;

import lms.model.Book;

public interface IBookDao {
	void save(Book book);
	void save(Collection<Integer> books);
	void delete(Book book);
	void deleteById(int id);
	void deleteByIdCol(Collection<Integer> ids);
	void update(Book book);
	Book getBookById(int id);
}
