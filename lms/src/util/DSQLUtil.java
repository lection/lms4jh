package util;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.Validate;

public class DSQLUtil {
	public static final String SQL_SPLIT = "{#WHERE#}";
	private String beforeSql;
	private String afterSql;
	private String[] columnNames;
	private String limit = "";
	
	public DSQLUtil(String sql,String[] columnNames){
		String[] sqlSplit = StringUtils.split(sql,SQL_SPLIT);
		Validate.isTrue(sqlSplit.length==2||
				StringUtils.contains(sql, SQL_SPLIT),
				"传入的SQL不符合规范，应当有且只有一个SQL_SPLIT常量");
		beforeSql = sqlSplit[0];
		afterSql = sqlSplit.length==2?sqlSplit[1]:"";
		this.columnNames = columnNames;
	}
	
	public WhereUtil createWhereUtil(){
		return new WhereUtil();
	}
	
	public class WhereUtil{
		private StringBuilder buffer = new StringBuilder(beforeSql+" where 1=1");
		private List<Object> paramList = new ArrayList<Object>(0);
		private int index = 0;
		
		public WhereUtil addEqual(Object object){
			if(object != null && !StringUtils.isBlank(object.toString())){
				buffer.append(" and "+columnNames[index]+"=?");
				paramList.add(object);
			}
			index++;
			return this;
		}
		
		public WhereUtil addSimpleLike(Object object){
			if(object != null && !StringUtils.isBlank(object.toString())){
				buffer.append(" and "+columnNames[index]+" like ?");
				paramList.add("%"+object+"%");
			}
			index++;
			return this;
		}
		
		public WhereUtil addCustomer(Object object){
			if(object != null && !StringUtils.isBlank(object.toString())){
				String and = columnNames[index].startsWith("\\s*and")?" ":" and ";
				buffer.append(and+columnNames[index]);
				paramList.add(object);
			}
			index++;
			return this;
		}
		
		public WhereUtil addLimit(int start,int length){
			limit = " limit "+start+","+length;
			return this;
		}
		
		public List<Object> getParamList(){
			return this.paramList;
		}
		
		public String toString(){
			return buffer.toString()+afterSql+limit;
		} 
	}
	
}
