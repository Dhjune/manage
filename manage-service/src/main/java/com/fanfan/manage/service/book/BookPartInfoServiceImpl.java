package com.fanfan.manage.service.book;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fanfan.manage.api.book.service.BookPartInfoServiceIntl;
import com.fanfan.manage.dao.base.PlatFormDaoImpl;
import com.fanfan.manage.dao.book.BookPartDaoImpl;
import com.fanfan.manage.dao.book.BookPartInfoDaoImpl;
import com.fanfan.manage.modle.base.PlatForm;
import com.fanfan.manage.modle.book.BookPart;
import com.fanfan.manage.modle.book.BookPartInfo;
import com.fanfan.manage.modle.book.BookPartInfoPK;
import com.fanfan.manage.service.vector.AdminOperateVector;


@Service
@Transactional
public class BookPartInfoServiceImpl implements BookPartInfoServiceIntl{
		
	@Autowired
	private BookPartInfoDaoImpl bookPartInfoDaoImpl;
	
	@Autowired
	private BookPartDaoImpl bookPartDaoImpl;
	
	@Autowired
	private PlatFormDaoImpl platFormDaoImpl;
	
	@Autowired
	private AdminOperateVector adminOperateVector;
	
	public BookPartInfo get() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public BookPartInfo get(int partId,int platId)  throws Exception{
		BookPartInfo info =bookPartInfoDaoImpl.get(partId, platId);
		return info;
		//return bookPartInfoDaoImpl.get(partId, platId);
	}

    public List<BookPartInfo> list(int partId,int start,int pageSize)  throws Exception{
		
		return bookPartInfoDaoImpl.list(partId,start,pageSize);
		
	}
	
	public int count(int partId)  throws Exception{
		
		return bookPartInfoDaoImpl.count(partId);
	}
	
	public int count()  throws Exception{
		
		return bookPartInfoDaoImpl.count();
	}

	public void put(BookPartInfo obj)  throws Exception{
		 	 
	    obj.setAddTime(new Date());
		bookPartInfoDaoImpl.put(obj);
		
	}
	
	public void put(BookPartInfo obj,int partId,int platId)  throws Exception{
		PlatForm plat =  platFormDaoImpl.get(platId);
		BookPart part =  bookPartDaoImpl.get(partId);
		BookPartInfoPK pk =  new BookPartInfoPK();
		pk.setBookPart(part);
		pk.setPlatForm(plat);
		obj.setPk(pk);
		bookPartInfoDaoImpl.put(obj);
	}

	public void delete(BookPartInfo obj)  throws Exception{
		
		//bookPartInfoDaoImpl.delete(obj);
		
		obj.setStatus(0);
		update(obj);
	}

	public void update(BookPartInfo obj)  throws Exception{
		
		obj.setUpDateTime(new Date());
	//	bookPartInfoDaoImpl.merge(obj);
		bookPartInfoDaoImpl.update(obj);
		
	}
	
	public void update(BookPartInfo obj,int partId,int platId)  throws Exception{
		PlatForm plat =  platFormDaoImpl.get(platId);
		BookPart part =  bookPartDaoImpl.get(partId);
		BookPartInfoPK pk =  new BookPartInfoPK();
		pk.setBookPart(part);
		pk.setPlatForm(plat);
		obj.setPk(pk);
		obj.setUpDateTime(new Date());
		//	bookPartInfoDaoImpl.merge(obj);
		bookPartInfoDaoImpl.update(obj);
	}
	
	public void update(BookPartInfo obj,int platId)  throws Exception{
		
		obj.setUpDateTime(new Date());
	//	bookPartInfoDaoImpl.merge(obj);
		bookPartInfoDaoImpl.update(obj);
		
	}

	public List<BookPartInfo> list()  throws Exception{
		
		return null;
		
	}

}
