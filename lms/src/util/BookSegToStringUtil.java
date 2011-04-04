package util;

import lms.model.Book;

public class BookSegToStringUtil {
	public static String toLiTags(Book book){
		return toLiTags(book.getPages(), book.getSegments(),book.getSegmentSize());
	}
	
	public static String toLiTags(int pages,int segs,int segSize){
		StringBuilder sb = new StringBuilder();
		int page = 0;
		for(int i=1;i<segs;i++){
			sb.append("<li><a onclick='view("+i+")'>"	+(page+1)+"-"+(page=page+segSize)+"</a></li>");
		}
		sb.append("<li><a  onclick='view("+segs+")'>"	+(page+1)+"-"+pages+"</a></li>");
		return sb.toString();
	}
	
	public static void main(String[] args){
		System.out.println(toLiTags(100,5,20));
	}
}
