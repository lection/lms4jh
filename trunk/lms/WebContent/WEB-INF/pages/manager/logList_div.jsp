<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<table width="100%" border="1">
	<thead>
		<tr>
			<th width="10%">登录名</th>
			<th width="10%">角色</th>
			<th width="10%">IP地址</th>
			<th width="30%">时间</th>
			<th width="40%">操作</th>
		</tr>
	</thead>
	<tbody>
	<s:iterator value="page.content">
		<tr>
			<td><s:property value="loginName"/></td>
			<td>
				<s:if test="role==@lms.model.LmsUser@MANAGER">管理员</s:if>
				<s:elseif test="role==@lms.model.LmsUser@STUDENT">学生</s:elseif>
				<s:else>校友</s:else>
			</td>
			<td><s:property value="ip"/></td>
			<td><s:date name="date" format="yyyy-MM-dd HH:mm:ss"/></td>
			<td><s:property value="handle"/></td>
		</tr>
	</s:iterator>
	</tbody>
</table>
<div>
	共&nbsp;<s:property value="page.total"/>&nbsp;条记录 &nbsp;共&nbsp;<s:property value="page.pageTotal"/>&nbsp;页
	&nbsp;第&nbsp;<s:property value="page.page"/>&nbsp;页&nbsp;
	<s:if test="page.page!=1">
			<input type="button" value="上一页" onClick="lmsPageUtil.go(<s:property value="page.page-1"/>);"/>
	</s:if>
	<s:else>
		上一页
	</s:else>
	<s:if test="page.page!=page.pageTotal">
			<input type="button" value="下一页" onClick="lmsPageUtil.go(<s:property value="page.page+1"/>);"/>
	</s:if>
	<s:else>
		下一页
	</s:else>
	<input type="text" id="lms_pageNum" size="4"/><input type="button" value="跳转" onClick="lmsPageUtil.go($('#lms_pageNum').val());"/>
</div>