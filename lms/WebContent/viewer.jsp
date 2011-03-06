<%@page contentType="text/html; charset=UTF-8" %>
<%@taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd"> 
<html xmlns="http://www.w3.org/1999/xhtml" lang="en" xml:lang="en">	
    <head> 
        <title></title>         
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" /> 
		<script type="text/javascript" src="js/swfobject/swfobject.js"></script>
		<script type="text/javascript">
			if(window.addEventListener)
			window.addEventListener('DOMMouseScroll', handleWheel, false);
			window.onmousewheel = document.onmousewheel = handleWheel;
			
			if (window.attachEvent) 
			window.attachEvent("onmousewheel", handleWheel);
			
			function handleWheel(event){
				try{
					if(!window.document.FlexPaperViewer.hasFocus()){return true;}
					window.document.FlexPaperViewer.setViewerFocus(true);
					window.document.FlexPaperViewer.focus();
					
					if(navigator.appName == "Netscape"){
						if (event.detail)
							delta = 0;
						if (event.preventDefault){
							event.preventDefault();
							event.returnValue = false;
							}
					}
					return false;	
				}catch(err){return true;}		
			}
		</script>
		
        <script type="text/javascript"> 
            var swfVersionStr = "9.0.124";
            var xiSwfUrlStr = "${expressInstallSwf}";
            var flashvars = { 
                  SwfFile : escape("viewSWF.action?book.id=<s:property value="book.id"/>"),
				  Scale : 0.6, 
				  ZoomTransition : "easeOut",
				  ZoomTime : 0.5,
  				  ZoomInterval : 0.1,
  				  FitPageOnLoad : false,
  				  FitWidthOnLoad : true,
  				  PrintEnabled : true,
  				  FullScreenAsMaxWindow : true,
				  ProgressiveLoading : true,
  				  localeChain: "en_US"
				  };
			 var params = {
				
			    }
            params.quality = "high";
            params.bgcolor = "#ffffff";
            params.allowscriptaccess = "sameDomain";
            params.allowfullscreen = "true";
            var attributes = {};
            attributes.id = "FlexPaperViewer";
            attributes.name = "FlexPaperViewer";
            swfobject.embedSWF(
                "FlexPaperViewer.swf", "flashContent", 
                "1024", "800", 
                swfVersionStr, xiSwfUrlStr, 
                flashvars, params, attributes);
			swfobject.createCSS("#flashContent", "display:block;text-align:left;");
        </script> 
        
    </head> 
    <body> 
    <center>
    	<div style="position:absolute;left:10px;top:10px">
	        <div id="flashContent"> 
	        </div>
        </div>
   </center>
   </body> 
</html> 