package util;

import java.util.Collection;

@SuppressWarnings("unchecked")
public class LmsPage {
	private int total;
	private Collection content;
	private int page;
	private int start;
	private int pageSize;
	private int pageTotal;
	
	public LmsPage(int page, int pageSize, int total) {
		this.page = page;
		this.pageSize = pageSize;
		this.total = total;
		pageTotal = total/pageSize + (total%pageSize==0?0:1);
		if(this.page > pageTotal)this.page = pageTotal;
		if(this.page < 1)this.page = 1;
		start = (this.page - 1)*pageSize;
	}
	
	public int getTotal() {
		return total;
	}
	public Collection getContent() {
		return content;
	}
	public void setContent(Collection content) {
		this.content = content;
	}
	public int getPage() {
		return page;
	}
	public int getPageSize() {
		return pageSize;
	}
	public int getStart() {
		return start;
	}
	public int getPageTotal() {
		return pageTotal;
	}
}
