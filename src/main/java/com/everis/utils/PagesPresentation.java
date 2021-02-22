package com.everis.utils;

import java.util.List;

public class PagesPresentation<T> {
	
	public List<T> data;
	public int totalElmenets;
	public int size;
	public int page;
	public String sort;
	public String orderby;
	
	
	public PagesPresentation(List<T> data, int totalElmenets, int size, int page, String sort, String orderBy) {
		this.data = data;
		this.totalElmenets = totalElmenets;
		this.size = size;
		this.page = page;
		this.sort = sort;
		this.orderby = orderBy;
	}
	
	

}
