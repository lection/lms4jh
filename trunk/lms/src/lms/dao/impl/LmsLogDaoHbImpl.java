package lms.dao.impl;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import util.DSQLUtil;

import lms.dao.ILmsLogDao;
import lms.model.LmsLog;

public class LmsLogDaoHbImpl extends HibernateDaoSupport implements ILmsLogDao {

	@Override
	public List<LmsLog> listLms(final int start,final int size) {
		return getHibernateTemplate().executeFind(new HibernateCallback(){
			@Override
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				return session.createQuery("from LmsLog order by id desc").setFirstResult(start).setMaxResults(size).list();
			}
		});
	}

	@Override
	public LmsLog load(long id) {
		return (LmsLog)getHibernateTemplate().get(LmsLog.class, id);
	}

	@Override
	public void save(LmsLog log) {
		getHibernateTemplate().save(log);
	}

	@Override
	public int getTotal() {
		return ((Number)getHibernateTemplate().execute(new HibernateCallback(){
			@Override
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				return session.createQuery("select count(l.id) from LmsLog as l").uniqueResult();
			}
		})).intValue();
	}

	@Override
	public int getTotal(final String userName,final Integer role,final  Date startDate,
			final Date endDate) {
		return ((Number)getHibernateTemplate().execute(new HibernateCallback(){
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				LmsLog log = new LmsLog();
				log.setLoginName(userName);
				log.setRole(role);
				Criteria ct = session.createCriteria(LmsLog.class).setProjection(Projections.rowCount()).add(Example.create(log));
				if(startDate != null)ct.add(Restrictions.gt("date", startDate));
				if(endDate != null)ct.add(Restrictions.lt("date", endDate));
				return ct.uniqueResult();
			}})).intValue();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<LmsLog> listLms(final String userName,final  Integer role,final  Date startDate,
			final Date endDate, final int start, final int size) {
		return getHibernateTemplate().executeFind(new HibernateCallback(){
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				LmsLog log = new LmsLog();
				log.setLoginName(userName);
				log.setRole(role);
				Criteria ct = session.createCriteria(LmsLog.class).add(Example.create(log))
				.setFirstResult(start).setMaxResults(size);
				if(startDate != null)ct.add(Restrictions.gt("date", startDate));
				if(endDate != null)ct.add(Restrictions.lt("date", endDate));
				return ct.list();
			}});
	}

}
