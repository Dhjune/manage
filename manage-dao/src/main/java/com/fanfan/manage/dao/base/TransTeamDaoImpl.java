package com.fanfan.manage.dao.base;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.fanfan.manage.api.base.dao.TransTeamDaoItl;
import com.fanfan.manage.modle.base.TransTeam;

@Repository
public class TransTeamDaoImpl implements TransTeamDaoItl{

	@Autowired
	private SessionFactory sessionFactory;
	
    public TransTeam get() {
    	
        TransTeam 	transTeam = null ;       
        return 	transTeam;  
    }

    public TransTeam get(int id) throws Exception{
        TransTeam transTeam = null ;
        Session session = sessionFactory.getCurrentSession();
		transTeam = (TransTeam) session.get(TransTeam.class, id);
        return 	transTeam; 
        
    }

    public List<TransTeam> list() throws Exception{
    	
        List<TransTeam> list = null;
      
        Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from TransTeam team where team.status=1");			
		list = query.list();		
		return list;  
    }
    
    public List<TransTeam> list(int start ,int pageSize) throws Exception{
    	
		List<TransTeam> list =null;
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from TransTeam");
		query.setFirstResult(start);
		query.setMaxResults(pageSize);		
		list = query.list();		
		return list;
		
	}
	
	
	public int count() throws Exception{
		int count = 0;
		Session session = sessionFactory.getCurrentSession();
		String  hql  ="select count(*) from TransTeam ";
		Query query = session.createQuery(hql);
		List list =  query.list();		
		if(list != null){								
			count = Integer.parseInt(list.get(0).toString());
		}			
		return count;
	}

    public void put(TransTeam obj) throws Exception{
    	Session session = sessionFactory.getCurrentSession();
		session.save(obj);
    }

    public void update(TransTeam obj) throws Exception{
    	Session session = sessionFactory.getCurrentSession();
		session.update(obj);
    }

    public void delete(TransTeam obj) throws Exception{
    	Session session = sessionFactory.getCurrentSession();
		session.delete(obj);
		
    }
    
    public boolean confirem(TransTeam obj) throws Exception{
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from TransTeam t where t.name = '"+obj.getName()+"'");
		List<TransTeam> list = query.list();
		if ( list != null && list.size()>0){
			return true;
		}else{
			return false;
		}
	}

}
