package com.fanfan.manage.dao.user;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.fanfan.manage.api.demo.AbstractDao;
import com.fanfan.manage.modle.user.UserRead;

@Repository
public class UserReadDaoImpl extends AbstractDao{

	@Autowired
	private SessionFactory sessionFactory;
	
	public UserRead get(String table , int id) throws Exception{
		UserRead uRead = null;
		Session session  =  sessionFactory.getCurrentSession();
		Query query = session.createSQLQuery("select read.start_time,read.end_time,read.read_log_id,read.network,read.book_id,read.read_type,book.book_title from "+table+" read join APP_BOOK_LIST book on book.book_id=read.book_id where read.read_log_id ="+id).setResultTransformer(Transformers.aliasToBean(UserRead.class));
		List<UserRead> list = query.list();
		System.out.println("the service used");
		System.out.println("the list is :"+list);
		if(list!=null && list.size()>0){
			uRead = list.get(0);			
		}
		return uRead;
		
	}
	
	public List<UserRead> list(String table,String userId,int start,int pageSize) throws Exception{
		
		System.out.println("the table name is :"+table);
		List<UserRead> list = null;
		String sql = String.format("select  read.proc_time,read.read_second,read.start_time,read.end_time,read.read_log_id,read.network,read.book_id,read.read_type,book.book_title from %s read join APP_BOOK_LIST book on book.book_id=read.book_id where read.user_id = %s",table,userId);
		Session session  =  sessionFactory.getCurrentSession();		
		Query query = session.createSQLQuery(sql).setResultTransformer(Transformers.aliasToBean(UserRead.class));
		query.setFirstResult(start);
		query.setMaxResults(pageSize);		
		list = query.list();				
		return list;
		
	}
	
	public int count(String table,String userId)throws Exception{
		
		int count = 0;
		Session session = sessionFactory.getCurrentSession();
		String  hql  = String.format("select count(*) from %s where user_id =%s",table,userId);
		Query query = session.createSQLQuery(hql);
		List list =  query.list();		
		if(list != null){								
			count = Integer.parseInt(list.get(0).toString());
		}
		
		return count;
	}
	
}
