package com.fanfan.manage.dao.activity;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.fanfan.manage.api.activity.dao.ActivityApplyDaoItl;
import com.fanfan.manage.modle.activity.ActivityApply;

@Repository
public class ActivityApplyDaoImpl implements ActivityApplyDaoItl{

	@Autowired
	private SessionFactory sessionFactory;
	
	public ActivityApply get(int id)  throws Exception{
		ActivityApply apply = null;
		Session session = sessionFactory.getCurrentSession();
		
		apply =  (ActivityApply) session.get(ActivityApply.class, id);	
		apply.getActivity();		
				
		return apply;
	}
	
	public ActivityApply get(int id ,boolean show)  throws Exception{
		ActivityApply apply = null;
		Session session = sessionFactory.getCurrentSession();
		apply =  (ActivityApply) session.get(ActivityApply.class, id);
		if(show){
			apply.getActivity();
		}
		return apply;
	}
	

	public List<ActivityApply> list()  throws Exception{
		
		return null;
	}

	public void put(ActivityApply obj)  throws Exception{
		
		
	}

	public void update(ActivityApply obj)  throws Exception{
		
		Session session  =  sessionFactory.getCurrentSession();
		session.update(obj);
	}

	public void delete(ActivityApply obj) {
		
		
	}

	public int count(String acId, String status) {
		int count = 0;
		Session session = sessionFactory.getCurrentSession();
		// QBC	效率太低。。。
//		Criteria c = session.createCriteria(ActivityApply.class);		
//		if(status!=null&&!status.equals("")){
//			c = c.add(Restrictions.eq("status", Integer.parseInt(status)));
//		}
//		
//		if(acId!=null&&!acId.equals("")){
//			c = c.createCriteria("activity")
//					.add(Restrictions.idEq(Integer.parseInt(acId)));
//		}
//		c.setProjection(Projections.projectionList().add(Projections.rowCount())
//				);
		String sql ="select count(*) from app_activity_apply  where 1=1 ";
		if(status!=null&&!status.equals("")){
			sql +=" and status="+status;
		}
		if(acId!=null&&!acId.equals("")){
			sql +=" and activity_id="+acId;
		}
		Query query = session.createSQLQuery(sql);
		List list = query.list();
		if(list!=null&&list.size()>0&&list.get(0)!=null){	
																			
			count=Integer.parseInt(list.get(0).toString());	
										
		}	
		
		return count;
	}

	public List<ActivityApply> list(String acId, String status, int start,
			int pageSize)  throws Exception{
		
		List<ActivityApply> list = null;
		Session session  =  sessionFactory.getCurrentSession();
		Query query = session.createQuery("from ActivityApply acply where acply.activity.id="+ Integer.parseInt(acId)+" and acply.status="+status);	
		query.setFirstResult(start);
		query.setMaxResults(pageSize);		
		list = query.list();				
		return list;
		
	}

	

}
