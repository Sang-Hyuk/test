package com.model2.mvc.common;


public class SearchVO {
	
	private int page;
	private String searchCondition;
	private String searchKeyword;
	private int pageUnit;
	private String minPrice;
	private String maxPrice;
	private String priceRange;
	private String priceSort;
	
	public SearchVO(){
	}
	
	public int getPageUnit() {
		return pageUnit;
	}
	public void setPageUnit(int pageUnit) {
		this.pageUnit = pageUnit;
	}
	
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}

	public String getSearchCondition() {
		return searchCondition;
	}
	public void setSearchCondition(String searchCondition) {
		this.searchCondition = searchCondition;
	}
	public String getSearchKeyword() {
		return searchKeyword;
	}
	public void setSearchKeyword(String searchKeyword) {
		this.searchKeyword = searchKeyword;
	}
	
	public String getMinPrice() {
		return minPrice;
	}

	public void setMinPrice(String minPrice) {
		this.minPrice = minPrice;
	}

	public String getMaxPrice() {
		return maxPrice;
	}

	public void setMaxPrice(String maxPrice) {
		this.maxPrice = maxPrice;
	}

	
	public String getPriceRange() {
		return priceRange;
	}

	public void setPriceRange(String priceRange) {
		this.priceRange = priceRange;
	}

	public String getPriceSort() {
		return priceSort;
	}

	public void setPriceSort(String priceSort) {
		this.priceSort = priceSort;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "[page] = "+page+"[searchKeyword] = "+searchKeyword+"[searchCondition] = "+searchCondition
				+"[minPrice] = "+minPrice+"[maxPrice] = "+maxPrice+"[priceSort] = "+priceSort;
	}
	
}