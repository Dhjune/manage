package com.fanfan.manage.dao.book;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.fanfan.manage.api.book.dao.EdCommentDaoItl;
import com.fanfan.manage.modle.book.Book;
import com.fanfan.manage.modle.book.BookPart;
import com.fanfan.manage.modle.book.EditorComment;

@Repository
public class EdCommentDaoImpl implements EdCommentDaoItl{
	
	@Autowired
	private SessionFactory sessionFactory;

	public EditorComment get(int id) throws Exception{
		EditorComment edComment = null ;
		Session session = sessionFactory.getCurrentSession();
       
		edComment =  (EditorComment) session.get(EditorComment.class, id);
		
		
		edComment.getBook();
		
        return edComment; 
        
    }

    public List<EditorComment> list() throws Exception{
    	
        List<Book> list = null;
      
        return null;  
    }
    
    public List<EditorComment> list(int bookId ,int start ,int pageSize) throws Exception{
    	System.out.println("the bookId is "+bookId);
		List<EditorComment> list =null;
		Session session = sessionFactory.getCurrentSession();	
		Query query = session.createQuery("from EditorComment bp where bp.book =" + bookId +" order by bp.id asc");
		query.setFirstResult(start);
		query.setMaxResults(pageSize);		
		list = query.list();				
		return list;
		
	}
    
    public List<EditorComment> list(int start ,int pageSize) throws Exception{
    	
		List<EditorComment> list =null;
		Session session = sessionFactory.getCurrentSession();
		
		Query query = session.createQuery("from EditorComment");
		query.setFirstResult(start);
		query.setMaxResults(pageSize);		
		list = query.list();		
		
		return list;
		
	}
    
    public int count(int bookId)throws Exception{
		int count = 0;
		Session session = sessionFactory.getCurrentSession();
		
		String  hql  ="select count(*) from EditorComment where book_id ="+ bookId;
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
		
		String  hql  ="select count(*) from EditorComment ";
		Query query = session.createQuery(hql);
		List list =  query.list();		
		if(list != null){								
			count = Integer.parseInt(list.get(0).toString());
		}			
		
		return count;
	}

    public void put(EditorComment obj) throws Exception{
    	Session session = sessionFactory.getCurrentSession();
		
		session.save(obj);
		
    }

    public void update(EditorComment obj) throws Exception{
    	Session session = sessionFactory.getCurrentSession();
    	session.update(obj);
        
    }

    public void delete(EditorComment obj) throws Exception{
    	Session session = sessionFactory.getCurrentSession();
		
		session.delete(obj);
		
    }

}
