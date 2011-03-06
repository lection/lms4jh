<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
<title>管理员维护</title>
</head>
<body>
	<s:actionmessage/>
	<a href="manager-page_addmanager.action">添加管理员</a>
	<a href="#">修改个人密码</a>
	<table width="90%" border="1">
		<thead>
			<tr>
				<th>用户名</th>
				<th>姓名</th>
				<th>联系方式</th>
				<%--
				<th>操作</th>--%>
			</tr>
		</thead>
		<tbody>
		<s:iterator value="#request.managerList">
			<tr>
				<td><s:property value="loginName"/></td>
				<td><s:property value="name"/></td>
				<td><s:property value="contact"/></td>
				<%--
				<td>
					<a href="manager_editmanager.action?manager.id=<s:property value="id"/>">编辑</a>
					<a onclick="return confirm('确认删除这个用户吗？');" href="manager_deleteMgr.action?manager.id=<s:property value="id"/>">删除</a>
				</td>--%>
			</tr>
		</s:iterator>
		</tbody>
	</table>
</body>
</html>