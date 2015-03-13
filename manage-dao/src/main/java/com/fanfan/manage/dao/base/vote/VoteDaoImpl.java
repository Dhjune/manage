package com.fanfan.manage.dao.base.vote;

import java.util.List;
import java.util.Set;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.fanfan.manage.api.base.dao.VoteDaoItl;
import com.fanfan.manage.modle.base.vote.Vote;
import com.fanfan.manage.modle.book.Book;
import com.fanfan.manage.modle.book.BookType;


@Repository
public class VoteDaoImpl implements VoteDaoItl{
	
	@Autowired
	private SessionFactory sessionFactory;

	 public Vote get(int id) throws Exception{
		 	Vote vote = null ;
	        Session session = sessionFactory.getCurrentSession();			
			vote =  (Vote) session.get(Vote.class, id);			
			vote.getVotes().size();			
	        return vote; 
	        
	    }

	    public List<Vote> list() throws Exception{
	    	
	    	List<Vote> list =null;
			Session session = sessionFactory.getCurrentSession();			
			Query query = session.createQuery("from Vote v order by v.id asc");				
			list = query.list();		
			
			return list;
	    }
	    
	    public List<Vote> list(int start ,int pageSize) throws Exception{
	    	
			List<Vote> list =null;
			Session session = sessionFactory.getCurrentSession();
			
			Query query = session.createQuery("from Vote v order by v.id asc");
			query.setFirstResult(start);
			query.setMaxResults(pageSize);		
			list = query.list();		
			
			return list;
			
		}
		
		
		public int count() throws Exception{
			int count = 0;
			Session session = sessionFactory.getCurrentSession();			
			String  hql  ="select count(*) from Vote ";
			Query query = session.createQuery(hql);
			List list =  query.list();		
			if(list != null){								
				count = Integer.parseInt(list.get(0).toString());
			}						
			return count;
		}

		
	    public void put(Vote obj) throws Exception{
	    	Session session = sessionFactory.getCurrentSession();
			
			session.save(obj);
			
	    }

	    public void update(Vote obj) throws Exception{
	    	Session session = sessionFactory.getCurrentSession();
			
			session.update(obj);
	    }

	    public void delete(Vote obj) throws Exception{
	    	
	    	Session session = sessionFactory.getCurrentSession();
			//obj.setVotes(null);
			session.delete(obj);
			
	    }

}
