<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<%@page import="lms.model.LmsUser"%><table width="100%" border="1">
	<thead>
		<tr>
			<th width="100">登录名</th>
			<th width="100">姓名</th>
			<th width="50">性别</th>
			<th width="100">入学年份</th>
			<th width="100">生日</th>
			<th width="100">状态</th>
			<th width="150">操作</th>
		</tr>
	</thead>
	<tbody>
	<s:iterator value="page.content">
		<tr>
			<td><a href="stu_edit.action?student.id=<s:property value="id"/>"><s:property value="loginName"/></a></td>
			<td><s:property value="name"/></td>
			<td><s:property value="gender==0?'男':'女'"/></td>
			<td><s:property value="grade"/></td>
			<td><s:date name="birthday" format="yyyy-MM-dd"/></td>
			<td><s:property value="@util.Role2StrUtil@toString(role)"/></td>
			<td>
			<input type="button" value="删除" onClick="lms_del(<s:property value="id"/>,<s:property value="page.page"/>);"/>
			<s:if test="role==@lms.model.LmsUser@AUDITING">
				<input type="button" value="通过审核" onClick="lms_change(<s:property value="id"/>,<%=LmsUser.STUDENT%>,<s:property value="page.page"/>);"/>
			</s:if>
			<s:elseif test="role==@lms.model.LmsUser@STUDENT">
				<input type="button" value="转为校友" onClick="lms_change(<s:property value="id"/>,<%=LmsUser.SCHOOLFELLOW%>,<s:property value="page.page"/>);"/>
			</s:elseif>
			<s:else>
				<input type="button" value="转为在校学生" onClick="lms_change(<s:property value="id"/>,<%=LmsUser.STUDENT%>,<s:property value="page.page"/>);"/>
			</s:else>
			</td>
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
	<input type="text" id="lms_pageNum" size="4"/><input type="button" value="跳转" onClick="lmsPageUtil.go();"/>
</div>