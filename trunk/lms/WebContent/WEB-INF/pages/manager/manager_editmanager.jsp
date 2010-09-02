<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
<title>增加管理员</title>
</head>
<body>
	<h4>增加管理员</h4>
	<form action="manager_updateMgr.action" method="post">
	<input type="hidden" name="manager.id" value="<s:property value="manager.id"/>"/>
	<table>
		<thead></thead>
		<tbody>
			<tr>
				<td>登录名</td>
				<td><input name="manager.loginName" readonly="true" type="text" size="10" value="<s:property value="manager.loginName"/>"/></td>
			</tr>
			<tr>
				<td>密码<br/>(不填写密码不会修改)</td>
				<td><input name="manager.password" type="password" size="15"/></td>
			</tr>
			<tr>
				<td>重复输入密码</td>
				<td><input type="password" size="15"/></td>
			</tr>
			<tr>
				<td>姓名</td>
				<td><input name="manager.name" type="text" size="10" value="<s:property value="manager.name"/>"/></td>
			</tr>
			<tr>
				<td>联系方式</td>
				<td><input name="manager.contact" type="text" size="15" value="<s:property value="manager.contact"/>"/></td>
			</tr>
			<tr>
				<td><input type="submit" value="修改"/></td>
				<td><input type="submit" value="重置"/></td>
			</tr>
		</tbody>
		<tfoot></tfoot>
	</table>
	</form>
</body>
</html>