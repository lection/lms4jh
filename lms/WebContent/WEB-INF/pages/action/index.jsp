<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<HTML>
<HEAD>
<TITLE>图书啊图书</TITLE>
<LINK href="css/main.css" type=text/css rel=stylesheet>
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" src="js/jquery-ui.js"></script>
<script type="text/javascript" src="js/lms_page.js"></script>
<script>
$(function(){
	lmsPageUtil.url="lms_listBook.action";
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
          <TD align=left height=25><IMG height=11 src="images/icon1.gif" 
            width=31>欢迎访问在线图书馆</TD></TR></TBODY>

</TABLE><BR>
	<div id="book_list_div">
		<%@include file="index_div.jsp" %>
     </div>
     <BR></TD></TR></TBODY>
</TABLE>
</CENTER>
</BODY>
</HTML>

