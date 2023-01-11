package com.QoreMigration.Model;

import org.springframework.data.domain.Sort;

public class page {
	
	private int pageNumber;
	private int pageSize;
	private Sort.Direction sortDirection = Sort.Direction.ASC;
	private String sortBy;
	private String searchdata;

	public String getSortBy() {
		return sortBy;
	}

	public void setSortBy(String sortBy) {
		this.sortBy = sortBy;
	}

	public int getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public Sort.Direction getSortDirection() {
		return sortDirection;
	}

	public void setSortDirection(Sort.Direction sortDirection) {
		this.sortDirection = sortDirection;
	}

	public String getSearchdata() {
		return searchdata;
	}

	public void setSearchdata(String searchdata) {
		this.searchdata = searchdata;
	}
	
}
