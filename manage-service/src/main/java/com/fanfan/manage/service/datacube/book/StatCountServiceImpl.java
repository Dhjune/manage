package com.fanfan.manage.service.datacube.book;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fanfan.manage.api.demo.AbstractService;
import com.fanfan.manage.dao.datacube.book.StatCountDaoImpl;
import com.fanfan.manage.modle.book.BookStatCount;
import com.fanfan.manage.modle.book.StatCountbook;


@Service
@Transactional
public class StatCountServiceImpl extends AbstractService{
	
	@Autowired
	private StatCountDaoImpl statCountDaoImpl;
	
	public BookStatCount get(int bookId ,int platId) throws Exception{
		
		return statCountDaoImpl.get(bookId, platId);
		
	}
	
	public List<StatCountbook> list(int platId,int start,int pageSize)  throws Exception{
		return statCountDaoImpl.list(platId,start,pageSize);
		
	}
	
	
	public List<StatCountbook> list(int start,int pageSize,Map map)  throws Exception{
		return statCountDaoImpl.list(start, pageSize, map);
	}
	
	public int count(Map map)  throws Exception{
		return statCountDaoImpl.count(map);
	}
	
	
	public StatCountbook bookStatCount(int bookId,int platId)  throws Exception{
		
		return statCountDaoImpl.bookStatCount(bookId,platId);
		
	}
	
	public int count(int platId)  throws Exception{
		return statCountDaoImpl.count(platId);
	}

}
