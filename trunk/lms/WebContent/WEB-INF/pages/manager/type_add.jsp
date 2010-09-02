<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
<title>增加图书分类</title>
</head>
<body>
	<h4>增加图书分类</h4>
	<form action="type_save.action" method="post">
	<table>
		<thead></thead>
		<tbody>
			<tr>
				<td>分类名称</td>
				<td><input name="type.name" type="text" size="10"/></td>
			</tr>
			<tr>
				<td>分类介绍</td>
				<td><textarea name="type.desc"></textarea></td>
			</tr>
			<tr>
				<td><input type="submit" value="添加"/></td>
				<td><input type="submit" value="重置"/></td>
			</tr>
		</tbody>
		<tfoot></tfoot>
	</table>
	</form>
</body>
</html>