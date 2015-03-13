package com.fanfan.manage.service.book;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fanfan.manage.api.book.service.BookTypeServiceItl;
import com.fanfan.manage.dao.book.BookTypeDaoImpl;
import com.fanfan.manage.modle.book.BookType;

@Service
@Transactional
public class BookTypeServiceImpl implements BookTypeServiceItl{

	@Autowired
	private BookTypeDaoImpl bookTypeDaoImpl;	
	
	public BookType get() {
		
		return null;
	}
	
	public BookType get(int id)  throws Exception{
		
		return bookTypeDaoImpl.get(id);
		
	}

	public List<BookType> list()  throws Exception{
		
		return bookTypeDaoImpl.list();
	}
	
    public List<BookType> list(int start,int pageSize,int status)  throws Exception{
		
		return bookTypeDaoImpl.list(start,pageSize,status);
		
	}
    
    public int count(int status)  throws Exception{
		
		return bookTypeDaoImpl.count(status);
		
	}
    
	
	public int count()  throws Exception{
		
		return bookTypeDaoImpl.count();
		
	}
	
	public void put(BookType obj)  throws Exception{
		
		if(!confirm(obj)){
			int index = count()+1;
			obj.setDispOrder(index);			
			bookTypeDaoImpl.put(obj);			
		}
		
	}

	public void delete(BookType obj)  throws Exception{
		
		obj.setStatus(0);
		update(obj);
		//bookTypeDaoImpl.delete(obj);	
		
	}

	public void update(BookType obj)  throws Exception{
	//	Date date = new Date();
		obj.setUpDateTime(new Date());
		bookTypeDaoImpl.update(obj);
				
	}
	
	public boolean confirm(BookType obj)  throws Exception{
		return bookTypeDaoImpl.confirem(obj);
	}

	

}
