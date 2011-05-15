<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head>
<title>用户注册</title>
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" src="js/jquery-ui.js"></script>
<link  rel="stylesheet" href="css/jquery_css/custom.css"/>
<link rel="stylesheet" type="text/css" href="css/main2.css"/>
<script type="text/javascript">
$(function() {
	$( "#datepicker" ).datepicker({
		changeMonth: true,
		changeYear: true,
		dateFormat:'yy-mm-dd'
	});
	$("#add_student_form").submit(function(){
		var stu_inputs = $(this).find("input[type='text']");
		var submit_flag = true;
		stu_inputs.each(function(){
			var input_val = $(this).val();
			if(!input_val||$.trim(input_val).length==0){
				alert($(this).parent().prev().html()+"  不允许为空");
				submit_flag = false;
			}
		});
		return submit_flag;
	});
});
</script>
</head>
<body>

<div id="main">
		<div id="header">
			<div id="title">
			  <div class="title"><a href="<%=request.getContextPath() %>">中华圣道神学院图书馆</a></div>
			  <div class="subtitle">English Name</div>
			</div>
			
		</div>
		<div id="content" style="width:748px;">
		  <h1>用户注册</h1>
		  <s:actionmessage/>
			<s:fielderror/>
			<s:actionerror/>
			<form id="add_student_form" action="register.action" method="post">
			<table border="1">
				<thead></thead>
				<tbody>
					<tr>
						<td width="35%">登录名</td>
						<td width="65%"><input name="student.loginName" type="text" size="30"/></td>
					</tr>
					<tr>
						<td width="35%">密码</td>
						<td width="65%"><input name="student.password" type="password" size="30"/></td>
					</tr>
					<tr>
						<td width="35%">姓名</td>
						<td width="65%"><input name="student.name" type="text" size="30"/></td>
					</tr>
					<tr>
						<td width="35%">性别</td>
						<td width="65%"><label><input type="radio" name="student.gender" checked="checked" value="0"/>男</label>&nbsp;&nbsp;
						<label><input type="radio" name="student.gender" value="1"/>女</label></td>
					</tr>
					<tr>
						<td width="35%">入学年份</td>
						<td width="65%">
							<select name="student.grade">
								<option value="2005">2005</option>
								<option value="2006">2006</option>
								<option value="2007">2007</option>
								<option value="2008">2008</option>
								<option value="2009">2009</option>
								<option value="2010" selected="selected">2010</option>
								<option value="2011">2011</option>
								<option value="2012">2012</option>
								<option value="2013">2013</option>
								<option value="2014">2014</option>
							</select>
						</td>
					</tr>
					<tr>
						<td width="35%">生日</td>
						<td width="65%"><input id="datepicker" name="student.birthday" type="text" size="30" readonly="readonly"/></td>
					</tr>
					<tr>
						<td width="35%">注册码<img src="authCode.jsp"/></td>
						<td width="65%"><input type="text" name="lms_authCode"/></td>
					</tr>
					<tr>
						<td><input type="submit" value="注册"/></td>
						<td><input type="reset" value="重置"/></td>
					</tr>
				</tbody>
				<tfoot></tfoot>
			</table>
			</form>
		</div>
	</div>

</body>
</html>