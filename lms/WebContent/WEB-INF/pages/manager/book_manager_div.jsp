<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<table width="100%" border="1">
	<thead>
		<tr>
			<th>书名</th>
			<th>作者</th>
			<th>出版社</th>
			<th>日期</th>
			<th>编码</th>
			<th>操作</th>
			<th>下载</th>
			<th>浏览</th>
		</tr>
	</thead>
	<tbody>
	<s:iterator value="page.content" var="b">
		<tr>
			<td><a href="book_book.action?book.id=<s:property value="#b.id"/>" target="_blank"><s:property value="#b.name"/></a></td>
			<td><s:property value="#b.author"/></td>
			<td><s:property value="#b.bookConcern"/></td>
			<td><s:date name="#b.bookDate" format="yyyy-MM-dd"/></td>
			<td><s:property value="#b.code"/></td>
			<td><input type="button" value="删除" onClick="lms_del(<s:property value="id"/>,<s:property value="page.page"/>);"/></td>
			<td>
				<s:if test="#b.fileName!=null&&#b.statue==@lms.model.Book@CAN_DOWNLOAD">
					<a href="download.action?book.id=<s:property value="#b.id"/>" target="_blank">下载</a>
				</s:if>
			</td>
			<td>
				<s:if test="#b.swf!=null">
					<a href="view.action?book.id=<s:property value="#b.id"/>" target="_blank">浏览</a>
				</s:if>
			</td>
		</tr>
		<tr>
			<td>简介</td>
			<td colspan="7"><s:property value="#b.desc"/></td>
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