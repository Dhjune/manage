package com.fanfan.manage.dao.book;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.fanfan.manage.api.book.dao.BookPartDaoItl;
import com.fanfan.manage.modle.book.Author;
import com.fanfan.manage.modle.book.Book;
import com.fanfan.manage.modle.book.BookPart;

@Repository
public class BookPartDaoImpl implements BookPartDaoItl{
	
	@Autowired
	private SessionFactory sessionFactory;

	
	public BookPart get(int id) throws Exception{
		
		BookPart bookPart = null ;
        Session session = sessionFactory.getCurrentSession();
		bookPart =  (BookPart) session.get(BookPart.class, id);	
		
		if(bookPart!=null){
			
			bookPart.getBook();
			
		}
		
        return bookPart; 
        
    }

    public List<BookPart> list() throws Exception{
    	
        List<Book> list = null;
      
        return null;  
    }
    
    public List<BookPart> list(int bookId ,int start ,int pageSize) throws Exception{
    	
		List<BookPart> list =null;
		Session session = sessionFactory.getCurrentSession();
		
		Query query = session.createQuery("from BookPart bp where book_id =" + bookId +" order by bp.id desc");
		query.setFirstResult(start);
		query.setMaxResults(pageSize);		
		list = query.list();		
		
		return list;
		
	}
    
    public List<BookPart> list(int start ,int pageSize) throws Exception{
    	
		List<BookPart> list =null;
		Session session = sessionFactory.getCurrentSession();
		
		Query query = session.createQuery("from BookPart");
		query.setFirstResult(start);
		query.setMaxResults(pageSize);		
		list = query.list();		
		
		return list;
		
	}
    
    public int count(int bookId) throws Exception{
		int count = 0;
		Session session = sessionFactory.getCurrentSession();
		
		String  hql  ="select count(*) from BookPart where book_id ="+ bookId ;
		Query query = session.createQuery(hql);
		List list =  query.list();		
		if(list != null){								
			count = Integer.parseInt(list.get(0).toString());
		}			
		
		return count;
	}
		
	public int count() throws Exception{
		int count = 0;
		Session session = sessionFactory.getCurrentSession();
		
		String  hql  ="select count(*) from BookPart ";
		Query query = session.createQuery(hql);
		List list =  query.list();		
		if(list != null){								
			count = Integer.parseInt(list.get(0).toString());
		}			
		
		return count;
	}
	
	public BookPart lastPart(int bookId) throws Exception{
		BookPart part = null;
		Session session = sessionFactory.getCurrentSession();
		String sql = "from BookPart part where part.partIndex=( select max(part1.partIndex) from BookPart part1 where part1.status = 1 and part1.book="+bookId+")"+" and part.book ="+bookId;
		Query query = session.createQuery(sql);
		
		List<BookPart> list = query.list();		
		if(list!= null && list.size()>0){
			
			part = list.get(0);
		}
		
		return part;
	}
	
	public int partIndex(int bookId) throws Exception{
		
		int partIndex = 0;
		Session session = sessionFactory.getCurrentSession();
		String sql = "select max(part_index) from app_book_parts where book_id = "+bookId;
		Query query = session.createSQLQuery(sql);
		List list = query.list();
		if(list != null && list.size()>0 && list.get(0) !=null){	
			//System.out.println(list);
			partIndex = Integer.parseInt(list.get(0).toString())+1;
			
		}else{
			partIndex = partIndex+1;
		}
		return partIndex;
		
	}

    public void put(BookPart obj) throws Exception{
    	Session session = sessionFactory.getCurrentSession();
		
		session.save(obj);
		
    }

    public void update(BookPart obj) throws Exception{
    	Session session = sessionFactory.getCurrentSession();
		
		session.update(obj);
		
    }

    public void delete(BookPart obj) throws Exception{
    	Session session = sessionFactory.getCurrentSession();
		
		session.delete(obj);
		
    }
    
    public boolean confirm(BookPart obj) throws Exception{
    	Session session = sessionFactory.getCurrentSession();		
		Query query = session.createQuery("from BookPart part where part.partName = '"+obj.getPartName()+"'" +" and part.book="+obj.getBook().getId()+"");
		List<Author> list = query.list();		
		if ( list != null && list.size()>0){
			return true;
		}else{
			return false;
		}
    }
       
	
}
