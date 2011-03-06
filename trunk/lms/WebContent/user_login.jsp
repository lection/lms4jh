<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>图书馆用户登录</title>
</head>
<body>
<s:actionerror/>
<s:actionmessage/>
<form action="userLogin.action" method="post">
	<table>
		<tbody>
			<tr>
				<td>用户名</td>
				<td><input name="student.loginName" type="text"/></td>
			</tr>		
			<tr>
				<td>密码</td>
				<td><input name="student.password" type="password"/></td>
			</tr>		
			<tr>
				<td width="35%">注册码<img src="authCode.jsp"/></td>
				<td width="65%"><input type="text" name="lms_authCode"/></td>
			</tr>	
			<tr>
				<td colspan="2">
					<table>
						<tr>
							<td><input type="submit" value="登录"/></td>
							<td><input type="reset"/></td>
						</tr>
					</table>
				</td>
			</tr>		
		</tbody>
	</table>
</form>
</body>
</html>