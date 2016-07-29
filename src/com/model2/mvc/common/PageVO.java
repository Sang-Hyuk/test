package com.model2.mvc.common;

public class PageVO {
	
	private int currentPage;
	private int totalCount;
	private int pageUnit;
	private int pageSize;
	private int totalpage;
	private int beginPage;
	private int endPage;
	
	public PageVO(){}
	
	public PageVO(int currentPage, int pageUnit, int pageSize, int totalCount){
		
		this.totalCount = totalCount;
		this.pageUnit = pageUnit;
		this.pageSize = pageSize;
		
		this.totalpage = (pageSize == 0) ? totalCount :  (totalCount-1)/pageSize +1;
		this.currentPage = ( currentPage > totalpage) ? totalpage : currentPage;
		
		this.beginPage = ( (currentPage-1) / pageUnit ) * pageUnit +1 ;
		
		if( totalpage <= pageUnit ){
			this.endPage = totalpage;
		}else{
			this.endPage = beginPage + (pageUnit -1);
			if( totalpage <= endPage){
				this.endPage = totalpage;
			}
		}
		
	}
	
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	public int getPageUnit() {
		return pageUnit;
	}
	public void setPageUnit(int pageUnit) {
		this.pageUnit = pageUnit;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getTotalpage() {
		return totalpage;
	}
	public void setTotalpage(int totalpage) {
		this.totalpage = totalpage;
	}
	public int getBeginPage() {
		return beginPage;
	}
	public void setBeginPage(int beginPage) {
		this.beginPage = beginPage;
	}
	public int getEndPage() {
		return endPage;
	}
	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}
	@Override
	public String toString() {
		
		return "[currentPage] : "+currentPage+" [totalCount] : "+totalCount+ "[pageUnit] : "
		+pageUnit+ "[pageSize] : "+pageSize+ "[totalpage] : "+totalpage+ "[beginPage] : "
		+beginPage+ "[endPage] : "+endPage;
	}
	
}
