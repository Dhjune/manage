package com.fanfan.manage.dao.client;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.fanfan.manage.api.client.dao.RecommendDaoItl;
import com.fanfan.manage.modle.base.PlatForm;
import com.fanfan.manage.modle.client.Recommend;

@Repository
public class RecommendDaoImpl implements RecommendDaoItl{

	@Autowired
	private SessionFactory sessionFactory;
	
	public Recommend get(int id) throws Exception{
		Recommend rec = null ;
		Session session  =  sessionFactory.getCurrentSession();
		rec =  (Recommend) session.get(Recommend.class, id);
		return rec;
	}

	public List<Recommend> list() throws Exception{
		// TODO Auto-generated method stub
		return null;
	}
	
	public List<Recommend> list(int start, int pageSize) throws Exception{
		List<Recommend> list =null;
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from Recommend rec where rec.status=1 order by rec.dispOrder desc");		
		query.setFirstResult(start);
		query.setMaxResults(pageSize);		
		list = query.list();				
		return list;
	}

	public void put(Recommend obj) throws Exception{
		Session session  =  sessionFactory.getCurrentSession();
		session.save(obj);
		
	}

	public void update(Recommend obj) throws Exception{

		Session session =  sessionFactory.getCurrentSession();
		session.update(obj);
		
	}

	public void delete(Recommend obj) throws Exception{
		// TODO Auto-generated method stub
		
	}
	
	public int count() throws Exception{
		int count = 0;
		Session session = sessionFactory.getCurrentSession();
		
		String  hql  ="select count(*) from Recommend rec where rec.status=1";
		Query query = session.createQuery(hql);
		List list =  query.list();		
		if(list != null){								
			count = Integer.parseInt(list.get(0).toString());
		}			
		
		return count;
	}
	
	public boolean confirm(Recommend obj){
		
		
		
		return false;
	}

}
