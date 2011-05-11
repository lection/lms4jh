package lms.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import lms.dao.IBookDao;
import lms.dao.ITypeDao;
import lms.model.Book;
import lms.model.Type;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;

import util.DSQLUtil;
import util.HiloUtil;
import util.DSQLUtil.WhereUtil;

public class BookDaoMysqlImpl extends JdbcDaoSupport implements IBookDao{
	
	private static final String BOOK_COLUMN
		= "c_id,c_name,c_author,c_translator,c_pages,c_code,c_book_concern,c_py,c_fpy,c_fileName" +
			",c_read_count,c_download_count,c_state,c_book_date,c_cover_img,c_swf,c_segments,c_segment_size,c_desc,c_created_date,c_created_by";
	
	private RowMapper rowMapper = new RowMapper(){
		@Override
		public Object mapRow(ResultSet rs, int index) throws SQLException {
			Book book = new Book();
			book.setId(rs.getInt("c_id"));
			book.setName(rs.getString("c_name"));
			book.setAuthor(rs.getString("c_author"));
			book.setTranslator(rs.getString("c_translator"));
			book.setPages(rs.getInt("c_pages"));
			book.setCode(rs.getString("c_code"));
			book.setBookConcern(rs.getString("c_book_concern"));
			book.setPinYin(rs.getString("c_py"));
			book.setFullPinYin(rs.getString("c_fpy"));
			book.setFileName(rs.getString("c_fileName"));
			book.setReadCount(rs.getInt("c_read_count"));
			book.setDownLoadCount(rs.getInt("c_download_count"));
			book.setStatue(rs.getInt("c_state"));
			book.setBookDate(rs.getDate("c_book_date"));
			book.setCoverImg(rs.getString("c_cover_img"));
			book.setSwf(rs.getString("c_swf"));
			book.setSegments(rs.getInt("c_segments"));
			book.setSegmentSize(rs.getInt("c_segment_size"));
			book.setDesc(rs.getString("c_desc"));
			book.setCreatedDate(rs.getDate("c_created_date"));
			book.setCreatedBy(rs.getString("c_created_by"));
			return book;
		}
	};
	
	private static final String[] columnNames = {
		"b.c_name","b.c_author","b.c_code","b.c_book_concern","b.c_desc","bt.c_type_id"
	};
	private DSQLUtil totalSQLUtil = new DSQLUtil(
			"select count(b.c_id) from t_book as b,t_book_type as bt"
			+DSQLUtil.SQL_SPLIT+" and b.c_id=bt.c_book_id",columnNames);
	private DSQLUtil listBookUtil = new DSQLUtil(
			"select distinct b.* from t_book as b,t_book_type as bt"+DSQLUtil.SQL_SPLIT+" and b.c_id=bt.c_book_id  order by b.c_id desc"
			,columnNames);
	
	private static final String[] columnNamesWithOutType = {
		"b.c_name","b.c_author","b.c_code","b.c_book_concern","b.c_desc"
	};
	private DSQLUtil totalSQLUtilWithOutType = new DSQLUtil(
			"select count(b.c_id) from t_book as b"+DSQLUtil.SQL_SPLIT,columnNamesWithOutType);
	private DSQLUtil listBookUtilWithOutType = new DSQLUtil(
			"select distinct b.* from t_book as b"+DSQLUtil.SQL_SPLIT,columnNamesWithOutType);
	
	private ITypeDao typeDao;
	private HiloUtil hiloUtil;

	@Override
	public void delete(Book book) {
		if(book != null)deleteById(book.getId());
	}

	@Override
	public void deleteById(int id) {
		getJdbcTemplate().update("delete from t_book_type where c_book_id=?",new Object[]{id});
		getJdbcTemplate().update("delete from t_book where c_id=?", new Object[]{id});
	}

	@Override
	public void deleteByIdCol(Collection<Integer> ids) {
		for(int id:ids){
			deleteById(id);
		}
	}
	
	@Override
	public int getTotal(){
		return getJdbcTemplate().queryForInt("select count(c_id) from t_book");
	}

