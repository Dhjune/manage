package com.fanfan.manage.dao.book;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.fanfan.manage.api.book.dao.BookInfoDaoItl;
import com.fanfan.manage.modle.book.Book;
import com.fanfan.manage.modle.book.BookInfo;
import com.fanfan.manage.modle.book.BookPart;
@Repository
public class BookInfoDaoImpl implements BookInfoDaoItl{

	@Autowired
	private SessionFactory sessionFactory;
	
	public BookInfo get(int id) throws Exception{
		BookInfo bookInfo = null ;
        Session session = sessionFactory.getCurrentSession();
		
		bookInfo =  (BookInfo) session.get(Book.class, id);
		
        return bookInfo; 		
	}
	
	public BookInfo get(int bookId,int platId,boolean show) throws Exception{
		BookInfo bookInfo = null ;
		System.out.println("the platId is"+platId );
        Session session = sessionFactory.getCurrentSession();
       // session.createSQLQuery(queryString)
        Query  query  =  session.createQuery("from BookInfo info where info.pk.book="+bookId+" and info.pk.platForm="+ platId);
        List<BookInfo> list = query.list();
        if(list.size()>0){
        	bookInfo = list.get(0);
        	
        	        	
        }if(show){
        	bookInfo.getPk();
        }
        return bookInfo;
	}

	public List<BookInfo> list() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public List<BookInfo> list(int bookId) throws Exception{
		List<BookInfo> list =null;
		Session session = sessionFactory.getCurrentSession();
		
		
		
		Query query = session.createQuery("from BookInfo bi where bi.pk.book =" + bookId);			
		list = query.list();
		for(int i = 0;i<list.size();i++){
			list.get(i).getPk().getPlatForm();
		}
		
		
		
		return list;
	}

	public void put(BookInfo obj) throws Exception{
		Session session = sessionFactory.getCurrentSession();
		
		session.save(obj);
		
		
	}

	public void update(BookInfo obj) throws Exception{
		Session session = sessionFactory.getCurrentSession();
		
		session.update(obj);
		
	}
	
	

	public void delete(BookInfo obj) throws Exception{
		Session session = sessionFactory.getCurrentSession();
		
		session.delete(obj);
		
	}
	
	public boolean confirm(BookInfo obj) throws Exception{
		Session session = sessionFactory.getCurrentSession();		
		Query query = session.createQuery("from BookInfo info where info.pk.book="+obj.getPk().getBook().getId()+" and info.pk.platForm="+ obj.getPk().getPlatForm().getId());
		List list = query.list();
		if(list!=null && list.size()>0){
			return true;
		}
		
		return false;
		
	}

}
