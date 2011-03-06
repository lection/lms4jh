<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<%@page import="lms.model.LmsUser"%><html>
<head>
<title>日志浏览</title>
<script type="text/javascript" src="js/lms_page.js"></script>
<script>
$(function(){
	$("#startDate,#endDate").datepicker({
		changeMonth: true,
		changeYear: true,
		dateFormat:'yy-mm-dd'
	});
	lmsPageUtil.url="_log_list.action";
	lmsPageUtil.contentId="log_list_div";
	lmsPageUtil.find_url="_log_find.action";
	lmsPageUtil.find_form_elements=$("#log_find_form > input,#log_find_form > select");
});
</script>
</head>
<body>
	<h4>日志浏览</h4>
	<form id="log_find_form" method="post">
	
	用户名<input type="text" name="userName"/>
	角色<select name="role">
			<option value="">&nbsp;全部&nbsp;</option>
			<option value="<%=LmsUser.MANAGER%>">&nbsp;管理员</option>
			<option value="<%=LmsUser.STUDENT%>">&nbsp;学生&nbsp;</option>
			<option value="<%=LmsUser.SCHOOLFELLOW%>">&nbsp;校友&nbsp;</option>
		</select>
	<br/>
	起始时间<input id="startDate" name="startDate" type="text" size="25"/>
	结束时间<input id="endDate" name="endDate" type="text" size="25"/>
	<br/>
	<input type="button" value="   查询   " onClick="lmsPageUtil.find_commit();"/>
	</form>
	<div id="log_list_div">
		<%@include file="logList_div.jsp" %>
	</div>
</body>
</html>