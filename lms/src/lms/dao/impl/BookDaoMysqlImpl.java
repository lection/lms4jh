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

import util.HiloUtil;

public class BookDaoMysqlImpl extends JdbcDaoSupport implements IBookDao{
	
	private static final String BOOK_COLUMN
		= "c_id,c_name,c_author,c_code,c_book_concern,c_py,c_fpy,c_fileName" +
			",c_read_count,c_download_count,c_state,c_book_date,c_cover_img,c_desc,c_created_date,c_created_by";
	
	private RowMapper rowMapper = new RowMapper(){
		@Override
		public Object mapRow(ResultSet rs, int index) throws SQLException {
			Book book = new Book();
			book.setId(rs.getInt("c_id"));
			book.setName(rs.getString("c_name"));
			book.setAuthor(rs.getString("c_author"));
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
			book.setDesc(rs.getString("c_desc"));
			book.setCreatedDate(rs.getDate("c_created_date"));
			book.setCreatedBy(rs.getString("c_created_by"));
			return book;
		}
	};
	
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
			ex.printStackTrace();
			return null;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Book> listBook(int start,int end) {
		try{
			return (List<Book>)getJdbcTemplate()
			.query("select "+BOOK_COLUMN+" from t_book limit ?,?"
			, new Object[]{start,end},rowMapper);
		}catch(Exception ex){
			ex.printStackTrace();
			return Collections.EMPTY_LIST;
		}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Book> listBook(int start, int end, Type type) {
		try{
			return (List<Book>)getJdbcTemplate()
			.query("select "+BOOK_COLUMN+" from t_book as b,t_book_type as bt where bt.c_type_id=? and c_book_id=b.c_id limit ?,?"
			, new Object[]{type.getId(),start,end},rowMapper);
		}catch(Exception ex){
			ex.printStackTrace();
			return Collections.EMPTY_LIST;
		}
	}

	@Override
	public void save(Book book) {
		book.setId(hiloUtil.getValue());
		getJdbcTemplate().update("insert into t_book ("+BOOK_COLUMN+")" +
				" values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)"
				, new Object[]{
				book.getId(),
				book.getName(),book.getAuthor(),book.getCode(),book.getBookConcern(),book.getPinYin(),book.getFullPinYin()
				,book.getFileName(),book.getReadCount(),book.getDownLoadCount(),book.getStatue(),book.getBookDate(),
				book.getCoverImg(),book.getDesc(),book.getCreatedDate(),book.getCreatedBy()
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
		getJdbcTemplate().update("update t_book set c_name=?,c_author=?,c_code=?," +
				"c_book_concern=?,c_py=?,c_fpy=?,c_fileName=?,c_read_count=?,c_download_count=?," +
				"c_state=?,c_book_date=?,c_cover_img=?,c_desc=?,c_created_date=?,c_created_by=? where c_id=?"
				, new Object[]{
				book.getName(),book.getAuthor(),book.getCode(),book.getBookConcern(),book.getPinYin(),book.getFullPinYin()
				,book.getFileName(),book.getReadCount(),book.getDownLoadCount(),book.getStatue(),book.getBookDate(),
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
}
