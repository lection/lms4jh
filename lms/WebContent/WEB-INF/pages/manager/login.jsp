<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>管理后台登录</title>
</head>
<body>
<s:actionerror/>
<s:actionmessage/>
<form action="manager_auth.action" method="post">
	<table>
		<tbody>
			<tr>
				<td>用户名</td>
				<td><input name="manager.loginName" type="text"/></td>
			</tr>		
			<tr>
				<td>密码</td>
				<td><input name="manager.password" type="password"/></td>
			</tr>		
			<tr>
				<td>验证码</td>
				<td></td>
			</tr>		
			<tr>
				<td colspan="2">
					<table>
						<tr>
							<td><input type="submit"/></td>
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