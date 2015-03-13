package com.fanfan.manage.dao.base;

import java.util.List;

import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.fanfan.manage.api.base.dao.PlatFormDaoItl;
import com.fanfan.manage.modle.base.PlatForm;
import com.fanfan.manage.modle.book.Author;

@Repository
public class PlatFormDaoImpl implements PlatFormDaoItl{

	@Autowired
	private SessionFactory sessionFactory;

	public PlatForm get(int id) throws Exception{
		PlatForm platForm = null;
		Session session = sessionFactory.getCurrentSession();
		
		platForm =  (PlatForm) session.get(PlatForm.class, id);
		platForm.getChildren().size();
		
		return platForm;
	}
	
	public PlatForm get(int id,boolean show) throws Exception{
		PlatForm platForm = null;
		Session session = sessionFactory.getCurrentSession();
		
		platForm =  (PlatForm) session.get(PlatForm.class, id);
		
		
		return platForm;
	}

	public List<PlatForm> list() throws Exception{
		
		List<PlatForm> list =null;
		Session session = sessionFactory.getCurrentSession();
					
		Query query = session.createQuery("from PlatForm plat where plat.status=1 order by plat.id asc");					
		list = query.list();		
		
		return list;
		
	}
	
	
	public List<PlatForm> list(int start ,int pageSize) throws Exception{
		
		List<PlatForm> list =null;
		Session session = sessionFactory.getCurrentSession();
					
		Query query = session.createQuery("from PlatForm");		
		query.setFirstResult(start);
		query.setMaxResults(pageSize);		
		list = query.list();		
		
		return list;
		
	}
		
	public int count() throws Exception{
		int count = 0;
		Session session = sessionFactory.getCurrentSession();
		
		String  hql  ="select count(*) from PlatForm ";
		Query query = session.createQuery(hql);
		List list =  query.list();		
		if(list != null){								
			count = Integer.parseInt(list.get(0).toString());
		}			
		
		return count;
	}
	

	public void put(PlatForm obj) throws Exception{	
		
		Session session = sessionFactory.getCurrentSession();
		
		session.save(obj);
		
	}

	public void update(PlatForm obj) throws Exception{
		Session session = sessionFactory.getCurrentSession();
		
		session.merge(obj);	
		
		
	}

	public void delete(PlatForm obj) throws Exception{
		Session session = sessionFactory.getCurrentSession();
		
		session.delete(obj);
				
	}

	public boolean confirem(PlatForm obj) throws Exception{
		Session session = sessionFactory.getCurrentSession();		
		Query query = session.createQuery("from PlatForm pf where pf.name = '"+obj.getName()+"'");
		List<Author> list= null;
		try{
			list = query.list();
		
		}catch(Exception e){
			return false;
			
		}finally{
			
		}
		
		if ( list != null && list.size()>0){
			return true;
		}else{
			return false;
		}
	}

	public void lock(PlatForm obj) throws Exception {
		Session session =  sessionFactory.getCurrentSession();
		session.lock(obj,LockMode.NONE );
		
	}

}
