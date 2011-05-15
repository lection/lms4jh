<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
<title>图书传递</title>
<script type="text/javascript" src="js/lms_page.js"></script>
<script type="text/javascript">
$(function(){
	$("#pass_form").submit(function(){
		$( "#dialog-message" ).dialog({
			modal: true,
			width: 600,
			height:400
		});
		upMsg();
	});
});
function upMsg(){
	$.getJSON("upMsg.action",function(data){
		var msgDiv = $("#msgDiv");
		for(var i=0;i<data.length;i++){
			if(data[i].status){
				msgDiv.append(data[i].message+"<br/>");
				if(data[i].status == "finished"){
					msgDiv.append("<a href='bookpass_manager.action'>继续操作</a>");
					return;
				}
				msgDiv.scrollTop=(msgDiv.scrollHeight-msgDiv.clientHeight);
			}
		}
		setTimeout("upMsg()",700);
	});
};
</script>
</head>
<body>
	<div id="dialog-message" title="图书传输" style="display: none;">
		<div id="msgDiv" style="width: 95%;height:95%;">
			图书正在传输处理，请勿关闭进行其他操作。
		</div>
	</div>
	<iframe width="0" height="0" name="book_pass_iframe" style="border: 0"></iframe>
	<h4>图书接受</h4>
	图书接受模块状态:
	<s:if test="receiver.flag">
		<font color="red">开启 监听:<s:property value="receiver.port"/>端口</font>
		<a href="bookpass_shutdown.action">点击关闭接受模块</a>
	</s:if>
	<s:else>
		 <font color="red">关闭</font>
		 <a href="bookpass_startup.action">点击开启接受模块</a> 
	</s:else>
	<br/>
	<br/>
	<br/>
	<h4>图书发送</h4>
	<form id="pass_form" action="bookpass_send.action" target="book_pass_iframe">
		IP地址<input type="text" name="ip" id="ip"/><br/>
		端口号<input type="text" name="port" id="port"/><br/>
		传送结束删除文件:<input type="checkbox" name="flag" value="true"/><br/>
		<input type="submit" value="传递"/><input type="reset"/>
	</form>
</body>
</html>