package com.fanfan.manage.service.datacube.book;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fanfan.manage.api.demo.AbstractService;
import com.fanfan.manage.dao.datacube.book.StatDayDaoImpl;
import com.fanfan.manage.modle.book.BookStatDay;
import com.fanfan.manage.modle.book.StatDaybook;


@Service
@Transactional
public class StatDayServiceImpl extends AbstractService{
	
	@Autowired
	private StatDayDaoImpl statDayDaoImpl;
	
	public BookStatDay get(int bookId,int platId,String day) throws Exception{
		
		return statDayDaoImpl.get(bookId, platId, day);
		
	}
	
	public List<StatDaybook> list(int platId,int start,int pageSize,String day) throws Exception{
		
		return statDayDaoImpl.list(platId, start, pageSize, day);
		
	}
	
	public List<StatDaybook> list(int start,int pageSize,Map map) throws Exception{
		
		return statDayDaoImpl.list(start, pageSize, map);
					
	}
	
	public int count(Map map) throws Exception{
		return statDayDaoImpl.count(map);
	}
	
	public int count(int platId , String day) throws Exception{
		
		return statDayDaoImpl.count(platId, day);
		
	}
	
	public StatDaybook bookStatDay(int bookId,int platId,String day) throws Exception{
		
		return statDayDaoImpl.bookStatDay(bookId, platId, day);
	}

	public List<StatDaybook> oneList(String bookId, String platId,
			String start_day, String end_day)  throws Exception{
		
		return statDayDaoImpl.oneList(bookId,platId,start_day,end_day);
	}
}
