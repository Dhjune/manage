package com.fanfan.manage.dao.copyright;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.fanfan.manage.api.demo.AbstractDao;
import com.fanfan.manage.modle.copyright.CrBookFile;

@Repository
public class CrBookfileDaoImpl extends AbstractDao{

	@Autowired
	private SessionFactory sessionFactory;
	
	public CrBookFile get(int id){
		
		CrBookFile crBookfile = null;		
		Session session = sessionFactory.getCurrentSession();
		crBookfile = (CrBookFile) session.get(CrBookFile.class, id);
		return crBookfile;		
		
	}
	
	public CrBookFile getByBookId(int bookId){
		CrBookFile crBookfile = null;
		Session session =sessionFactory.getCurrentSession();
		String hql = "from CrBookFile crbf where crbf.bookId="+bookId;
		Query query = session.createQuery(hql);
		List<CrBookFile> list = query.list();
		if(list!=null && list.size()>0){
			crBookfile = list.get(0);
		}
		return crBookfile;
	}
	
	public void save(CrBookFile crBookfile){
		Session session =  sessionFactory.getCurrentSession();
		session.save(crBookfile);
	}
	
	public void update(CrBookFile crBookfile){
		Session session  =  sessionFactory.getCurrentSession();
		session.update(crBookfile);
		
	}
	
	public void delete(CrBookFile crBookfile){
		Session session  = sessionFactory.getCurrentSession();
		session.delete(crBookfile);
		
	}
	
}
