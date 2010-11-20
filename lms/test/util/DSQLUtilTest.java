package util;

import org.junit.Before;
import org.junit.Test;

public class DSQLUtilTest {
	
	@Test(expected=RuntimeException.class)
	public void testDSQLUtil() {
		DSQLUtil sqlUtil = new DSQLUtil("select * from",new String[]{});
	}

	@Test
	public void testCreateWhereUtil(){
		String sql = "select * from"+DSQLUtil.SQL_SPLIT;
		String[] columnNames = {"c_name","c_code"};
		DSQLUtil sqlUtil = new DSQLUtil(sql,columnNames);
		String sqlFinal = 
			sqlUtil.createWhereUtil().addSimpleLike(null).addEqual("1100").toString();
		System.out.println(sqlFinal);
	}
}
