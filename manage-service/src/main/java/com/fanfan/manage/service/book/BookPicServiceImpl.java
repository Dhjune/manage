package com.fanfan.manage.service.book;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fanfan.manage.api.book.service.BookPicServiceItl;
import com.fanfan.manage.dao.book.BookDaoImpl;
import com.fanfan.manage.dao.book.BookPicDaoImpl;
import com.fanfan.manage.modle.book.BookPic;

@Service
@Transactional
public class BookPicServiceImpl implements BookPicServiceItl{

	@Autowired
	private BookPicDaoImpl bookPicDaoImpl;
	
	public BookPic get() {
		
		return bookPicDaoImpl.get();
	}	
	
	public BookPic get(int id) throws Exception{
		return bookPicDaoImpl.get(id);
	}

	public List<BookPic> list() {
		
		return null;
	}
	
	public List<BookPic> list(int bookId)  throws Exception{
		
		return bookPicDaoImpl.list(bookId);
	}

	public void put(BookPic obj)  throws Exception{
		obj.setDispOrder(count(0));
		bookPicDaoImpl.put(obj);
		
	}
	
	public void put(BookPic obj,int bookId)  throws Exception{
		obj.setDispOrder(count(bookId)+1);		
		//obj.setCreateTime(new Date());
		bookPicDaoImpl.put(obj);
		
	}

	public void delete(BookPic obj)  throws Exception{
		obj.setStatus(0);
		//bookPicDaoImpl.delete(obj);
		update(obj);
	}
	
	public int count( int bookId)  throws Exception{
		
		return bookPicDaoImpl.count(bookId);
		
	}
	
	public void merge(BookPic obj) throws Exception{
		
		bookPicDaoImpl.merge(obj);
		
	}

	public void update(BookPic obj)  throws Exception{
		
		bookPicDaoImpl.update(obj);
		
	}

}
