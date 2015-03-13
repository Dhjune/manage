package com.fanfan.manage.dao.base.vote;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.fanfan.manage.api.base.dao.VotePlanItl;
import com.fanfan.manage.modle.base.vote.Vote;
import com.fanfan.manage.modle.base.vote.VotePlan;

@Repository
public class VotePlanDaoImpl implements VotePlanItl{

	@Autowired
	private SessionFactory sessionFactory;
	
	

	public VotePlan get(int id) throws Exception{
		VotePlan plan = null ;
        Session session = sessionFactory.getCurrentSession();			
        plan =  (VotePlan) session.get(VotePlan.class, id);	
        plan.getVote();
        return plan; 
        
    }
	
	public VotePlan get(int id,boolean show) throws Exception{
		VotePlan plan = null ;
		Session session = sessionFactory.getCurrentSession();	
		plan =  (VotePlan) session.get(VotePlan.class, id);	
		if(show&&plan!=null){
			plan.getVote();
			plan.getPlatForm();
		}       		      							
        return plan; 
        
    }
	

    public List<VotePlan> list() throws Exception{
    	
    	List<VotePlan> list =null;
		Session session = sessionFactory.getCurrentSession();			
		Query query = session.createQuery("from VotePlan v order by v.id asc");				
		list = query.list();		
		
		return list;
    }
    
    public List<VotePlan> list(String refType,int refObjId,int refSubId) throws Exception{
    	
    	List<VotePlan> list =null;
    	
    	String hql = "from VotePlan v where v.refType='"+refType+"' and v.refObjId="+refObjId ;
    	
    	if(refSubId >0){
    		hql += " and v.refSubId="+refSubId;
    	}
		Session session = sessionFactory.getCurrentSession();			
		Query query = session.createQuery(hql);				
		list = query.list();
		if(list!=null && list.size()>0){
			for(int i=0;i<list.size();i++){
				System.out.println(list.get(i).getVote().getOptions());
			}
		}
		
		return list;
    }
    
    public List<VotePlan> list(int start ,int pageSize,String refType) throws Exception{
    	
		List<VotePlan> list =null;
		Session session = sessionFactory.getCurrentSession();	
		String hql = "from VotePlan v where v.refType='"+refType+"' order by v.id desc" ;
		Query query = session.createQuery("from VotePlan v where v.refType='"+refType+"' order by v.id desc");
		query.setFirstResult(start);
		query.setMaxResults(pageSize);		
		list = query.list();
		if(list!=null && list.size()>0){
			for(int i=0;i<list.size();i++){
				System.out.println(list.get(i).getVote().getOptions());
			}
		}
		
		
		return list;
		
	}
	
	
	public int count() throws Exception{
		int count = 0;
		Session session = sessionFactory.getCurrentSession();			
		String  hql  ="select count(*) from VotePlan ";
		Query query = session.createQuery(hql);
		List list =  query.list();		
		if(list != null){								
			count = Integer.parseInt(list.get(0).toString());
		}						
		return count;
	}

	
    public void put(VotePlan obj) throws Exception{
    	Session session = sessionFactory.getCurrentSession();		
		session.save(obj);
		
    }
    
    
    

    public void update(VotePlan obj) throws Exception{
    	
    	Session session = sessionFactory.getCurrentSession();		
		session.update(obj);
    }
    
    public void merge(VotePlan obj) throws Exception{
    	
    	Session session  = sessionFactory.getCurrentSession();
    	session.merge(obj);
    	
    }

    public void delete(VotePlan obj) throws Exception{
    	
    	Session session = sessionFactory.getCurrentSession();		
		session.delete(obj);		
    }


}
