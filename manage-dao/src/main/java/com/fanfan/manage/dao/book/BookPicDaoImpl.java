package com.fanfan.manage.dao.book;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.fanfan.manage.api.book.dao.BookPicDaoItl;
import com.fanfan.manage.modle.book.Author;
import com.fanfan.manage.modle.book.Book;
import com.fanfan.manage.modle.book.BookPic;

@Repository
public class BookPicDaoImpl implements BookPicDaoItl{
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public BookPic get(){
		BookPic bookPic = null;
		return bookPic;
	}

	 public BookPic get(int id) throws Exception{
		    BookPic bookPic = null ;
	        Session session = sessionFactory.getCurrentSession();
			
			bookPic =  (BookPic) session.get(BookPic.class, id);
			
	        return bookPic; 
	        
	    }

	    public List<BookPic> list() throws Exception{
	    	
	        List<BookPic> list = null;
	      
	        return null;  
	    }
	    
	    public List<BookPic> list(int bookId) throws Exception{
	    	
	    	List<BookPic> list =null;
			Session session = sessionFactory.getCurrentSession();
			
			Query query = session.createQuery("from BookPic bp where bp.book = "+bookId);				
			list = query.list();		
			
			return list;
	      	          
	    }
	    
	    
	    public List<BookPic> list(int start ,int pageSize) throws Exception{
	    	
			List<BookPic> list =null;
			Session session = sessionFactory.getCurrentSession();
			
			Query query = session.createQuery("from BookPic");
			query.setFirstResult(start);
			query.setMaxResults(pageSize);		
			list = query.list();		
			
			return list;
			
		}
				
		public int count() throws Exception{
			int count = 0;
			Session session = sessionFactory.getCurrentSession();
			
			String  hql  ="select count(*) from BookPic ";
			Query query = session.createQuery(hql);
			List list =  query.list();		
			if(list != null){								
				count = Integer.parseInt(list.get(0).toString());
			}			
			
			return count;
		}
		
		
		public int count(int bookId) throws Exception{
			int count = 0;
			Session session = sessionFactory.getCurrentSession();
			
			String  hql  ="select count(*) from BookPic bp where bp.book="+bookId;
			Query query = session.createQuery(hql);
			List list =  query.list();		
			if(list != null){								
				count = Integer.parseInt(list.get(0).toString());
			}			
			
			return count;
		}
		

	    public void put(BookPic obj) throws Exception{
	    	Session session = sessionFactory.getCurrentSession();
			
			session.save(obj);
			
	    }

	    public void update(BookPic obj) throws Exception{
	        
	    	Session session = sessionFactory.getCurrentSession();
			
			session.update(obj);
	    	
	    }
	    
	    public void merge(BookPic obj) throws Exception{
	    	Session session = sessionFactory.getCurrentSession();
	    	session.merge(obj);
	    	
	    }
	    

	    public void delete(BookPic obj) throws Exception{
	    	Session session = sessionFactory.getCurrentSession();
			
			session.delete(obj);
			
			
	    }
	    
	    
	    

}
