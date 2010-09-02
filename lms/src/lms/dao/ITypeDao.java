package lms.dao;

import java.util.List;

import lms.model.Type;

public interface ITypeDao {
	//C
	void save(Type type);
	//R
	Type loadType(int id);
	Type loadType(String name);
	List<Type> listType();
	//U
	void update(Type type);
	//D
	void deleteById(int id);
	void deleteByName(String name);
}
