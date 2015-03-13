package com.fanfan.manage.dao.base;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.fanfan.manage.api.base.dao.AppDaoItl;
import com.fanfan.manage.modle.base.Application;
import com.fanfan.manage.modle.base.TransTeam;

@Repository
public class AppDaoImpl implements AppDaoItl{

	@Autowired
	private SessionFactory sessionFactory;
	
	
	public Application get(int id) throws Exception{
		Application app = null ;
        Session session = sessionFactory.getCurrentSession();
		app = (Application) session.get(Application.class, id);
        return 	app; 
        
    }

    public List<Application> list() throws Exception{
    	
        List<Application> list = null;
      
        Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from Application");			
		list = query.list();		
		return list;  
    }
    
    public List<Application> list(int start ,int pageSize) throws Exception{
    	
		List<Application> list =null;
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from Application");
		query.setFirstResult(start);
		query.setMaxResults(pageSize);		
		list = query.list();		
		return list;
		
	}
	
	
	public int count() throws Exception{
		int count = 0;
		Session session = sessionFactory.getCurrentSession();
		String  hql  ="select count(*) from Application ";
		Query query = session.createQuery(hql);
		List list =  query.list();		
		if(list != null){								
			count = Integer.parseInt(list.get(0).toString());
		}			
		return count;
	}

    public void put(Application obj) throws Exception{
    	Session session = sessionFactory.getCurrentSession();
		session.save(obj);
    }

    public void update(Application obj) throws Exception{
    	Session session = sessionFactory.getCurrentSession();
		session.update(obj);
    }

    public void delete(Application obj) throws Exception{
    	Session session = sessionFactory.getCurrentSession();
		session.delete(obj);
		
    }
    
    

}
