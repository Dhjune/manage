package com.fanfan.manage.service.common;

import java.util.List;

import com.fanfan.manage.modle.book.Book;
import com.fanfan.manage.modle.book.StatMonthbook;

public class PageNavJson<T>{
	
	private int pageSize;
	private int pageIndex;
	private int pageCount;	
	private int total;
	private List<T> list ;
	private int next;	
	private int prev;
					
	public PageNavJson(List<T> list, int total, int pageSize,
			int pageIndex) {
		this.list = list;
		this.total = total;
		this.pageSize = pageSize;
		this.pageIndex = pageIndex;
		this.initPage();
	}
		

	public void initPage(){
		pageCount = (total % pageSize >0)? (total / pageSize +1):(total / pageSize) ;
		if(pageIndex < pageCount){			
			next = pageIndex + 1;
		}
		
		if(pageIndex >1){
			prev = pageIndex -1 ;
		}
		
	}
	
	
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getPageIndex() {
		return pageIndex;
	}
	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}
	public int getPageCount() {
		return pageCount;
	}
	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public List<T> getList() {
		return list;
	}
	public void setList(List<T> list) {
		this.list = list;
	}
	public int getNext() {
		return next;
	}
	public void setNext(int next) {
		this.next = next;
	}
	public int getPrev() {
		return prev;
	}
	public void setPrev(int prev) {
		this.prev = prev;
	}
	
}
