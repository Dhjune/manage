package com.fanfan.manage.dao.user;

import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.fanfan.manage.api.demo.AbstractDao;
import com.fanfan.manage.modle.user.UserComment;

@Repository
public class UserCommentDaoImpl extends AbstractDao{
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public  UserComment get(int id) throws Exception{
		UserComment comment = null;
		Session session = sessionFactory.getCurrentSession();
		comment = (UserComment) session.get(UserComment.class, id);		
		return comment;
	}
	
	public List<UserComment> list(int userId, int start, int pageSize) throws Exception{
		List<UserComment> list = null;
		Session session  =  sessionFactory.getCurrentSession();
		String hql  = "from UserComment where 1=1";
		if(userId > 0 ){
			hql += " and user_id="+userId;
		}
		hql += " order by comment_time desc";
		Query query =  session.createQuery(hql);
		query.setFirstResult(start);
		query.setMaxResults(pageSize);	
		list  = query.list();
		return list;
	}
	
	public List<UserComment> list(int start,int pageSize,Map map) throws Exception{
		List<UserComment> list = null;
		Session session =  sessionFactory.getCurrentSession();
		
		return list;
	}
	
	public int count(Map map) throws Exception{
		
		int count  = 0;	
		
		return count;
		
	}
	
	public int count(int userId){
		int count = 0;
		String  hql  ="select count(*) from UserComment where 1=1";
		if(userId>0){
			hql +=" and user_id="+userId;
		}		 
		Session session = sessionFactory.getCurrentSession();
		
		
		Query query = session.createQuery(hql);
		List list =  query.list();		
		if(list != null){								
			count = Integer.parseInt(list.get(0).toString());
		}			
		
		return count;
	}
	
	
}
