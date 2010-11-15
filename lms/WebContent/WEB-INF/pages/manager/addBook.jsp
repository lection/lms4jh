<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
<title>添加图书</title>
</head>
<body>
	<h4>添加图书</h4>
	<form action="bookfile_save.action" method="post">
	<table width="80%">
		<thead></thead>
		<tbody>
			<tr>
				<td width="30%">书籍名称</td>
				<td width="70%"><input name="book.name" type="text" size="30"/></td>
			</tr>
			<tr>
				<td>作者</td>
				<td><input name="book.author" type="text" size="30"/></td>
			</tr>
			<tr>
				<td>书籍编码</td>
				<td><input name="book.code" type="text" size="30"/></td>
			</tr>
			<tr>
				<td>出版社</td>
				<td><input name="book.bookConcern" type="text" size="30"/></td>
			</tr>
			<tr>
				<td>出版日期</td>
				<td><input name="book.bookDate" type="text" size="25"/></td>
			</tr>
			<tr>
				<td>是否允许下载</td>
				<td>
					<label>是<input type="radio" name="book.statue" checked="checked" value="<s:property value="@lms.model.Book.ONLY_READ"/>"/></label>
					<label>否<input type="radio" name="book.statue" value="<s:property value="@lms.model.Book.CAN_DOWNLOAD"/>"/></label>
				</td>
			</tr>
			<tr>
				<td>图书分类</td>
				<td>
					<s:iterator value="types" var="t">
						<label>
							<s:property value="#t.name"/>
							<input type="checkbox" value="<s:property value="#t.id"/>" name="types"/>
						</label>
					</s:iterator>
				</td>
			</tr>
			<tr>
				<td>上传图书文件</td>
				<td><input name="bookFile" type="file" size="30"/></td>
			</tr>
			<tr>
				<td>上传SWF文件</td>
				<td><input name="swf" type="file" size="30"/></td>
			</tr>
			<tr>
				<td>上传图书封面</td>
				<td><input name="coverImg" type="file" size="30"/></td>
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