	@Override
	public Book getBookById(int id) {
		try{
			Book book =
				(Book)getJdbcTemplate().queryForObject("select "+BOOK_COLUMN+" from t_book where c_id=?"
					, new Object[]{id}, rowMapper);
			book.setTypes(typeDao.listType(id));
			return book;
		}catch(Exception ex){
//			ex.printStackTrace();
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Book> listBook(int start,int size) {
		try{
			return (List<Book>)getJdbcTemplate()
			.query("select "+BOOK_COLUMN+" from t_book order by c_id desc limit ?,?"
			, new Object[]{start,size},rowMapper);
		}catch(Exception ex){
			ex.printStackTrace();
			return Collections.EMPTY_LIST;
		}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Book> listBook(int start, int size, Type type) {
		try{
			return (List<Book>)getJdbcTemplate()
			.query("select "+BOOK_COLUMN+" from t_book as b,t_book_type as bt where bt.c_type_id=? and c_book_id=b.c_id order by b.c_id desc limit ?,?"
			, new Object[]{type.getId(),start,size},rowMapper);
		}catch(Exception ex){
			ex.printStackTrace();
			return Collections.EMPTY_LIST;
		}
	}

	@Override
	public void save(Book book) {
		book.setId(hiloUtil.getValue());
		getJdbcTemplate().update("insert into t_book ("+BOOK_COLUMN+")" +
				" values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)"
				, new Object[]{
				book.getId(),
				book.getName(),book.getAuthor(),book.getTranslator(),book.getPages(),book.getCode(),book.getBookConcern(),book.getPinYin(),book.getFullPinYin()
				,book.getFileName(),book.getReadCount(),book.getDownLoadCount(),book.getStatue(),book.getBookDate(),
				book.getCoverImg(),book.getSwf(),book.getSegments(),book.getSegmentSize(),book.getDesc(),book.getCreatedDate(),book.getCreatedBy()
		});
		for(Type type:book.getTypes()){
			saveBTM(book, type);
		}
	}

	@Override
	public void save(Collection<Book> books) {
		for(Book book:books){
			save(book);
		}
	}

	@Override
	public void update(Book book) {
		getJdbcTemplate().update("update t_book set c_name=?,c_author=?,c_translator=?" +
//				",c_pages=?" +
				",c_code=?,c_book_concern=?,c_py=?,c_fpy=?" +
//				",c_fileName=?" +
				",c_read_count=?,c_download_count=?," +
				"c_state=?,c_book_date=?,c_cover_img=?,c_desc=?,c_created_date=?,c_created_by=? where c_id=?"
				, new Object[]{
				book.getName(),book.getAuthor(),book.getTranslator()
				//,book.getPages()
				,book.getCode(),book.getBookConcern()
				,book.getPinYin(),book.getFullPinYin()
				//,book.getFileName()
				,book.getReadCount(),book.getDownLoadCount(),book.getStatue(),book.getBookDate(),
				book.getCoverImg(),book.getDesc(),book.getCreatedDate(),book.getCreatedBy(),book.getId()
		});
		Set<Type> newSet = new HashSet<Type>(book.getTypes());
		Set<Type> oldSet = new HashSet<Type>(typeDao.listType(book.getId()));
		Set<Type> newSetBak = new HashSet<Type>(newSet);
		Set<Type> oldSetBak = new HashSet<Type>(oldSet);
		newSetBak.removeAll(oldSet);
		oldSetBak.removeAll(newSet);
		
		for(Type type:newSetBak){
			saveBTM(book,type);
		}
		
		for(Type type:oldSetBak){
			getJdbcTemplate().update("delete from t_book_type where c_book_id=? and c_type_id=?"
					, new Object[]{book.getId(),type.getId()});
		}
	}
	
	//保存Book Type 映射
	private void saveBTM(Book book,Type type){
		getJdbcTemplate().update("insert into t_book_type(c_book_id,c_type_id) values(?,?)"
				, new Object[]{book.getId(),type.getId()});
	}

	public void setTypeDao(ITypeDao typeDao) {
		this.typeDao = typeDao;
	}

	public void setHiloUtil(HiloUtil hiloUtil) {
		this.hiloUtil = hiloUtil;
	}

	@Override
	public int getTotal(String name, String author, String code,
			String bookConcern,String desc, Integer typeId) {
		WhereUtil util = (typeId!=null?totalSQLUtil:totalSQLUtilWithOutType).createWhereUtil();
		util.addSimpleLike(name).addSimpleLike(author)
		.addEqual(code).addSimpleLike(bookConcern).addSimpleLike(desc);
		if(typeId!=null)util.addEqual(typeId);
//		System.out.println(util.toString());
		return getJdbcTemplate().queryForInt(util.toString(), util.getParamList().toArray());
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Book> listBook(String name, String author, String code,
			String bookConcern,String desc, Integer typeId,int start,int size) {
		WhereUtil util = (typeId!=null?listBookUtil:listBookUtilWithOutType).createWhereUtil();
		util.addSimpleLike(name).addSimpleLike(author)
		.addEqual(code).addSimpleLike(bookConcern).addSimpleLike(desc);
		if(typeId!=null){
			util.addEqual(typeId).addLimit(start, size);
		}else{
			util.addLimit(start, size);
		}
//		System.out.println(util.toString());
		return getJdbcTemplate().query(util.toString(), util.getParamList().toArray(), rowMapper);
	}

	@Override
	public int getTotal(Type type) {
		return getJdbcTemplate()
		.queryForInt("select count(b.c_id) from t_book as b,t_book_type as bt where bt.c_type_id=? and bt.c_book_id=b.c_id"
				,new Object[]{type.getId()});
	}
}
