<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
<title>编辑学员  <s:property value="student.loginName"/>/<s:property value="student.name"/></title>
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
		if(isNaN($(this).find("input[name='student.maxDownloadCount']").val())){
			alert("下载上限必须为数字");
			submit_flag = false;
		}
		if(isNaN($(this).find("input[name='student.downloadCount']").val())){
			alert("本月下载次数必须为数字");
			submit_flag = false;
		}
		return submit_flag;
	});
	$("grade option").each(function(){
		var gopt = $(this);
		if(gopt.val()=='<s:property value="student.grade"/>'){
			gopt.attr("selected","selected");
		}
	});
});
</script>
</head>
<body>
	<h4>编辑学员  <s:property value="student.loginName"/>/<s:property value="student.name"/></h4>
	<s:actionmessage/>
	<s:fielderror/>
	<s:actionerror/>
	<form id="edit_student_form" action="stu_update.action" method="post">
	<input type="hidden" name="student.id" value="<s:property value="student.id"/>"/>
	<table width="100%">
		<thead></thead>
		<tbody>
			<tr>
				<td width="35%">登录名</td>
				<td width="65%"><s:property value="student.loginName"/></td>
			</tr>
			<tr>
				<td width="35%">密码</td>
				<td width="65%"><input name="student.password" type="password" size="30" value="<s:property value="student.password"/>"/></td>
			</tr>
			<tr>
				<td width="35%">姓名</td>
				<td width="65%"><input name="student.name" type="text" size="30" value="<s:property value="student.name"/>"/></td>
			</tr>
			<tr>
				<td width="35%">性别</td>
				<td width="65%"><label><input type="radio" name="student.gender" <s:property value="student.gender==0?'checked':''"/> value="0"/>男</label>&nbsp;&nbsp;
				<label><input type="radio" name="student.gender" <s:property value="student.gender==1?'checked':''"/> value="1"/>女</label></td>
			</tr>
			<tr>
				<td width="35%">入学年份</td>
				<td width="65%">
					<select id="grade" name="student.grade">
						<option value="2005">2005</option>
						<option value="2006">2006</option>
						<option value="2007">2007</option>
						<option value="2008">2008</option>
						<option value="2009">2009</option>
						<option value="2010">2010</option>
						<option value="2011">2011</option>
						<option value="2012">2012</option>
						<option value="2013">2013</option>
						<option value="2014">2014</option>
					</select>
				</td>
			</tr>
			<tr>
				<td width="35%">生日</td>
				<td width="65%"><input id="datepicker" value="<s:date name="student.birthday" format="yyyy-MM-dd"/>" name="student.birthday" type="text" size="30" readonly="readonly"/></td>
			</tr>
			<tr>
				<td width="35%">每月下载上限</td>
				<td width="65%"><input name="student.maxDownloadCount" type="text" size="30" value="<s:property value="student.maxDownloadCount"/>"/></td>
			</tr>
			<tr>
				<td width="35%">本月下载次数</td>
				<td width="65%"><input name="student.downloadCount" type="text" size="30" value="<s:property value="student.downloadCount"/>"/></td>
			</tr>
			<tr>
				<td><input type="submit" value="添加"/></td>
				<td><input type="reset" value="重置"/></td>
			</tr>
		</tbody>
		<tfoot></tfoot>
	</table>
	</form>
</body>
</html>