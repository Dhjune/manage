package com.fanfan.manage.dao.book;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.fanfan.manage.api.book.dao.BookTypeRefDaoItl;
import com.fanfan.manage.modle.book.Author;
import com.fanfan.manage.modle.book.BookType;
import com.fanfan.manage.modle.book.BookTypeRef;

@Repository
public class BookTypeRefDaoImpl implements BookTypeRefDaoItl{

	@Autowired
	private  SessionFactory sessionFactory;

	public BookTypeRef get(int id) throws Exception{
		
		BookTypeRef typeRef = null;		
		Session session = sessionFactory.getCurrentSession();
		typeRef =  (BookTypeRef) session.get(BookTypeRef.class, id);
		return typeRef;
		
	}
	
	public BookTypeRef get(int bookId ,int typeId)throws Exception{
		BookTypeRef typeRef = null;		
		Session session = sessionFactory.getCurrentSession();
		Query query =session.createQuery("from BookTypeRef ref where ref.pk.book="+bookId+" and ref.pk.bookType="+typeId);
		List<BookTypeRef> list =  query.list();
		if(list!=null && list.size()>0){
			typeRef =  list.get(0);
		}
		return typeRef;
	}

	public List<BookTypeRef> list() throws Exception{
		List<BookTypeRef> list = null;
		Session session  = sessionFactory.getCurrentSession();
		Query  query = session.createQuery("from BookTypeRef");
		list = query.list();
		return list;
	}
	
	public List<BookTypeRef> list(int bookId) throws Exception{
		List<BookTypeRef> list = null;
		Session session  = sessionFactory.getCurrentSession();
		Query  query = session.createQuery("from BookTypeRef btf where btf.pk.book="+bookId);
		list = query.list();
		return list;
	}
	
	public List<BookTypeRef> list(int bookId,int start,int pageSize) throws Exception{
		List<BookTypeRef> list = null;
		Session session  = sessionFactory.getCurrentSession();
		Query  query = session.createQuery("from BookTypeRef btf where btf.pk.book="+bookId);
		query.setFirstResult(start);
		query.setMaxResults(pageSize);	
		list = query.list();
		BookTypeRef bref = null;
		for(BookTypeRef bRef : list){
			bref =bRef;
			bRef.getPk().getBookType();
		}
		if(bref != null){
			
			bref.getPk().getBook();
		}
		return list;
	}

	public void put(BookTypeRef obj) throws Exception{
		Session session  = sessionFactory.getCurrentSession();
		session.save(obj);
		
	}

	public void update(BookTypeRef obj) throws Exception{
		Session session  = sessionFactory.getCurrentSession();
		session.update(obj);
	}

	public void delete(BookTypeRef obj) throws Exception{
		
		Session session  = sessionFactory.getCurrentSession();
		session.delete(obj);	
	}
	
	public int count() throws Exception{
		int count = 0;
		Session session = sessionFactory.getCurrentSession();
		
		String  hql  ="select count(*) from BookTypeRef ";
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
		String  hql  ="select count(*) from BookTypeRef where book_id="+bookId;
		Query query = session.createQuery(hql);
		List list =  query.list();		
		if(list != null){								
			count = Integer.parseInt(list.get(0).toString());
		}
		return count;
	}
	
	public boolean confirem(BookTypeRef obj) throws Exception{
		
		Session session = sessionFactory.getCurrentSession();		
		Query query = session.createQuery("from BookTypeRef bt where book_id ="+obj.getPk().getBook().getId()+" and type_id ="+obj.getPk().getBookType().getId());
		List<Author> list = query.list();		
		if ( list != null && list.size()>0){
			return true;
		}else{
			return false;
		}
	}
	
}
