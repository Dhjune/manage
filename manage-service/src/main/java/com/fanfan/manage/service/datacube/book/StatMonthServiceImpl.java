package com.fanfan.manage.service.datacube.book;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fanfan.manage.api.demo.AbstractService;
import com.fanfan.manage.dao.datacube.book.StatMonthDaoImpl;
import com.fanfan.manage.modle.book.BookStatMonth;
import com.fanfan.manage.modle.book.StatMonthbook;

@Service
@Transactional
public class StatMonthServiceImpl extends AbstractService{

	@Autowired
	private StatMonthDaoImpl statMonthDaoImpl;
	
	public BookStatMonth get(int bookId,int platId,String month)  throws Exception{
		
		return statMonthDaoImpl.get(bookId, platId, month);
		
	}
	
	public List<StatMonthbook> list(int platId,int start,int pageSize,String month)  throws Exception{
		
		return statMonthDaoImpl.list(platId, start, pageSize, month);
		
	}
	
	
	public List<StatMonthbook> list(int start,int pageSize,Map map)  throws Exception{
		
		return statMonthDaoImpl.list(start,pageSize,map);
		
	}
	
	public int count(Map map)  throws Exception{
		return statMonthDaoImpl.count(map);
	}
	
	public List<StatMonthbook> oneList(String bookId,String platId,String start_month,String end_month)  throws Exception{
		return statMonthDaoImpl.oneList(bookId,platId,start_month,end_month);
	}
	
	public int count(int platId,String  month)  throws Exception{
		
		return statMonthDaoImpl.count(platId, month);
		
	}
	
	public StatMonthbook bookStatMonth(int bookId, int platId,String month) throws Exception{
		
		return statMonthDaoImpl.bookStatMonth(bookId, platId, month);
		
	}
	
}
