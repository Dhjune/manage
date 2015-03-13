package com.fanfan.manage.dao.client;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.fanfan.manage.api.client.dao.HotSearchDaoItl;
import com.fanfan.manage.modle.client.HotSearch;
import com.fanfan.manage.modle.client.HotSearch;

@Repository
public class HotSearchDaoImpl implements HotSearchDaoItl{

	@Autowired
	private SessionFactory sessionFactory;
	
	public HotSearch get(int id) {
		HotSearch search = null ;
		Session session  =  sessionFactory.getCurrentSession();
		search =  (HotSearch) session.get(HotSearch.class, id);
		return search;
	}

	public List<HotSearch> list() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public List<HotSearch> list(int start, int pageSize) {
		List<HotSearch> list =null;
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("from HotSearch rec where rec.status=1 order by rec.dispOrder desc");		
		query.setFirstResult(start);
		query.setMaxResults(pageSize);		
		list = query.list();				
		return list;
	}

	public void put(HotSearch obj) throws Exception{
		Session session  =  sessionFactory.getCurrentSession();	
		session.save(obj);
		
	}

	public void update(HotSearch obj) throws Exception{

		Session session =  sessionFactory.getCurrentSession();
		session.update(obj);
		
	}

	public void delete(HotSearch obj) throws Exception{
		// TODO Auto-generated method stub
		
	}
	
	
	public boolean confirm(HotSearch obj) throws Exception{
		Session session = sessionFactory.getCurrentSession();		
		String sql =String.format("from HotSearch hs where hs.platformId=%s and hs.refId=%s and hs.kind=%s and hs.content='%s'",obj.getPlatformId(),obj.getRefId(),obj.getKind(),obj.getContent());
		Query query = session.createQuery(sql);
		List list = query.list();
		if(list !=null && list.size()>0) {
			return true;
		}
		return false;
	}
	
	public int count() throws Exception{
		int count = 0;
		Session session = sessionFactory.getCurrentSession();
		
		String  hql  ="select count(*) from HotSearch rec where rec.status=1 ";
		Query query = session.createQuery(hql);
		List list =  query.list();		
		if(list != null){								
			count = Integer.parseInt(list.get(0).toString());
		}			
		
		return count;
	}

}
