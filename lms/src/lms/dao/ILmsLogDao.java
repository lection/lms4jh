package lms.dao;

import java.util.Date;
import java.util.List;

import lms.model.LmsLog;

public interface ILmsLogDao {
	void save(LmsLog log);
	LmsLog load(long id);
	List<LmsLog> listLms(int start,int size);
	List<LmsLog> listLms(String userName,Integer role,Date startDate,Date endDate,int start,int size);
	int getTotal();
	int getTotal(String userName,Integer role,Date startDate,Date endDate);
}
