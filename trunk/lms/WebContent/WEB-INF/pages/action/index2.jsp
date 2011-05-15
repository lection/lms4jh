<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<s:set name="lmsUser" value="#session[@lms.model.LmsUser@LOGIN_FLAG]"></s:set>
<s:set name="isLogin" value="#session[@lms.model.LmsUser@LOGIN_FLAG]!=null"></s:set>
<!DOCTYPE html>
<html>
<head>
<title>中华圣道神学院图书馆</title>
<link rel="stylesheet" type="text/css" href="css/main2.css"/>
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" src="js/jquery-ui.js"></script>
<script type="text/javascript" src="js/lms_page.js"></script>
<script>
$(function(){
	lmsPageUtil.url="lms_listBook.action";
	lmsPageUtil.find_url="lms_findBook.action";
	lmsPageUtil.find_form_elements=$("#book_find_form > input,#book_find_form > select");
	$("#find_book_button").click(function(){lmsPageUtil.find_commit();});
});
function fn_findByType(id){
	lmsPageUtil.url="lms_listBookByType.action";
	lmsPageUtil.param={typeId:id};
	lmsPageUtil.go(1);
}
function fn_reset(){
	lmsPageUtil.url="lms_listBook.action";
	lmsPageUtil.param={};
	lmsPageUtil.go(1);
}
function fn_can_download(bid){
	$.ajax({
		"url":"canDownload.jsp",
		"async":"false",
		"dataType":"json",
		"cache":false,
		"success":function(data){
			if("student"==data.role){
				if(data.count>0){
					if(confirm("您还有"+data.count+"次下载机会，确认下载吗？")){
						window.location="download.action?book.id="+bid;
					}
				}else{
					alert("您本月已经不允许下载了");
				}
			}else{
				window.location="download.action?book.id="+bid;
			}
		}
	});
}
</script>
</head>
<body>
	<div id="main">
		<div id="header">
			<div id="title">
			  <div class="title"><a href="<%=request.getContextPath() %>">中华圣道神学院图书馆</a></div>
			  <div class="subtitle">English Name</div>
			</div>
			
		</div>
		<div id="lpanel">
		  <div class="heading">欢迎访问</div>
		  		<s:if test="isLogin">
		  		欢迎访问在线图书馆<br/>
            		用户:<s:property value="#lmsUser.getUserName()"/>&nbsp;&nbsp;
            		<s:property value="@util.Role2StrUtil@toString(#lmsUser.getRole())"/>&nbsp;&nbsp;
            		<a href="userLogout.action">退出</a>
            	</s:if>
            	<s:else>
            	<form id="login_form" action="userLogin.action" method="post">
            		用户名<br/><input name="student.loginName" type="text"/><br/>
            		密码<br/><input name="student.password" type="password"/><br/>
            		注册码<img src="authCode.jsp"/><br/>
            		<input type="text" name="lms_authCode"/><br/>
            		<a onclick="$('#login_form').submit();">登录</a>&nbsp;&nbsp;<a href="user_register.jsp">注册</a>
            	</form>
            	</s:else>
		  <div class="heading">图书分类</div>
		  <a onClick="fn_reset();">全部图书</a><br/>
		  <s:iterator value="listType">
		  	<a onClick='fn_findByType(<s:property value="id"/>);'><s:property value="name"/></a><br/>
		  </s:iterator>
		  <div class="heading">搜索查询</div>
		  	<form id="book_find_form" >
			书名<br/>
			<input type="text" name="book.name" value="<s:property value="book.name"/>"/><br/>
			分类<br/>
			<select name="typeId">
				<option value="">&nbsp;可以选择图书分类&nbsp;&nbsp;</option>
				<s:iterator value="listType">
					<option <s:if test="id==typeId">selected="selected"</s:if> value="<s:property value="id"/>"><s:property value="name"/></option>
				</s:iterator>
			</select><br/>
			作者<br/>
			<input type="text" name="book.author" value="<s:property value="book.author"/>"/><br/>
			出版社<br/>
			<input type="text" size="15" name="book.bookConcern" value="<s:property value="book.bookConcern"/>"/><br/>
			其他关键字<br/>
			<input type="text" size="12"  name="book.desc" value="<s:property value="book.desc"/>"/><br/>
			<a id="find_book_button">查询</a>
			</form>
		</div>
		<div id="content">
		  <h1>图书馆书籍列表</h1>
		  <div id="book_list_div">
			<%@include file="index_div.jsp" %>
	     </div>
		</div>
		<div id="footer">
		  <div>中华圣道神学院图书馆&nbsp;&nbsp;<a href="manager_manager.action">管理后台</a></div>
		  <div>English Name</div>
		</div>
		<div>&nbsp;</div>
	</div>
</body>
</html>