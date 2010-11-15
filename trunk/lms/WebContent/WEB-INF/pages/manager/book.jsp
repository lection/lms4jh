<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
<title>图书信息---<s:property value="book.name"/></title>
</head>
<body>
	<h4>图书信息:《<s:property value="book.name"/>》</h4>
	<table width="80%">
		<thead></thead>
		<tbody>
			<tr>
				<td width="30%">书籍名称</td>
				<td width="70%">《<s:property value="book.name"/>》</td>
			</tr>
			<tr>
				<td>作者</td>
				<td><s:property value="book.author"/></td>
			</tr>
			<tr>
				<td>书籍编码</td>
				<td><s:property value="book.code"/></td>
			</tr>
			<tr>
				<td>出版社</td>
				<td><s:property value="book.bookConcern"/></td>
			</tr>
			<tr>
				<td>出版日期</td>
				<td><s:property value="book.bookDate"/></td>
			</tr>
			<tr>
				<td>图书分类</td>
				<td>
					<s:iterator value="book.types" var="t">
						<label>
							<s:property value="#t.name"/>&nbsp;
						</label>
					</s:iterator>
				</td>
			</tr>
		</tbody>
		<tfoot></tfoot>
	</table>
</body>
</html>