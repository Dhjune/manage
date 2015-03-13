package com.fanfan.manage.service.base.vote;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fanfan.manage.api.base.service.VoteServiceItl;
import com.fanfan.manage.dao.base.PlatFormDaoImpl;
import com.fanfan.manage.dao.base.vote.VoteDaoImpl;
import com.fanfan.manage.modle.base.vote.Vote;
import com.fanfan.manage.modle.book.Book;

@Service
@Transactional
public class VoteServiceImpl implements VoteServiceItl{

	@Autowired
	private VoteDaoImpl voteDaoImpl;
	
	@Autowired
	private PlatFormDaoImpl platFormDaoImpl; 
	
	public Vote get() {
				
		return null;
	}
	
	public Vote get(int id ) throws Exception{
		
	    return voteDaoImpl.get(id);
	    
	}

	    

    public List<Vote> list(int start,int pageSize) throws Exception{
		
		return voteDaoImpl.list(start,pageSize);
		
	}
	
	public int count() throws Exception{
		
		return voteDaoImpl.count();
		
	}
	

	public List<Vote> list() throws Exception{
		
		return voteDaoImpl.list();
		
	}

	public void put(Vote obj) throws Exception{
		
		voteDaoImpl.put(obj);
		
	}
	
	public void put(Vote obj,int platId) throws Exception{
		obj.setPlatForm(platFormDaoImpl.get(platId));
		voteDaoImpl.put(obj);
		
		
	}

	public void delete(Vote obj) throws Exception{
		obj.setStatus(0);
		//voteDaoImpl.delete(obj);
		update(obj);
		
	}

	public void update(Vote obj) throws Exception{
		obj.setUpdateTime(new Date());
		voteDaoImpl.update(obj);
		
	}

	public void update(Vote vote, int parseInt) throws Exception{
		
		vote.setPlatForm(platFormDaoImpl.get(parseInt));		
		voteDaoImpl.update(vote);
		
	}

}
