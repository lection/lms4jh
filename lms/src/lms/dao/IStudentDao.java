package lms.dao;

import java.util.Collection;
import java.util.List;

import lms.model.Student;

public interface IStudentDao {
	//C
	public void save(Student student);
	public void save(Collection<Student> students);
	//D
	public void delete(Student student);
	public void delete(Long id);
	//U
	public void update(Student student);
	//R
	int getTotal();
	int getTotal(Student student);
	List<Student> listStudent(int start,int size);
	List<Student> listStudent(Student student,int start,int size);
	Student loadStudent(long id);
	Student loadStudent(String loginName);
}
