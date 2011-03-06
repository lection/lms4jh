<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
<title>图书管理</title>
<script type="text/javascript" src="js/lms_page.js"></script>
<script>
$(function(){
	lmsPageUtil.url="_book_list.action";
	lmsPageUtil.find_url="_book_find.action";
	lmsPageUtil.find_form_elements=$("#book_find_form > input,#book_find_form > select");
});
function lms_del(id,page){
	if(confirm("确认删除这本图书吗？")){
		$("#book_list_div").load("_book_del.action?book.id="+id+"&pageNum="+page);
	}
}
</script>
</head>
<body>
	<h4>图书管理</h4>
	<form id="book_find_form" >
	
	书名<input type="text" name="book.name" value="<s:property value="book.name"/>"/>
	分类<select name="typeId">
		<option value="">&nbsp;可以选择图书分类&nbsp;&nbsp;</option>
	<s:iterator value="types" var="t">
		<option <s:if test="#t.id==typeId">selected="selected"</s:if> value="<s:property value="#t.id"/>"><s:property value="#t.name"/></option>
	</s:iterator>
	</select>
	<br/>
	作者<input type="text" name="book.author" value="<s:property value="book.author"/>"/>
	编码<input type="text" name="book.code" value="<s:property value="book.code"/>"/>
	<br/>
	出版社<input type="text" size="35" name="book.bookConcern" value="<s:property value="book.bookConcern"/>"/>
	<br/>
	其他关键字<input type="text" size="32"  name="book.desc" value="<s:property value="book.desc"/>"/>
	<br/>
	<input type="button" value="查询" onClick="lmsPageUtil.find_commit();"/>
	</form>
	<div id="book_list_div">
		<%@include file="book_manager_div.jsp" %>
	</div>
</body>
</html>