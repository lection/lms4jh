<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<s:set name="lmsUser" value="#session[@lms.model.LmsUser@LOGIN_FLAG]"></s:set>
<s:set name="isLogin" value="#session[@lms.model.LmsUser@LOGIN_FLAG]!=null"></s:set>
<html>
<head>
<title>图书信息---<s:property value="book.name"/></title>
</head>
<body>
	<h4>图书信息:《<s:property value="book.name"/>》</h4>
	<table width="100%" border="1">
		<tbody>
			<tr>
				<td width="30%">书籍名称</td>
				<td width="70%">《<s:property value="book.name"/>》</td>
			</tr>
			<tr>
				<td>作者</td>
				<td><s:property value="book.author"/></td>
			</tr>
			<tr>
				<td>译者</td>
				<td><s:property value="book.translator"/></td>
			</tr>
			<tr>
				<td>书籍编码</td>
				<td><s:property value="book.code"/></td>
			</tr>
			<tr>
				<td>页数</td>
				<td><s:property value="book.pages"/></td>
			</tr>
			<tr>
				<td>出版社</td>
				<td><s:property value="book.bookConcern"/></td>
			</tr>
			<tr>
				<td>出版日期</td>
				<td><s:date name="book.bookDate" format="yyyy-MM-dd"/></td>
			</tr>
			<tr>
				<td>添加日期</td>
				<td><s:date name="book.createdDate" format="yyyy-MM-dd"/></td>
			</tr>
			<tr>
				<td>添加人</td>
				<td><s:property value="book.createdBy"/></td>
			</tr>
			<tr>
				<td>图书分类</td>
				<td>
					<s:iterator value="book.types">
						<label>
							<s:property value="name"/>&nbsp;
						</label>
					</s:iterator>
				</td>
			</tr>
			<tr>
				<td>简介</td>
				<td><s:property value="book.desc"/>&nbsp;</td>
			</tr>
			<tr>
				<td colspan="2">
				<s:if test="isLogin"><s:if test="#lmsUser.role==@lms.model.LmsUser@MANAGER"><a href="_book_edit">编辑</a></s:if></s:if>
				&nbsp;&nbsp;&nbsp;<a href="download.action?book.id=<s:property value="book.id"/>" target="_blank">下载</a>
				&nbsp;&nbsp;&nbsp;<a href="view.action?book.id=<s:property value="book.id"/>" target="_blank">浏览</a>
			</td>
			</tr>
		</tbody>
		<tfoot></tfoot>
	</table>
</body>
</html>