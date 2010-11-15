<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
<title>图书管理</title>
</head>
<body>
	<h4>图书管理</h4>
	<form>
	书名<input type="text"/>
	分类<select>
	<s:iterator value="types" var="t">
		<option value="<s:property value="#t.id"/>"><s:property value="#t.name"/></option>
	</s:iterator>
	</select>
	<input type="button" value="查询"/>
	</form>
	<table width="100%" border="1">
		<thead>
			<tr>
				<th>书名</th>
				<th>作者</th>
				<th>出版社</th>
				<th>日期</th>
				<th>编码</th>
				<th>编辑</th>
				<th>下载</th>
				<th>浏览</th>
			</tr>
		</thead>
		<tbody>
		<s:iterator value="page.content" var="b">
			<tr>
				<td><a href="book_book.action?book.id=<s:property value="#b.id"/>"><s:property value="#b.name"/></a></td>
				<td><s:property value="#b.author"/></td>
				<td><s:property value="#b.bookConcern"/></td>
				<td><s:property value="#b.bookDate"/></td>
				<td><s:property value="#b.code"/></td>
				<td><a>编辑</a></td>
				<td><a>下载</a></td>
				<td><a>浏览</a></td>
			</tr>
			<tr>
				<td>简介</td>
				<td colspan="7"><s:property value="#b.desc"/></td>
			</tr>
			</s:iterator>
		</tbody>
	</table>
</body>
</html>