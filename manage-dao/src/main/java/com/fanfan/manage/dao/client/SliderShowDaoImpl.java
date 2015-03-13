package com.fanfan.manage.dao.client;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.fanfan.manage.api.client.dao.SliderShowDaoItl;
import com.fanfan.manage.modle.client.SliderShow;
import com.fanfan.manage.modle.client.SliderShow;

@Repository
public class SliderShowDaoImpl implements SliderShowDaoItl{

	@Autowired
	private SessionFactory sessionFactory;
	
	
	public SliderShow get(int id) throws Exception{
		SliderShow search = null ;
		Session session  =  sessionFactory.getCurrentSession();
		search =  (SliderShow) session.get(SliderShow.class, id);
		return search;
	}

	public List<SliderShow> list() throws Exception{
		// TODO Auto-generated method stub
		return null;
	}
	
	public List<SliderShow> list(int start, int pageSize) throws Exception{
		List<SliderShow> list =null;
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from SliderShow rec where rec.status=1 order by rec.dispOrder desc");		
		query.setFirstResult(start);
		query.setMaxResults(pageSize);		
		list = query.list();				
		return list;
	}

	public void put(SliderShow obj) throws Exception{
		Session session  =  sessionFactory.getCurrentSession();	
		session.save(obj);
		
	}

	public void update(SliderShow obj) throws Exception{

		Session session =  sessionFactory.getCurrentSession();
		session.update(obj);
		
	}

	public void delete(SliderShow obj) throws Exception{
		// TODO Auto-generated method stub
		
	}
	
	
	public boolean confirm(SliderShow obj) throws Exception{
		Session session = sessionFactory.getCurrentSession();		
		String sql =String.format("from SliderShow hs where hs.platformId=%s and hs.refId=%s and hs.kind=%s and hs.content='%s'",obj.getPlatformId(),obj.getRefId());
		Query query = session.createQuery(sql);
		List list = query.list();
		if(list !=null && list.size()>0){
			return true;
		}
		return false;
	}
	
	public int count() throws Exception{
		int count = 0;
		Session session = sessionFactory.getCurrentSession();
		
		String  hql  ="select count(*) from SliderShow slid where slid.status=1";
		Query query = session.createQuery(hql);
		List list =  query.list();		
		if(list != null){								
			count = Integer.parseInt(list.get(0).toString());
		}					
		return count;
	}
	
	
	
}
