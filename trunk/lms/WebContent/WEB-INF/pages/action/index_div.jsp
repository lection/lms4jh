<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<s:set name="lmsUser" value="#session[@lms.model.LmsUser@LOGIN_FLAG]"></s:set>
<s:set name="isLogin" value="#session[@lms.model.LmsUser@LOGIN_FLAG]!=null"></s:set>
<TABLE border="1" style="text-align: center;" cellSpacing=0 cellPadding=0 width=570 
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
        		<td><a class="bookTip" title="
        			编码:<s:property value="code"/><br/>
        			译者:<s:property value="translator"/><br/>
        			页数:<s:property value="pages"/><br/>
        			出版日期:<s:date name="bookDate" format="yyyy-MM-dd"/><br/>
        			简介:<s:property value="desc"/><br/>
        		"><s:property value="#b.name"/></a></td>
        		<td><s:property value="#b.author"/></td>
        		<td><s:property value="#b.bookConcern"/></td>
        		<s:if test="isLogin">
        		<s:if test="#lmsUser.role!=@lms.model.LmsUser@SCHOOLFELLOW">
        		<td>
        			<s:if test="#b.fileName!=null&&#b.statue==@lms.model.Book@CAN_DOWNLOAD">
						<a href="javascript:void(0);" onClick="fn_can_download(<s:property value="#b.id"/>)">下载</a>
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
	<div id="pagination">
            共有 <s:property value="page.pageTotal"/> 页 当前为第 <s:property value="page.page"/> 页 
     &nbsp;&nbsp;此类共有<s:property value="page.total"/>本书<br/>
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
    <input type="text" id="lms_pageNum" size="4"/><input type="button" value="跳转" onClick="lmsPageUtil.go();"/></div>
