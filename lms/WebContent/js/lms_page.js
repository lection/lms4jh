var lmsPageUtil = {
		url:null,
		pageName:'pageNum',
		contentId:'book_list_div',
		goId:'lms_pageNum',
		param:{},
		go:function(page){
			if(!this.url){
				alert("使用lms分页有异常，未设置url");return;
			}
			this.param[this.pageName]=page;
			var content_div = $("#"+this.contentId);
			content_div.html("<img src='images/wait.gif'/>");
			content_div.load(this.url,this.param);
		}
};