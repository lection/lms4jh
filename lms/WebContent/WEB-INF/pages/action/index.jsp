<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<s:set name="lmsUser" value="#session[@lms.model.LmsUser@LOGIN_FLAG]"></s:set>
<s:set name="isLogin" value="#session[@lms.model.LmsUser@LOGIN_FLAG]!=null"></s:set>
<HTML>
<HEAD>
<TITLE>图书馆</TITLE>
<LINK href="css/main.css" type=text/css rel=stylesheet>
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" src="js/jquery-ui.js"></script>
<script type="text/javascript" src="js/lms_page.js"></script>
<script>
$(function(){
	lmsPageUtil.url="lms_listBook.action";
	lmsPageUtil.find_url="lms_findBook.action";
	lmsPageUtil.find_form_elements=$("#book_find_form > input,#book_find_form > select");
});
function fn_findByType(id){
	lmsPageUtil.url="lms_listBookByType.action";
	lmsPageUtil.param={typeId:id};
	lmsPageUtil.go(1);
}
function fn_reset(){
	lmsPageUtil.url="lms_listBook.action";
	lmsPageUtil.go(1);
}
</script>
</HEAD>
<BODY>
<CENTER>
<TABLE height=15 cellSpacing=0 cellPadding=0 width=778 border=0>
  <TBODY>
  <TR>
    <TD style="FONT-SIZE: 10px; LINE-HEIGHT: 10px" 
    background=images/bnbg1.gif height=15></TD></TR></TBODY>
</TABLE>
<TABLE cellSpacing=0 cellPadding=0 width=778 bgColor=#ffffff border=0>
  <TBODY>
  <TR>
    <TD vAlign=top width=168 height=400>
      <TABLE class=center height="100%" cellSpacing=0 cellPadding=0 width=168 
      bgColor=#ffffff background=images/leftbg.gif border=0>
        <TBODY>
        <TR>

          <TD class=lefttd align="center" bgColor=#ffffff height=50>
          	<a href="manager_login.action" style="border: none;"><IMG style="border: none;" height=40 src="images/design.jpg" width=155></a>
          </TD></TR>


        <TR>
          <TD class=lefttd vAlign=top align=middle><BR>
            <TABLE class=bor style="TEXT-ALIGN: left" cellSpacing=0 
            cellPadding=0 width=140 bgColor=#ffffff border=1>
              <TBODY>
              <TR>
                <TD height=25><IMG height=11 src="images/icon1.gif" 
                  width=31>&nbsp;&nbsp;&nbsp;&nbsp;
                 	<a onClick="fn_reset();">全部图书</a> 
                 </TD>
              </TR>
              <s:iterator value="listType" var="t">
              <TR>
                <TD height=25><IMG height=11 src="images/icon1.gif" 
                  width=31>&nbsp;&nbsp;&nbsp;&nbsp;
                 	<a onClick="fn_findByType(<s:property value="#t.id"/>);"><s:property value="#t.name"/></a> 
                 </TD>
              </TR>
              </s:iterator>
              </TBODY></TABLE></TD></TR></TBODY></TABLE></TD>
                <TD vAlign=top align=middle width=610>


<TABLE cellSpacing=0 cellPadding=0 width="98%" 
      background=images/top01.gif border=0>
        <TBODY>
        <TR>
          <TD align=left height=25><IMG height=11 src="images/icon1.gif" width="31">
          		欢迎访问在线图书馆&nbsp;&nbsp;&nbsp;
            	<s:if test="isLogin">
            		用户:<s:property value="#lmsUser.getUserName()"/>&nbsp;&nbsp;
            		<s:property value="@util.Role2StrUtil@toString(#lmsUser.getRole())"/>&nbsp;&nbsp;
            		<a href="userLogout.action">退出</a>
            	</s:if>
            	<s:else>
            		<a href="user_login.jsp">登录</a>&nbsp;&nbsp;<a href="user_register.jsp">注册</a>
            	</s:else>
            </TD></TR></TBODY>

</TABLE>
<form id="book_find_form" >
	书名<input type="text" name="book.name" value="<s:property value="book.name"/>"/>
	分类<select name="typeId">
		<option value="">&nbsp;可以选择图书分类&nbsp;&nbsp;</option>
	<s:iterator value="types" var="t">
		<option <s:if test="#t.id==typeId">selected="selected"</s:if> value="<s:property value="#t.id"/>"><s:property value="#t.name"/></option>
	</s:iterator>
	</select>
	作者<input type="text" name="book.author" value="<s:property value="book.author"/>"/>
	<br/>
	出版社<input type="text" size="15" name="book.bookConcern" value="<s:property value="book.bookConcern"/>"/>
	其他关键字<input type="text" size="12"  name="book.desc" value="<s:property value="book.desc"/>"/>
	<input type="button" value="查询" onClick="lmsPageUtil.find_commit();"/>
	</form>
	<div id="book_list_div">
		<%@include file="index_div.jsp" %>
     </div>
     <BR></TD></TR></TBODY>
</TABLE>
</CENTER>
</BODY>
</HTML>

