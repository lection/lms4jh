<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>

<TABLE border="1" style="text-align: center;" cellSpacing=0 cellPadding=0 width=590 
      border=0>
      	<thead>
      		<tr style="font-weight: bold">
      			<td width="23%">书名</td>
      			<td width="22%">作者</td>
      			<td width="30%">出版社</td>
      			<s:if test="isLogin">
      				<td 
      				<s:if test="#lmsUser.role!=@lms.model.LmsUser@SCHOOLFELLOW">
      				colspan="2"
      				</s:if>
      				>操作</td>
      			</s:if>
      		</tr>
      	</thead>
        <TBODY>
        <s:iterator value="page.content" var="b">
        	<tr>
        		<td><s:property value="#b.name"/></td>
        		<td><s:property value="#b.author"/></td>
        		<td><s:property value="#b.bookConcern"/></td>
        		<s:if test="isLogin">
        		<s:if test="#lmsUser.role!=@lms.model.LmsUser@SCHOOLFELLOW">
        		<td>
        			<s:if test="#b.fileName!=null&&#b.statue==@lms.model.Book@CAN_DOWNLOAD">
						<a href="download.action?book.id=<s:property value="#b.id"/>" target="_blank">下载</a>
					</s:if>
				</td>
				</s:if>
				<td>
					<s:if test="#b.swf!=null">
						<a href="view.action?book.id=<s:property value="#b.id"/>" target="_blank">浏览</a>
					</s:if>
        		</td>
        		</s:if>
        	</tr>	
       	</s:iterator>
        </TBODY>
	</TABLE>
      <TABLE cellSpacing=0 cellPadding=5 width="100%" border=0>
        <TBODY>
           <TR align="center">
           <TD height=20>
	<s:if test="page.page!=1"><a onClick="lmsPageUtil.go(1)">首页</a></s:if>
    <s:else>首页 </s:else>
          | 
    <s:if test="page.page!=1"><a onClick="lmsPageUtil.go(<s:property value="page.page-1"/>)">上一页</a></s:if>
    <s:else>上一页 </s:else>
          | 
    <s:if test="page.page!=page.pageTotal"><a onClick="lmsPageUtil.go(<s:property value="page.page+1"/>)">下一页</a></s:if>
    <s:else>下一页 </s:else>
            
            | 
    <s:if test="page.page!=page.pageTotal"><a onClick="lmsPageUtil.go(<s:property value="page.pageTotal"/>)">末页</a></s:if>
    <s:else>末页 </s:else>
            共有 <s:property value="page.pageTotal"/> 页 当前为第 <s:property value="page.page"/> 页 
     <input type="text" id="lms_pageNum" size="4"/><input type="button" value="跳转" onClick="lmsPageUtil.go();"/>
     &nbsp;&nbsp;此类共有<s:property value="page.total"/>本书
     </TD></TR></TBODY></TABLE>