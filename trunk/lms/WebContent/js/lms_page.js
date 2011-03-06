var lmsPageUtil = {
		url:null,//正常分页使用的url
		find_url:null,//复杂查询条件使用url
		pageName:'pageNum',//分页页码的属性名
		contentId:'book_list_div',//刷新区域的ID
		goId:'lms_pageNum',//使用跳转按钮时，获取页码的文本id
		param:{},//使用查询时的参数
		find_flag:false,//是否拥有查询条件
		find_form_elements:null,//查询需要访问的表单元素JQUERY对象
		find_commit:function(){
			lmsPageUtil.find_flag=false;
			lmsPageUtil.param={};
			if(!this.find_form_elements){
				return;
			}
			this.find_form_elements.each(function(){
				var input = $(this);
				if("button"==input.attr("type"))return;
				if(input.val()&&$.trim(input.val()).length!=0){
					lmsPageUtil.param[input.attr("name")]=input.val();
					lmsPageUtil.find_flag = true;
				};
			});
			this.go(1);
		},//提交翻页函数
		go:function(page){
			if(!this.url || !this.find_url){
				alert("使用lms分页有异常，未设置url或find_url");
				return;
			}
			if(!page){
				page = $("#"+this.goId).val();
				if(isNaN(page)){page = 1;}
			}
			this.param[this.pageName]=page;
			var content_div = $("#"+this.contentId);
			content_div.html("<img src='images/wait.gif'/>");
			content_div.load(this.find_flag?this.find_url:this.url,this.param);
		}
};