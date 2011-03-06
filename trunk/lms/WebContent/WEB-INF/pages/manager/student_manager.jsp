<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<%@page import="lms.model.LmsUser"%>
<%@page import="lms.model.Student"%><html>
<head>
<title>学员管理</title>
<script type="text/javascript" src="js/lms_page.js"></script>
<script>
$(function(){
	lmsPageUtil.url="_stu_list.action";
	lmsPageUtil.find_url="_stu_find.action";
	lmsPageUtil.contentId="student_list_div";
	lmsPageUtil.find_form_elements=$("#student_find_form > input,#student_find_form > select");
});
function lms_del(id,page){
	if(confirm("确认删除这个学生吗？")){
		$("#student_list_div").load("_stu_del.action?student.id="+id+"&pageNum="+page);
	}
}
function lms_change(id,role,page){
	if(confirm("确认该学生身份换转吗？")){
		$("#student_list_div").load("_stu_change.action?student.role="+role+"&student.id="+id+"&pageNum="+page);
	}
}
</script>
</head>
<body>
	<h4>学员管理</h4><a href="stu_add.action">添加新学员</a>
	<form id="student_find_form" method="post">
	
	用户名<input type="text" name="student.loginName"/>
	姓名<input type="text" name="student.name"/>
	<br/>
	性别<select name="student.gender">
			<option value="">&nbsp;全部&nbsp;</option>
			<option value="0">&nbsp;男&nbsp;</option>
			<option value="1">&nbsp;女&nbsp;</option>
		</select>
	状态<select name="student.role">
			<option value="">&nbsp;全部&nbsp;</option>
			<option value="<%=LmsUser.AUDITING%>">&nbsp;待审核&nbsp;</option>
			<option value="<%=LmsUser.STUDENT%>">&nbsp;学生&nbsp;</option>
			<option value="<%=LmsUser.SCHOOLFELLOW%>">&nbsp;校友&nbsp;</option>
		</select>
	入学年份<select name="student.grade">
						<option value="">全部</option>
						<option value="2005">2005</option>
						<option value="2006">2006</option>
						<option value="2007">2007</option>
						<option value="2008">2008</option>
						<option value="2009">2009</option>
						<option value="2010">2010</option>
						<option value="2011">2011</option>
						<option value="2012">2012</option>
						<option value="2013">2013</option>
						<option value="2014">2014</option>
		</select>
	<br/>
	<input type="button" value="   查询   " onClick="lmsPageUtil.find_commit();"/>
	</form>
	<div id="student_list_div">
		<%@include file="student_manager_div.jsp" %>
	</div>
</body>
</html>