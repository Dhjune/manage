package com.fanfan.manage.dao.book;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.fanfan.manage.api.book.dao.BookTypeDaoItl;
import com.fanfan.manage.modle.book.Author;
import com.fanfan.manage.modle.book.BookType;


@Repository
public class BookTypeDaoImpl implements BookTypeDaoItl{

	@Autowired
	private SessionFactory sessionFactory;

	public BookType get(int id) throws Exception{
		BookType bookType = null;
		Session session = sessionFactory.getCurrentSession();
		
		bookType =  (BookType) session.get(BookType.class, id);
		
		return bookType;
	}

	public List<BookType> list() throws Exception{
		
		List<BookType> list =null;
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from BookType");
		
		try{
		list = query.list();
		}catch(HibernateException e){
			e.printStackTrace();
		}
		
		return list;
	}
	
	
	public List<BookType> list(int start ,int pageSize,int status) throws Exception{
		
		List<BookType> list =null;
		Session session = sessionFactory.getCurrentSession();
		
		Query query = session.createQuery("from BookType bkt where bkt.status="+status);
		query.setFirstResult(start);
		query.setMaxResults(pageSize);		
		list = query.list();		
		
		return list;
		
	}
	
	
	public int count() throws Exception{
		int count = 0;
		Session session = sessionFactory.getCurrentSession();
		
		String  hql  ="select count(*) from BookType ";
		Query query = session.createQuery(hql);
		List list =  query.list();		
		if(list != null){								
			count = Integer.parseInt(list.get(0).toString());
		}			
		
		return count;
	}
	
	public int count(int status) throws Exception{
		int count = 0;
		Session session = sessionFactory.getCurrentSession();
		
		String  hql  ="select count(*) from BookType bkt where bkt.status ="+status;
		Query query = session.createQuery(hql);
		List list =  query.list();		
		if(list != null){								
			count = Integer.parseInt(list.get(0).toString());
		}			
		
		return count;
	}

	public void put(BookType obj) throws Exception{	
		
		Session session = sessionFactory.getCurrentSession();
		
		session.save(obj);
			
		
	}

	public void update(BookType obj) throws Exception{
			
		Session session = sessionFactory.getCurrentSession();
		
		session.update(obj);
	}

	public void delete(BookType obj) throws Exception{
		Session session = sessionFactory.getCurrentSession();
		
		session.delete(obj);
			
	}

	public boolean confirem(BookType obj) throws Exception{
		Session session = sessionFactory.getCurrentSession();
		
		Query query = session.createQuery("from BookType bt where bt.name = '"+obj.getName()+"'");
		List<Author> list = query.list();		
		if ( list != null && list.size()>0){
			return true;
		}else{
			return false;
		}
	}
}
