package com.fanfan.manage.dao.activity;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.fanfan.manage.api.activity.dao.ActivityImageDaoItl;
import com.fanfan.manage.modle.activity.ActivityImage;

@Repository
public class ActivityImageDaoImpl implements ActivityImageDaoItl{

	@Autowired
	private SessionFactory sessionFactory;
	
	
	public ActivityImage get(int id)  throws Exception{
		ActivityImage activityImage = null ;
         Session session = sessionFactory.getCurrentSession();
		activityImage =  (ActivityImage) session.get(ActivityImage.class, id);		
		activityImage.getActivity();
		return activityImage;
	}

	public List<ActivityImage> list(int acId ,int start ,int pageSize)  throws Exception{
    	
		List<ActivityImage> list =null;
		 Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from ActivityImage img where img.activity =" + acId +" order by img.id asc");
		query.setFirstResult(start);
		query.setMaxResults(pageSize);		
		list = query.list();		
		return list;
		
	}
    
    public List<ActivityImage> list(int start ,int pageSize)  throws Exception{
    	
		List<ActivityImage> list =null;
		 Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from ActivityImage");
		query.setFirstResult(start);
		query.setMaxResults(pageSize);		
		list = query.list();		
		return list;
		
	}
    
    public int count(int acId)  throws Exception{
		int count = 0;
		 Session session = sessionFactory.getCurrentSession();
		String  hql  ="select count(*) from ActivityImage img where img.id ="+ acId;
		Query query = session.createQuery(hql);
		List list =  query.list();		
		if(list != null){								
			count = Integer.parseInt(list.get(0).toString());
		}			
		return count;
	}
		
	public int count()  throws Exception{
		int count = 0;
		 Session session = sessionFactory.getCurrentSession();
		String  hql  ="select count(*) from ActivityImage ";
		Query query = session.createQuery(hql);
		List list =  query.list();		
		if(list != null){								
			count = Integer.parseInt(list.get(0).toString());
		}			
		return count;
	}


	public void put(ActivityImage obj)  throws Exception{
		
		Session session = sessionFactory.getCurrentSession();
		
		session.save(obj);
		
	}

	public void update(ActivityImage obj)  throws Exception{
		Session session  =  sessionFactory.getCurrentSession();
		session.update(obj);
		
	}

	public void delete(ActivityImage obj)  throws Exception{
		// TODO Auto-generated method stub
		Session session =  sessionFactory.getCurrentSession();
		session.delete(obj);
	}

	public List<ActivityImage> list()  throws Exception{
		// TODO Auto-generated method stub
		return null;
	}

	public int last(ActivityImage img)  throws Exception{
		int dispLast = 0;		
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createSQLQuery("SELECT max(disp_order) FROM app_activity_image where activity_id="+img.getActivity().getId());
		List list = null;
		list = query.list();
		if(list!=null&&list.size()>0&&list.get(0)!=null){	
			
			dispLast=Integer.parseInt(list.get(0).toString());
			
		}		
		return dispLast;
	}

}
