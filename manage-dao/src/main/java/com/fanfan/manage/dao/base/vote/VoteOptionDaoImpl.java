package com.fanfan.manage.dao.base.vote;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.fanfan.manage.api.base.dao.VoteOptionItl;
import com.fanfan.manage.modle.base.vote.Vote;
import com.fanfan.manage.modle.base.vote.VoteOption;
import com.fanfan.manage.modle.book.Author;

@Repository
public class VoteOptionDaoImpl implements VoteOptionItl{

	@Autowired
	private SessionFactory sessionFactory;
	
	

	public VoteOption get(int id) throws Exception{
		VoteOption option = null ;
        Session session = sessionFactory.getCurrentSession();			
        option =  (VoteOption) session.get(VoteOption.class, id);			
		option.getVote();			
        return option; 
        
    }

    public List<VoteOption> list() throws Exception{
    	
    	List<VoteOption> list =null;
		Session session = sessionFactory.getCurrentSession();			
		Query query = session.createQuery("from VoteOption v order by v.id asc");				
		list = query.list();		
		
		return list;
    }
    
    public List<VoteOption> list(int voteId) throws Exception{
    	
    	List<VoteOption> list =null;
		Session session = sessionFactory.getCurrentSession();			
		Query query = session.createQuery("from VoteOption v where vote_id="+voteId+" order by v.id desc");				
		list = query.list();		
		
		return list;
    }
    
    public List<VoteOption> list(int start ,int pageSize) throws Exception{
    	
		List<VoteOption> list =null;
		Session session = sessionFactory.getCurrentSession();
		
		Query query = session.createQuery("from VoteOption v order by v.id asc");
		query.setFirstResult(start);
		query.setMaxResults(pageSize);		
		list = query.list();		
		
		return list;
		
	}
	
	
	public int count() throws Exception{
		int count = 0;
		Session session = sessionFactory.getCurrentSession();			
		String  hql  ="select count(*) from VoteOption ";
		Query query = session.createQuery(hql);
		List list =  query.list();		
		if(list != null){								
			count = Integer.parseInt(list.get(0).toString());
		}						
		return count;
	}
	
	public int count(int voteId) throws Exception{
		int count = 0;
		Session session = sessionFactory.getCurrentSession();			
		String  hql  ="select count(*) from VoteOption where vote_id="+voteId;
		Query query = session.createQuery(hql);
		List list =  query.list();		
		if(list != null){								
			count = Integer.parseInt(list.get(0).toString());
		}						
		return count;
	}

	
    public void put(VoteOption obj) throws Exception{
    	Session session = sessionFactory.getCurrentSession();
		
		session.save(obj);
		
    }

    public void update(VoteOption obj) throws Exception{
    	Session session = sessionFactory.getCurrentSession();
		
		session.update(obj);
    }

    public void delete(VoteOption obj) throws Exception{
    	
    	Session session = sessionFactory.getCurrentSession();
		
		session.delete(obj);
		
    }
    
    public boolean confirm(VoteOption obj) throws Exception{
    	Session session = sessionFactory.getCurrentSession();		
		Query query = session.createQuery("from VoteOption option where option.subject ='"+obj.getSubject()+"'" +" and option.vote="+obj.getVote().getId()+" and option.key="+obj.getKey());
		List<Author> list = query.list();		
		if ( list != null && list.size()>0){
			return true;
		}else{
			return false;
		}
    	
    }

}
