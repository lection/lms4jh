package lms.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;

public class Book implements Serializable{
	private static final long serialVersionUID = -940118617769052325L;
	public static final int ONLY_READ = 1;
	public static final int CAN_DOWNLOAD = 2;
	
	private int id;
	private String name;
	private String author;
	private String translator;
	private int pages;
	private List<Type> types = new ArrayList<Type>(0);
	private String code;
	private String bookConcern;
	private String pinYin;
	private String fullPinYin;
	private Date bookDate;
	private int readCount;
	private int downLoadCount;
	private int statue;
	private String fileName;
	private String swf;
	private int	segments;
	private int segmentSize;
	private String coverImg;
	private Date createdDate;
	private String createdBy;
	private String desc;
	
	public Book(){}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public List<Type> getTypes() {
		return types;
	}
	public void setTypes(List<Type> types) {
		this.types = types;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getBookConcern() {
		return bookConcern;
	}
	public void setBookConcern(String bookConcern) {
		this.bookConcern = bookConcern;
	}
	public String getPinYin() {
		return pinYin;
	}
	public void setPinYin(String pinYin) {
		this.pinYin = pinYin;
	}
	public String getFullPinYin() {
		return fullPinYin;
	}
	public void setFullPinYin(String fullPinYin) {
		this.fullPinYin = fullPinYin;
	}
	public Date getBookDate() {
		return bookDate;
	}
	public void setBookDate(Date bookDate) {
		this.bookDate = bookDate;
	}
	public int getReadCount() {
		return readCount;
	}
	public void setReadCount(int readCount) {
		this.readCount = readCount;
	}
	public int getDownLoadCount() {
		return downLoadCount;
	}
	public void setDownLoadCount(int downLoadCount) {
		this.downLoadCount = downLoadCount;
	}
	public int getStatue() {
		return statue;
	}
	public void setStatue(int statue) {
		this.statue = statue;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public String getCoverImg() {
		return coverImg;
	}
	public void setCoverImg(String coverImg) {
		this.coverImg = coverImg;
	}
	
	public String getSwf() {
		return swf;
	}

	public void setSwf(String swf) {
		this.swf = swf;
	}

	public String getTranslator() {
		return translator;
	}

	public void setTranslator(String translator) {
		this.translator = translator;
	}

	public String toString(){
		return ReflectionToStringBuilder.toString(this);
	}

	public int getPages() {
		return pages;
	}

	public void setPages(int pages) {
		this.pages = pages;
	}

	public int getSegments() {
		return segments;
	}

	public void setSegments(int segments) {
		this.segments = segments;
	}

	public int getSegmentSize() {
		return segmentSize;
	}

	public void setSegmentSize(int segmentSize) {
		this.segmentSize = segmentSize;
	}
}
