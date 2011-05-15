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
        <style type="text/css">
        	body{
        		background-color: #EEEEEE;
        	}
        	h3{
        		background-color: green;
        		padding: 10px;
        		color: white;
        	}
        	ul{
        		padding-left: 10px;
        	}
        	ul li a{
        		color: #333;
				cursor:pointer;
				text-decoration: none;
				font-weight: bold;
				padding-left: 15px;
				background: url(images/b-1.gif) no-repeat center left;
        	}
        	ul li a:hover {
				color: #690;
  				background: url(images/b-2.gif) no-repeat center left;
			}
			td{
				background-color: white;
			}
        </style>
    </head> 
    <body> 
    <table width="820" cellpadding="0" cellspacing="0" align="center">
    	<tr>
    		<td width="700"><h3>《<s:property value="book.name"/>》</h3></td>
    		<td></td>
    	</tr>
    	<tr>
    		<td valign="top" id="content_td"></td>
    		<td>
    		<div style="width:120px;height:450px; overflow:scroll; border:1px solid;overflow-x:hidden;border: 0px;">
    			<ul style="list-style: none;">
    				<s:property value="@util.BookSegToStringUtil@toLiTags(book)" escape="false"/>
    			</ul>
    		</div>
    		</td>
    	</tr>
    </table>
   </body> 
</html> 