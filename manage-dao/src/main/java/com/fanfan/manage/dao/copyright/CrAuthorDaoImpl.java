package com.fanfan.manage.dao.copyright;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.fanfan.manage.api.demo.AbstractDao;
import com.fanfan.manage.modle.copyright.CrAuthor;

@Repository
public class CrAuthorDaoImpl extends AbstractDao{
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public CrAuthor get(int id){
		
		CrAuthor crAuthor = null;		
		Session session = sessionFactory.getCurrentSession();
		crAuthor = (CrAuthor) session.get(CrAuthor.class, id);
		return crAuthor;		
		
	}
	
	public void save(CrAuthor crAuthor){
		Session session =  sessionFactory.getCurrentSession();
		session.save(crAuthor);
	}
	
	public void update(CrAuthor crAuthor){
		Session session  =  sessionFactory.getCurrentSession();
		session.update(crAuthor);
		
	}
	
	public void delete(CrAuthor crAuthor){
		Session session  = sessionFactory.getCurrentSession();
		session.delete(crAuthor);
		
	}

	public CrAuthor searcheByName(String authorName) {
		CrAuthor crAuthor = null;
		Session session  =  sessionFactory.getCurrentSession();
		String hql ="from CrAuthor crAu where crAu.name='"+authorName+"'";
		Query query = session.createQuery(hql);
		List<CrAuthor> list = null;
		list =query.list();
		if(list!=null && list.size()>0){
			crAuthor = list.get(0);
		}
		
		return crAuthor;
	}
}
