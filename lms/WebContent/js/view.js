if (window.addEventListener)
	window.addEventListener('DOMMouseScroll', handleWheel, false);
window.onmousewheel = document.onmousewheel = handleWheel;

if (window.attachEvent)
	window.attachEvent("onmousewheel", handleWheel);

function handleWheel(event) {
	try {
		if (!window.document.FlexPaperViewer.hasFocus()) {
			return true;
		}
		window.document.FlexPaperViewer.setViewerFocus(true);
		window.document.FlexPaperViewer.focus();

		if (navigator.appName == "Netscape") {
			if (event.detail)
				delta = 0;
			if (event.preventDefault) {
				event.preventDefault();
				event.returnValue = false;
			}
		}
		return false;
	} catch (err) {
		return true;
	}
}

var swfVersionStr = "9.0.124";
var xiSwfUrlStr = "${expressInstallSwf}";
var flashvars = {
	SwfFile :"",
	Scale :0.6,
	ZoomTransition :"easeOut",
	ZoomTime :0.5,
	ZoomInterval :0.1,
	FitPageOnLoad :false,
	FitWidthOnLoad :true,
	PrintEnabled :true,
	FullScreenAsMaxWindow :true,
	ProgressiveLoading :true,
	localeChain :"zh_CN"
};
var params = {};
params.quality = "high";
params.bgcolor = "#ffffff";
params.allowscriptaccess = "sameDomain";
params.allowfullscreen = "true";
var attributes = {};
attributes.id = "FlexPaperViewer";
attributes.name = "FlexPaperViewer";
var book = {};
function view(seg){
	var ctd = $("#content_td");
	ctd.empty();
	ctd.append("<div id='flashContent'></div>");
	flashvars.SwfFile=escape("viewSWF.action?book.segments="+seg+"&&book.swf="+book.swf);
	swfobject.embedSWF("FlexPaperViewer.swf", "flashContent", "700", "450",
			swfVersionStr, xiSwfUrlStr, flashvars, params, attributes);
	swfobject.createCSS("#flashContent", "display:block;text-align:left;");
}