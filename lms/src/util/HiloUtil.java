package util;

import org.springframework.jdbc.core.support.JdbcDaoSupport;

public class HiloUtil extends JdbcDaoSupport {
	private int hi = 1;
	private int low = 0;
	private int length = 30;
	private int base = hi*length;
	
	public synchronized int getValue(){
		if((low+1) == length){
			low = 0;
			hi += 1;
			base = hi*length;
			getJdbcTemplate().update("update t_book_hilo set c_hi="+(hi+1));
		}else{
			low++;
		}
		return base+low;
	}

	public void setLength(int length) {
		this.length = length;
	}

	@Override
	protected void initTemplateConfig() {
		super.initTemplateConfig();
		hi = getJdbcTemplate().queryForInt("select c_hi from t_book_hilo");
		getJdbcTemplate().update("update t_book_hilo set c_hi="+(hi+1));
		base = hi*length;
	}
}
