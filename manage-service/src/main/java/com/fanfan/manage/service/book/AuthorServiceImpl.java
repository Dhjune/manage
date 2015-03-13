package com.fanfan.manage.service.book;

import java.util.Date;
import java.util.Map;
import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fanfan.manage.api.book.service.AuthorServiceItl;
import com.fanfan.manage.dao.book.AuthorDaoImpl;
import com.fanfan.manage.modle.book.Author;
import com.fanfan.manage.modle.book.Book;

@Service
@Transactional
public class AuthorServiceImpl implements AuthorServiceItl{
	
	@Autowired
	private AuthorDaoImpl authorDaoImpl;

	public Author get() {
		
		return null;
	}
	
	public Author get(int id) throws Exception{
		return  authorDaoImpl.get(id);
	}

	public List<Author> list() throws Exception{
				
		return authorDaoImpl.list();
	}
	
	
	public List<Author> list(int start,int pageSize,int status) throws Exception{
		
		return authorDaoImpl.list(start,pageSize,status);
		
	}
	public List<Author> list(int start,int pageSize,Map map) throws Exception{
		
		return authorDaoImpl.list(start,pageSize,map);
		
	}
	
	
	public int count()throws Exception{
		
		return authorDaoImpl.count();
		
	}

	 public int countSearch(Map map)throws Exception{
		 
	    return authorDaoImpl.countSearch(map);
	    
	 }
	 
	 public int count(Map map)throws Exception{
		 return authorDaoImpl.count(map);
	 }
	 
	 public int count(int status)throws Exception{
		 return authorDaoImpl.count(status);
	 }
	    
    public List<Author> search(int start,int pageSize,Map map)throws Exception{
    	
    	return authorDaoImpl.search(start,pageSize,map);
    	
    }
	
	public void put(Author obj) throws Exception{
		
		if(!confirm(obj)){
				 	 	
		    obj.setAddTime(new Date());	 
		    authorDaoImpl.put(obj);
		    
		}
	    	 			
	}

	public void delete(Author obj) throws Exception{
		
		authorDaoImpl.delete(obj);
		
	}

	public void update(Author obj) throws Exception{
		
		obj.setUpDateTime(new Date());	 
		authorDaoImpl.update(obj);
		
	}
	
	public boolean confirm(Author obj) throws Exception{
		return authorDaoImpl.confirm(obj);
	}
		
}
