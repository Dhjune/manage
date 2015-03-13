package com.fanfan.manage.service.book;

import com.fanfan.manage.api.book.service.BookServiceItl;
import com.fanfan.manage.dao.base.PlatFormDaoImpl;
import com.fanfan.manage.dao.book.AuthorDaoImpl;
import com.fanfan.manage.dao.book.BookDaoImpl;
import com.fanfan.manage.dao.book.BookInfoDaoImpl;
import com.fanfan.manage.modle.base.PlatForm;
import com.fanfan.manage.modle.book.Book;
import com.fanfan.manage.modle.book.BookInfo;
import com.fanfan.manage.modle.book.BookInfoPK;
import com.fanfan.manage.modle.book.BookType;
import com.fanfan.manage.service.vector.AdminOperateVector;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class BookServiceImpl implements BookServiceItl{
	
	private static Log log = LogFactory.getLog(BookServiceImpl.class.getName());
	
	@Autowired
	private AdminOperateVector adminOperateVector;
	
	@Autowired
	private BookDaoImpl bookDaoImpl;
	
	@Autowired
	private AuthorDaoImpl  authorDaoImpl;
	
	@Autowired 
	private BookInfoDaoImpl bookInfoDaoImpl;
	
	@Autowired
	private PlatFormDaoImpl platFormDaoImpl;
	
    public Book get() {
        return null;
    }
    
    public Book get(int id )  throws Exception{
    //	log.info("ssssssss");
    	return bookDaoImpl.get(id);
    }
    
    public Book get(int id,int typeshow)  throws Exception{
    	//log.info("ssssssss");
    	Book book = null;
    	book = bookDaoImpl.get(id,typeshow);
    //	adminOperateVector.operate(book, new HashMap());
    	return book;
    }
    
    public Book get(int id,boolean show) throws Exception{
    //	log.info("ssssssss");
    	Book book = null;
    	book = bookDaoImpl.get(id,show);
    	adminOperateVector.operate(book, new HashMap());
    	return book;
    }

    public List<Book> list() {
        return null;
    }
    
    public List<Book> list(Map map)  throws Exception{
    	return bookDaoImpl.list(map);
    }
    public List<Book> list(int start,int pageSize,Map map)  throws Exception{
    	return bookDaoImpl.list(start,pageSize,map);
    }

    public List<Book> list(int start,int pageSize,int status)  throws Exception{
		
		System.out.println("bookDao :"+bookDaoImpl);
    	
		return bookDaoImpl.list(start,pageSize,status);
		
	}
    
    public int countSearch(Map map)  throws Exception{
    	
    	return bookDaoImpl.countSearch(map);
    	
    }
    
    public int count(Map map)  throws Exception{
    	return bookDaoImpl.count(map);
    }
    
    public List<Book> search(int start,int pageSize,Map map)  throws Exception{
    	
    	return bookDaoImpl.search(start,pageSize,map);
    	
    }
	
    
    
	public int count() throws Exception{
		
		return bookDaoImpl.count();
		
	}
	
	public int count(int status)  throws Exception{
		
		return bookDaoImpl.count(status);
		
	}
	
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor=RuntimeException.class)
    public void put(Book obj)  throws Exception{
		
		if(obj.getAuthor()!=null){
		int num =  obj.getAuthor().getBookNum()+1;		
		obj.getAuthor().setBookNum(num);
		}
    	bookDaoImpl.put(obj);
    	
    }
	

	public BookInfo put(Book obj,boolean info)  throws Exception{
		
		if(info){
			PlatForm plat =  platFormDaoImpl.get(0);
			BookInfo bookInfo = new BookInfo();
			BookInfoPK pk =  new BookInfoPK();
			pk.setBook(obj);
			pk.setPlatForm(plat);
			bookInfo.setPk(pk);
			bookInfo.setBookTitle(obj.getBookTitle());
			bookInfo.setStatus(1);
			bookDaoImpl.put(obj);
			bookInfoDaoImpl.put(bookInfo);
			return bookInfo;
		}
		return null;
	}

	public boolean confirm(Book obj)  throws Exception{
		return bookDaoImpl.confirm(obj);
	}
	
	
	public void delete(Book obj)  throws Exception{
    	
    	obj.setStatus(0);
    	bookDaoImpl.update(obj);
    	
    }
       
    public void update(Book obj)  throws Exception{
    	obj.setUpdateTime(new Date());
    	//bookDaoImpl.update(obj);
    	
    	bookDaoImpl.merge(obj); 
    	
    }
    
    public void merge(Book obj) throws Exception{
    	
    	bookDaoImpl.merge(obj);
    	
    }
    
    

}
