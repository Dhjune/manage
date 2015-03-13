package com.fanfan.manage.dao.copyright;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.fanfan.manage.api.demo.AbstractDao;
import com.fanfan.manage.modle.copyright.CrLog;

@Repository
public class CrLogDaoImpl extends AbstractDao{
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public void save(CrLog crLog){
		
		Session  session =  sessionFactory.getCurrentSession();
		session.save(crLog);
										
	}
	
	public List<CrLog> getListByBookId(int start,int pageSize,int bookId){
		List<CrLog> list =null;
		Session session =sessionFactory.getCurrentSession();
		String hql = "from CrLog log where log.bookId="+bookId+" order by log.id desc";
		Query query = session.createQuery(hql);
		query.setFirstResult(start);
		query.setMaxResults(pageSize);		
		list= query.list();
		return list;
	}
	
	public int count(int bookId){
		
		int count = 0;
		Session session =sessionFactory.getCurrentSession();
		String hql = "from CrLog log where log.bookId="+bookId;
		return count;
		
	}
	
	
}
