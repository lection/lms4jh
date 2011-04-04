<%@page import="lms.model.*"%><%@ page language="java" contentType="application/json"%><%
	LmsUser user = (LmsUser)session.getAttribute(LmsUser.LOGIN_FLAG);
	if(user.getRole()==LmsUser.STUDENT){
		Student student = (Student)user;
		out.print("{\"role\":\"student\",\"count\":"+(student.getMaxDownloadCount()-student.getDownloadCount())+"}");
	}else{
		out.print("{\"role\":\"manager\"}");
	}
%>