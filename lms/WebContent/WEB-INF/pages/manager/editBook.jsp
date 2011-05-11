<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<s:set name="lmsUser" value="#session[@lms.model.LmsUser@LOGIN_FLAG]"></s:set>
<s:set name="isLogin" value="#session[@lms.model.LmsUser@LOGIN_FLAG]!=null"></s:set>
<html>
<head>
<title>编辑图书---<s:property value="book.name"/></title>
<link  rel="stylesheet" href="css/manager.css"/>
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" src="js/jquery-ui.js"></script>
<link  rel="stylesheet" href="css/jquery_css/custom.css"/>
<style type="text/css">
#add_book_form label{
	border-right:1px solid gray;
	border-bottom:1px solid gray;
	padding: 3px;
	margin: 2px;
}
#add_book_form tr{
	height: 25px;
}
</style>
<SCRIPT type="text/javascript">
	$(function() {
		$( "#datepicker" ).datepicker({
			changeMonth: true,
			changeYear: true,
			dateFormat:'yy-mm-dd'
		});
		$("#book_file").change(function(){
			var book_name = $("#book_name");
			var book_name_val = book_name.val();
			if(!book_name_val||$.trim(book_name_val).length==0){
				var input_file_name = $(this).val();
				book_name.val(input_file_name.substring(input_file_name.lastIndexOf("\\"),input_file_name.lastIndexOf(".")));
				book_name.change();
			}
		});
		$("#add_book_form").submit(function(){
			var book_name=$("#book_name").val();
			if(!book_name||$.trim(book_name).length==0){
				alert("书籍名称不允许为空");
				return false;
			}
			var book_pages = $("#book_pages").val();
			if(!book_pages||isNaN(book_pages)){
				alert("图书的页数必须填写数字");
				return false;
			}
			var book_types=$("#book_types input:checkbox:checked");
			if(!book_types||book_types.length==0){
				alert("图书至少要选择一个分类");
				return false;
			}
		});
		$("#book_name").change(fn_pinyin);
	});
	function fn_pinyin(){
		var param = {s:$(this).val()};
		$.getJSON("pinyin.jsp",param,function(data){
			$("#pinyin").val(data.pinyin);
			$("#py").val(data.py);
		});
	}
</SCRIPT>
</head>
<body>
	<h4>编辑图书:《<s:property value="book.name"/>》</h4>
	<form action="_book_update.action" method="post">
	<input type="hidden" name="book.id" value="<s:property value="book.id"/>"/>
	<table width="100%" border="1">
		<tbody>
			<tbody>
			<tr>
				<td width="35%">书籍名称</td>
				<td width="65%"><input id="book_name" name="book.name" type="text" size="30" value="<s:property value="book.name"/>"/></td>
			</tr>
			<tr>
				<td>作者</td>
				<td><input name="book.author" type="text" size="30" value="<s:property value="book.author"/>"/></td>
			</tr>
			<tr>
				<td>译者</td>
				<td><input name="book.translator" type="text" size="30" value="<s:property value="book.translator"/>"/></td>
			</tr>
			<tr>
				<td>书籍编码</td>
				<td><input name="book.code" type="text" size="30" value="<s:property value="book.code"/>"/></td>
			</tr>
			<tr>
				<td>出版社</td>
				<td><input name="book.bookConcern" type="text" size="30" value="<s:property value="book.bookConcern"/>"/></td>
			</tr>
			<tr>
				<td>出版日期</td>
				<td><input id="datepicker" name="book.bookDate" type="text" size="25" value="<s:date name="book.bookDate" format="yyyy-MM-dd"/>"/></td>
			</tr>
			<tr>
				<td>拼音</td>
				<td><input id="pinyin" style="background-color: silver;" name="book.fullPinYin" type="text" size="15" value="<s:property value="book.fullPinYin"/>"/></td>
			</tr>
			<tr>
				<td>拼音缩写</td>
				<td><input id="py" style="background-color: silver;" name="book.pinYin" type="text" size="10" value="<s:property value="book.pinYin"/>"/></td>
			</tr>
			<tr>
				<td>是否允许下载</td>
				<td>
					<label>是<input type="radio" name="book.statue" <s:property value="book.statue==@lms.model.Book@CAN_DOWNLOAD?'checked':''"/> value="<s:property value="@lms.model.Book@CAN_DOWNLOAD"/>"/></label>
					<label>否<input type="radio" name="book.statue" <s:property value="book.statue==@lms.model.Book@ONLY_READ?'checked':''"/> value="<s:property value="@lms.model.Book@ONLY_READ"/>"/></label>
				</td>
			</tr>
			<tr>
				<td>图书分类</td>
				<td id="book_types">
					<s:iterator value="types" var="t">
						<label>
							<s:property value="#t.name"/>
							<input type="checkbox" <s:property value="book.types.contains(#t)?'checked':''"/> value="<s:property value="#t.id"/>" name="types"/>
						</label>
					</s:iterator>
				</td>
			</tr>
			<tr>
				<td>简介</td>
				<td><textarea cols="30" rows="3" name="book.desc"><s:property value="book.desc"/></textarea></td>
			</tr>
			<tr>
				<td><input type="submit" value="添加"/></td>
				<td><input type="reset" value="重置"/></td>
			</tr>
		</tbody>
		<tfoot></tfoot>
	</table>
	</form>
</body>
</html>