package com.fanfan.manage.dao.activity;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.fanfan.manage.api.activity.dao.ActivityDaoItl;
import com.fanfan.manage.modle.activity.Activity;
import com.fanfan.manage.modle.base.PlatForm;
import com.fanfan.manage.modle.book.Author;

@Repository
public class ActivityDaoImpl implements ActivityDaoItl{

	@Autowired
	private SessionFactory sessionFactory;
	
	public Activity get(int id)  throws Exception{
		
		Activity activity = null;
		 Session session = sessionFactory.getCurrentSession();
		activity = (Activity) session.get(Activity.class, id);
		activity.getPlatForm();
		return activity;
		
	}

	public List<Activity> list()  throws Exception{
		
		List<Activity> list =null;
		 Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from Activity");					
		list = query.list();		
		return list;
		
	}
	
	
	public List<Activity> list(int start ,int pageSize,int status)  throws Exception{
		
		List<Activity> list =null;
		 Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from Activity ac where ac.status="+status);		
		query.setFirstResult(start);
		query.setMaxResults(pageSize);		
		list = query.list();		
		return list;
		
	}
	
	public List<Activity> list(int start ,int pageSize)  throws Exception{
		
		List<Activity> list =null;
		 Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from Activity ac order by ac.id desc");		
		query.setFirstResult(start);
		query.setMaxResults(pageSize);		
		list = query.list();		
		
		return list;
		
	}
		
	public int count()  throws Exception{
		
		int count = 0;
		 Session session = sessionFactory.getCurrentSession();
		
		String  hql  ="select count(*) from Activity ";
		Query query = session.createQuery(hql);
		List list =  query.list();		
		if(list != null){								
			count = Integer.parseInt(list.get(0).toString());
		}			
		return count;
		
	}
	
	public int count(int status)  throws Exception{
		
		int count = 0;
		 Session session = sessionFactory.getCurrentSession();
		
		String  hql  ="select count(*) from Activity ac where ac.status=" +status;
		Query query = session.createQuery(hql);
		List list =  query.list();		
		if(list != null){								
			count = Integer.parseInt(list.get(0).toString());
		}			
				
		return count;
						
	}
	

	public void put(Activity obj)  throws Exception{	
		
		Session session = sessionFactory.getCurrentSession();
		session.save(obj);
		
	}

	public void update(Activity obj)  throws Exception{
		Session session = sessionFactory.getCurrentSession();	
		session.update(obj);
	}

	public void delete(Activity obj)  throws Exception{
		Session session = sessionFactory.getCurrentSession();
		session.delete(obj);
	}

	public boolean confirem(Activity obj)  throws Exception{
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		Query query = session.createQuery("from Activity a where a.schedule = '"+obj.getSchedule()+"'");
		List<Author> list= null;
		try{
			list = query.list();
		}catch(Exception e){
			return false;			
		}finally{
			session.close();
		}
		
		if ( list != null && list.size()>0){
			return true;
		}else{
			return false;
		}
	}

	public int last()  throws Exception{
		int dispLast = 0;		
		 Session session = sessionFactory.getCurrentSession();
		Query query = session.createSQLQuery("SELECT max(disp_order) FROM app_activity_list");
		List list = null;
		list = query.list();
		if(list!=null&&list.size()>0&&list.get(0)!=null){	
			
			dispLast=Integer.parseInt(list.get(0).toString());
			
		}		
		return dispLast;
		
	}

}
