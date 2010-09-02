<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
<title>图书分类维护</title>
</head>
<body>
	<s:actionmessage/>
	<a href="type_add.action">添加分类</a>
	<table width="70%" border="1">
		<thead>
			<tr>
				<th>分类名</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
		<s:iterator value="#request.list">
			<tr>
				<td><s:property value="name"/></td>
				<td>
					<a href="type_edit.action?type.id=<s:property value="id"/>">编辑</a>
					<a onclick="return confirm('确认删除这个用户吗？');" href="type_delete.action?type.id=<s:property value="id"/>">删除</a>
				</td>
			</tr>
		</s:iterator>
		</tbody>
	</table>
</body>
</html>