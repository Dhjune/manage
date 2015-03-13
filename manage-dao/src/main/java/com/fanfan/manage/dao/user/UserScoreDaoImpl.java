package com.fanfan.manage.dao.user;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.fanfan.manage.api.user.dao.UserScoreDaoItl;
import com.fanfan.manage.modle.book.BookPart;
import com.fanfan.manage.modle.user.ScoreHistory;

@Repository
public class UserScoreDaoImpl implements UserScoreDaoItl{

	@Autowired
	private SessionFactory sessionFactory;
	
	
	public ScoreHistory get(int id) throws Exception{
		
		ScoreHistory score =  null;
		Session session =  sessionFactory.getCurrentSession();
		score =  (ScoreHistory) session.get(ScoreHistory.class, id);	
		return score;
		
	}

	public List<ScoreHistory> list() throws Exception{
		// TODO Auto-generated method stub
		return null;
	}
	
	public List<ScoreHistory> list(int userId ,int start ,int pageSize) {
    	
		List<ScoreHistory> list =null;
		Session session = sessionFactory.getCurrentSession();		
		Query query = session.createQuery("from ScoreHistory sc where sc.userId =" + userId +" order by sc.id asc");
		query.setFirstResult(start);
		query.setMaxResults(pageSize);		
		list = query.list();				
		return list;
		
	}
	
	public int count(int userId) throws Exception{
		int count = 0;
		Session session = sessionFactory.getCurrentSession();		
		String  hql  ="select count(*) from ScoreHistory where user_id="+userId;
		Query query = session.createQuery(hql);
		List list =  query.list();		
		if(list != null){								
			count = Integer.parseInt(list.get(0).toString());
		}			
		
		return count;
	}

	public void put(ScoreHistory obj) throws Exception{
		Session session =  sessionFactory.getCurrentSession();
		session.save(obj);
		
	}

	public void update(ScoreHistory obj) throws Exception{
		Session session =  sessionFactory.getCurrentSession();
		session.update(obj);
		
	}

	public void delete(ScoreHistory obj)  throws Exception{
		Session session =  sessionFactory.getCurrentSession();
		session.delete(obj);
	}
	
	
	
	
}
