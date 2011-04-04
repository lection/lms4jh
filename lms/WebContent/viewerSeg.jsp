<%@ page contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%-- 分段在线浏览图书的页面 --%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd"> 
<html xmlns="http://www.w3.org/1999/xhtml" lang="en" xml:lang="en">	
    <head> 
        <title>《<s:property value="book.name"/>》</title>         
        <script type="text/javascript" src="js/swfobject/swfobject.js"></script>
        <script type="text/javascript" src="js/jquery.js"></script>
        <script type="text/javascript" src="js/view.js"></script>
        <script type="text/javascript">
        $(function(){
        book.swf="<s:property value="book.swf"/>";view(1);
        });
        </script>
    </head> 
    <body> 
    <table width="70%" border="1" align="center">
    	<tr>
    		<td colspan="2">
    			书名:<s:property value="book.name"/>
    		</td>
    	</tr>
    	<tr>
    		<td valign="top" id="content_td"></td>
    		<td>
    			<ul>
    				<s:property value="@util.BookSegToStringUtil@toLiTags(book)" escape="false"/>
    			</ul>
    		</td>
    	</tr>
    </table>
   </body> 
</html> 