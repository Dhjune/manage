package com.fanfan.manage.service.book;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fanfan.manage.api.book.service.BookInfoServiceItl;
import com.fanfan.manage.dao.base.PlatFormDaoImpl;
import com.fanfan.manage.dao.book.BookInfoDaoImpl;
import com.fanfan.manage.modle.base.PlatForm;
import com.fanfan.manage.modle.book.BookInfo;

@Service
@Transactional
public class BookInfoServiceImpl implements BookInfoServiceItl{

	@Autowired
	private BookInfoDaoImpl bookInfoDaoImpl;
	
	@Autowired
	private PlatFormDaoImpl platFormDaoImpl;
	
	public BookInfo get(){
		
		return null;
	}
	
	public BookInfo get(int id) throws Exception{
		
		return bookInfoDaoImpl.get(id);
	}
	
	public BookInfo get(int bookId,int platId,boolean show) throws Exception{
		return bookInfoDaoImpl.get(bookId,platId,show);
	}
	
	public List<BookInfo> list() throws Exception{
		
		return bookInfoDaoImpl.list();
		
	}

	public List<BookInfo> list(int bookId) throws Exception{
		
		return bookInfoDaoImpl.list(bookId);
	}

	public void put(BookInfo obj) throws Exception{
		
		obj.setAddTime(new Date());
		
		if(!bookInfoDaoImpl.confirm(obj)) {
			bookInfoDaoImpl.put(obj);
		}
				
	}
	
	public void megerLastPart(int bookId,Map map) throws Exception{
		if(map.size()>0){			
			List<BookInfo> list =  bookInfoDaoImpl.list(bookId);
			for(BookInfo info : list){	
				System.out.println("the ifno");
				info.setLastedPart(map.get("partName").toString());				
				info.setPartUpdateTime((Date)map.get("addTime"));				
			}
		}
	}

	public void delete(BookInfo obj) throws Exception{
		bookInfoDaoImpl.delete(obj);
		
	}

	public void update(BookInfo obj) throws Exception{
		bookInfoDaoImpl.update(obj);
		
	}
	
	public void update(BookInfo obj,int platId) throws Exception{
		PlatForm platForm =  platFormDaoImpl.get(platId);
	//	System.out.println("the platForm id is"+platForm.getName());
		
		obj.getPk().setPlatForm(platForm);
		bookInfoDaoImpl.update(obj);
	}
	
	public boolean confirm(BookInfo obj) throws Exception{
		return  bookInfoDaoImpl.confirm(obj);
	}

}
