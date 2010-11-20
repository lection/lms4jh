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
			$("#"+this.contentId).load(this.url,this.param);
		}
};