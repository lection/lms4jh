<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="d"  uri="http://www.opensymphony.com/sitemesh/decorator" %>
<%@ taglib prefix="s"  uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title><d:title default="管理后台"/></title>
<link  rel="stylesheet" href="css/manager.css"/>
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" src="js/jquery-ui.js"></script>
<link  rel="stylesheet" href="css/jquery_css/custom.css"/>
<d:head/>
</head>
<body>
<h1>管理后台</h1>
<hr/>
<div id="manager">
	<div id="manager_left">
		<ul>
			<li><a href="manager_manager.action">管理员维护</a></li>
			<li><a href="type_manager.action">分类维护</a></li>
			<li><a href="book_manager.action">图书管理</a></li>
			<li><a href="book_add.action">添加图书</a></li>
			<li><a href="manager_logout.action">注销</a></li>
		</ul>
	</div>
	<div id="manager_content">
		<p>白驼山壮骨粉</p>
		<d:body/>
	</div>
</div>
</body>
</html>