package lms.dao.impl;

import java.sql.SQLException;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import lms.dao.IStudentDao;
import lms.model.Student;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Projections;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class StudentDaoHbImpl extends HibernateDaoSupport implements IStudentDao {

	@Override
	public void delete(Student student) {
		getHibernateTemplate().delete(student);
	}

	@Override
	public void delete(Long id) {
		getHibernateTemplate().delete(getHibernateTemplate().get(Student.class, id));
	}

	@Override
	public List<Student> listStudent(final int start,final int size) {
		return getHibernateTemplate().executeFind(new HibernateCallback(){
			@Override
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				return session.createQuery("from Student s order by s.id desc").setFirstResult(start).setMaxResults(size).list();
			}
		});
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Student> listStudent(final Student student,final int start,final int size) {
		return getHibernateTemplate().executeFind(new HibernateCallback(){
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				return session.createCriteria(Student.class)
				.add(Example.create(student))
				.setFirstResult(start).setMaxResults(size)
				.list();
			}
		});
	}

	@Override
	public void save(Student student) {
		getHibernateTemplate().save(student);
	}

	@Override
	public void save(Collection<Student> students) {

	}

	@Override
	public void update(Student student) {
		getHibernateTemplate().update(student);
	}

	@Override
	public int getTotal() {
		return ((Number)getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				return session.createQuery("select count(s.id) from Student s").uniqueResult();
			}})).intValue();
	}

	@Override
	public int getTotal(final Student student) {
		return ((Number)getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				return session.createCriteria(Student.class).setProjection(Projections.rowCount())
				.add(Example.create(student))
				.uniqueResult();
			}
		})).intValue();
	}

	@Override
	public Student loadStudent(long id) {
		return (Student)getHibernateTemplate().get(Student.class, id);
	}

	@Override
	public Student loadStudent(final String loginName) {
		return (Student)getHibernateTemplate().execute(new HibernateCallback(){
			@Override
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				return session.createQuery("from Student as s where s.loginName=:ln").setString("ln", loginName).uniqueResult();
			}
		});
	}

	@Override
	public void updateDownloadCount(Long id, int count) {
		Student student = (Student)getHibernateTemplate().get(Student.class, id);
		student.setDownloadCount(count);
		student.setLastDownloadDate(new Date());
		getHibernateTemplate().update(student);
	}

}
