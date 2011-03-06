<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
<title>添加图书</title>
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
	function fn_can_edit(id){
		var input=$("#"+id);
		if(input.attr("readonly")){
			input.removeAttr("readonly");
			input.css("background-color","white");
		}else{
			input.attr("readonly","readonly");
			input.css("background-color","silver");
		}
	}
</SCRIPT>
</head>
<body>
	<h4>添加图书</h4>
	<s:actionmessage/>
	<s:fielderror/>
	<s:actionerror/>
	<form id="add_book_form" action="bookfile_save.action" method="post" enctype="multipart/form-data">
	<table width="85%">
		<thead></thead>
		<tbody>
			<tr>
				<td width="35%">书籍名称</td>
				<td width="65%"><input id="book_name" name="book.name" type="text" size="30"/></td>
			</tr>
			<tr>
				<td>作者</td>
				<td><input name="book.author" type="text" size="30"/></td>
			</tr>
			<tr>
				<td>译者</td>
				<td><input name="book.translator" type="text" size="30"/></td>
			</tr>
			<tr>
				<td>页数</td>
				<td><input name="book.pages" id="book_pages" type="text" size="30"/></td>
			</tr>
			<tr>
				<td>书籍编码</td>
				<td><input name="book.code" type="text" size="30"/></td>
			</tr>
			<tr>
				<td>出版社</td>
				<td><input name="book.bookConcern" type="text" size="30"/></td>
			</tr>
			<tr>
				<td>出版日期</td>
				<td><input id="datepicker" name="date" type="text" size="25"/></td>
			</tr>
			<tr>
				<td>拼音</td>
				<td><input id="pinyin" style="background-color: silver;" name="book.fullPinYin" type="text" readonly="readonly" size="15"/>
				<label><input type="checkbox" onClick="fn_can_edit('pinyin')"/>修改</label></td>
			</tr>
			<tr>
				<td>拼音缩写</td>
				<td><input id="py" style="background-color: silver;" readonly="readonly" name="book.pinYin" type="text" size="10"/>
				<label><input type="checkbox" onClick="fn_can_edit('py')"/>修改</label></td>
			</tr>
			<tr>
				<td>是否允许下载</td>
				<td>
					<label>是<input type="radio" name="book.statue" checked="checked" value="<s:property value="@lms.model.Book@CAN_DOWNLOAD"/>"/></label>
					<label>否<input type="radio" name="book.statue" value="<s:property value="@lms.model.Book@ONLY_READ"/>"/></label>
				</td>
			</tr>
			<tr>
				<td>图书分类</td>
				<td id="book_types">
					<s:iterator value="types" var="t">
						<label>
							<s:property value="#t.name"/>
							<input type="checkbox" value="<s:property value="#t.id"/>" name="types"/>
						</label>
					</s:iterator>
				</td>
			</tr>
			<tr>
				<td>上传图书文件</td>
				<td><input id="book_file" name="bookFile" type="file" size="30"/></td>
			</tr>
			<tr>
				<td>上传SWF文件(Word或txt)</td>
				<td><input name="swf" type="file" size="30"/></td>
			</tr>
			<tr>
				<td>上传图书封面</td>
				<td><input name="coverImg" type="file" size="30"/></td>
			</tr>
			<tr>
				<td>简介</td>
				<td><textarea cols="30" rows="3" name="book.desc"></textarea></td>
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