package com.fanfan.manage.dao.book;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.fanfan.manage.api.book.dao.BookPartInfoDaoItl;
import com.fanfan.manage.modle.book.Book;
import com.fanfan.manage.modle.book.BookPart;
import com.fanfan.manage.modle.book.BookPartInfo;


@Repository
public class BookPartInfoDaoImpl implements BookPartInfoDaoItl{

	@Autowired
	private SessionFactory sessionFactory;
	
	
	public BookPartInfo get(int id) throws Exception{
		BookPartInfo bookPartInfo = null ;
        Session session = sessionFactory.getCurrentSession();
		
		bookPartInfo =  (BookPartInfo) session.get(BookPartInfo.class, id);
		
        return bookPartInfo; 
        
    }
	
	public BookPartInfo get (int partId ,int platId) throws Exception{
		BookPartInfo bookPartInfo =  null;
		
		Session session  =  sessionFactory.getCurrentSession();
		Query  query = session.createQuery("from BookPartInfo info where info.pk.bookPart="+partId+" and  info.pk.platForm="+platId);
		List<BookPartInfo> list = query.list();		
		if(list!=null && list.size()>0){
			bookPartInfo =  list.get(0);
		}
		
		return bookPartInfo;
		
	}

    public List<BookPartInfo> list()  throws Exception{
    	
        List<Book> list = null;
      
        return null;  
    }
    
    public List<BookPartInfo> list(int partId ,int start ,int pageSize) throws Exception{
    	
		List<BookPartInfo> list =null;
		Session session = sessionFactory.getCurrentSession();
		
		Query query = session.createQuery("from BookPartInfo pi where pi.pk.bookPart =" + partId);		
		query.setFirstResult(start);
		query.setMaxResults(pageSize);		
		list = query.list();			
		
		return list;
		
	}
    
    public List<BookPartInfo> list(int start ,int pageSize) throws Exception{
    	
		List<BookPartInfo> list =null;
		Session session = sessionFactory.getCurrentSession();
		
		Query query = session.createQuery("from BookPartInfo");
		query.setFirstResult(start);
		query.setMaxResults(pageSize);		
		list = query.list();		
		
		return list;
		
	}
    
    public int count(int partId) throws Exception{
		int count = 0;
		Session session = sessionFactory.getCurrentSession();
		
		String  hql  ="select count(*) from BookPartInfo where part_id ="+ partId;
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
		
		String  hql  ="select count(*) from BookPartInfo ";
		Query query = session.createQuery(hql);
		List list =  query.list();		
		if(list != null){								
			count = Integer.parseInt(list.get(0).toString());
		}			
		
		return count;
	}

    public void put(BookPartInfo obj) throws Exception{
    	Session session = sessionFactory.getCurrentSession();
		
		session.save(obj);
		
    }

    public void update(BookPartInfo obj) throws Exception{
    	Session session = sessionFactory.getCurrentSession();
		
		session.update(obj);
    	
    }
    
    public void merge(BookPartInfo obj) throws Exception{
    	Session session =  sessionFactory.getCurrentSession();
    	session.merge(obj);
    }
    

    public void delete(BookPartInfo obj) throws Exception{
    	Session session = sessionFactory.getCurrentSession();
		
		session.delete(obj);
		
    }

}
