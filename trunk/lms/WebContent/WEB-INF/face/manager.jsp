<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="d"
	uri="http://www.opensymphony.com/sitemesh/decorator"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<%@page import="java.net.InetAddress"%>
<html>
<head>
<title><d:title default="管理后台" /></title>
<link rel="stylesheet" href="css/manager2.css" />
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" src="js/jquery-ui.js"></script>
<link rel="stylesheet" href="css/jquery_css/custom.css" />
<d:head />
</head>
<body>
<hr />

<div id="top">
<div id="logo"><font color="green" style="font-size:xx-large">管理后台</font></div>
	<div id="xx">
	<s:if test="#session[@lms.model.LmsUser@LOGIN_FLAG]!=null">
		<div id="hy">您好，<s:property value="#session[@lms.model.LmsUser@LOGIN_FLAG].name"/> [ <a href="manager_logout.action">退出</a> ]</div>
	</s:if>
	</div>
</div>
<hr style="border: 1px solid green;"/>
<div id="zhong">
<div id="zb">
<div id="sys"><img src="image/icon01.gif" width="11" height="6" />管理功能</div>
<div id="leftMenu">
<div id="leftMenuPic"><img src="image/mun_bg.gif" /></div>
<ul>
	<li><a href="manager_manager.action">管理员维护</a></li>
	<li><a href="type_manager.action">分类维护</a></li>
	<li><a href="book_manager.action">图书管理</a></li>
	<li><a href="book_add.action">添加图书</a></li>
	<li><a href="stu_manager.action">学员管理</a></li>
	<li><a href="logList.action">查看日志</a></li>
	<li><a href="manager_logout.action">退出</a></li>
</ul>
</div>
<div id="ot"></div>
</div>
<div id="content"><d:body /></div>
</div>
</body>
</html>