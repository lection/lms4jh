<%@page import="util.PinyinUtil"%><%@ page language="java" contentType="application/json"%><%
	request.setCharacterEncoding("ISO-8859-1");
	String s = request.getParameter("s");
	if(s!=null)
		s=new String(s.getBytes("ISO-8859-1"),"UTF-8");
	String[] result = PinyinUtil.converString(s);
%>{"pinyin":"<%=result[0]%>","py":"<%=result[1]%>"}