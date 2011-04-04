<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<table width="100%" border="1">
	<thead>
		<tr>
			<th width="200">书名</th>
			<th width="150">作者</th>
			<th width="100">编码</th>
			<th width="100">译者</th>
			<th width="50">页数</th>
			<th width="50">操作</th>
			<th width="50">下载</th>
			<th width="50">浏览</th>
		</tr>
	</thead>
	<tbody>
	<s:iterator value="page.content">
		<tr>
			<td><a href="_book_book.action?book.id=<s:property value="id"/>" target="_blank"><s:property value="name"/></a></td>
			<td><s:property value="author"/></td>
			<td><s:property value="code"/></td>
			<td><s:property value="translator"/></td>
			<td><s:property value="pages"/></td>
			<td><input type="button" value="删除" onClick="lms_del(<s:property value="id"/>,<s:property value="page.page"/>);"/></td>
			<td>
				<s:if test="fileName!=null&&statue==@lms.model.Book@CAN_DOWNLOAD">
					<a href="download.action?book.id=<s:property value="id"/>" target="_blank">下载</a>
				</s:if>
			</td>
			<td>
				<s:if test="swf!=null">
					<a href="view.action?book.id=<s:property value="id"/>" target="_blank">浏览</a>
				</s:if>
			</td>
		</tr>
		<%-- <tr>
			<td><b>出版社</b></td>
			<td colspan="3"><s:property value="bookConcern"/></td>
			<td><b>出版日期</b></td>
			<td colspan="3"><s:date name="bookDate" format="yyyy-MM-dd"/></td>
		</tr>--%>
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