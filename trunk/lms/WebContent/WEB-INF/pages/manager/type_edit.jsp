<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
<title>增加图书分类</title>
</head>
<body>
	<h4>增加图书分类</h4>
	<form action="type_update.action" method="post">
	<input name="type.id" type="hidden" value="<s:property value="type.id"/>"/>
	<table>
		<thead></thead>
		<tbody>
			<tr>
				<td>分类名称</td>
				<td><input name="type.name" value="<s:property value="type.name"/>" type="text" size="10"/></td>
			</tr>
			<tr>
				<td>分类介绍</td>
				<td><textarea name="type.desc"><s:property value="type.desc"/></textarea></td>
			</tr>
			<tr>
				<td><input type="submit" value="修改"/></td>
				<td><input type="reset" value="重置"/></td>
			</tr>
		</tbody>
		<tfoot></tfoot>
	</table>
	</form>
</body>
</html>