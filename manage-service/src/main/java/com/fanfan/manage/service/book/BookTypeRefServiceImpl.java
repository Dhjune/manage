package com.fanfan.manage.service.book;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fanfan.manage.api.book.service.BookTypeRefServiceItl;
import com.fanfan.manage.dao.book.BookTypeRefDaoImpl;
import com.fanfan.manage.modle.book.BookTypeRef;

@Service
@Transactional
public class BookTypeRefServiceImpl implements BookTypeRefServiceItl{

	@Autowired
	private BookTypeRefDaoImpl bookTypeRefDaoImpl;
	
	public BookTypeRef get() {
		
		return null;
	}
	
	public BookTypeRef get(int bookId,int typeId)  throws Exception{
		return bookTypeRefDaoImpl.get(bookId,typeId);
	}
	
	public BookTypeRef get(int  id) throws Exception{
		
		return bookTypeRefDaoImpl.get(id);
	}

	public List<BookTypeRef> list() {
		
		return null;
	}
	
	public List<BookTypeRef> list(int bookId)  throws Exception{
		
		return bookTypeRefDaoImpl.list(bookId);
	}
	
	public List<BookTypeRef> list(int bookId,int start,int pageSize)  throws Exception{
		
		return bookTypeRefDaoImpl.list(bookId,start,pageSize);
	}

	public void put(BookTypeRef obj)  throws Exception{
		
		if(!confirm(obj)){
			int index = count()+1;
			obj.setDispOrder(index);
			bookTypeRefDaoImpl.put(obj);
		}
		
		
	}

	public void delete(BookTypeRef obj)  throws Exception{
		bookTypeRefDaoImpl.delete(obj);
	}

	public void update(BookTypeRef obj)  throws Exception{
		obj.setUpdateTime(new Date());
		
		bookTypeRefDaoImpl.update(obj);
	}
	
	public int count()  throws Exception{
		return bookTypeRefDaoImpl.count();
	}
	
	public int count(int bookId) throws Exception{
		
		return bookTypeRefDaoImpl.count(bookId);
		
	}
	
	public boolean confirm(BookTypeRef obj) throws Exception{
		return bookTypeRefDaoImpl.confirem(obj);
	}
	
}
