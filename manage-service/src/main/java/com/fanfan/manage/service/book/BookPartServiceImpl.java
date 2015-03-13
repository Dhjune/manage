package com.fanfan.manage.service.book;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fanfan.manage.api.book.service.BookPartServiceItl;
import com.fanfan.manage.dao.book.BookDaoImpl;
import com.fanfan.manage.dao.book.BookInfoDaoImpl;
import com.fanfan.manage.dao.book.BookPartDaoImpl;
import com.fanfan.manage.modle.book.Book;
import com.fanfan.manage.modle.book.BookInfo;
import com.fanfan.manage.modle.book.BookPart;

@Service
@Transactional
public class BookPartServiceImpl implements BookPartServiceItl{

	@Autowired
	private BookPartDaoImpl bookPartDaoImpl;
	
	@Autowired
	private BookDaoImpl  bookDaoImpl;
	
	@Autowired BookInfoDaoImpl bookInfoDaoImpl;
	
	public BookPart get() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public BookPart get(int id)  throws Exception{
		return bookPartDaoImpl.get(id);
	}

	public List<BookPart> list() {
		// TODO Auto-generated method stub
		return null;
	}
	
    public List<BookPart> list(int bookId ,int start,int pageSize)  throws Exception{
		
		return bookPartDaoImpl.list(bookId,start,pageSize);
		
	}
	
	public List<BookPart> list(int start,int pageSize)  throws Exception{
		
		return bookPartDaoImpl.list(start,pageSize);
		
	}
	
	public int count(int bookId)  throws Exception{
		
		return bookPartDaoImpl.count(bookId);
	}
	
	public int count()  throws Exception{
		
		return bookPartDaoImpl.count();
	}
	
	public Map lastPart(int bookId)  throws Exception{
		
		Map map =new HashMap();
		
		BookPart part = bookPartDaoImpl.lastPart(bookId);
		if(part!=null){	
			//System.out.println(list);
			map.put("partName",part.getPartName()) ;
			map.put("addTime", part.getAddTime());
			
		}else{
			
		}
		return map;
		
	}
	
	public int partIndex(int bookId)  throws Exception{
		return bookPartDaoImpl.partIndex(bookId);
	}

	public void put(BookPart obj)  throws Exception{
		
		obj.setAddTime(new Date());	
		obj.setDispOrder(count(obj.getBook().getId())+1);
		Book book =  obj.getBook();
		book.setChapterNum(book.getChapterNum()+1);
		bookDaoImpl.update(book);
		bookPartDaoImpl.put(obj);	
						
	}

	public void delete(BookPart obj)  throws Exception{
		bookPartDaoImpl.delete(obj);
		
	}

	public void update(BookPart obj)  throws Exception{
				
		obj.setUpdateTime(new Date());
		bookPartDaoImpl.update(obj);
		Map map = lastPart(obj.getBook().getId());		
		if(map.size()>0){			
			List<BookInfo> list =  bookInfoDaoImpl.list(obj.getBook().getId());
			for(BookInfo info : list){
				
				info.setLastedPart(map.get("partName").toString());				
				info.setPartUpdateTime((Date)map.get("addTime"));
				
			}
		}
		
	}
	
	
	public boolean confirm(BookPart obj)  throws Exception{
		
		return bookPartDaoImpl.confirm(obj);
		
	}

}
