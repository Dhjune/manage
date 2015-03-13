package com.fanfan.manage.service.base.vote;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fanfan.manage.api.base.service.VoteOptionServiceItl;
import com.fanfan.manage.dao.base.vote.VoteOptionDaoImpl;
import com.fanfan.manage.modle.base.vote.Vote;
import com.fanfan.manage.modle.base.vote.VoteOption;

@Service
@Transactional
public class VoteOptionServiceImpl implements VoteOptionServiceItl{

	@Autowired
	private VoteOptionDaoImpl voteOptionDaoImpl;
	
	public VoteOption get() {
		// TODO Auto-generated method stub
		return null;
	}

	public VoteOption get(int id )throws Exception{
		
	    return voteOptionDaoImpl.get(id);
	    
	}

	public List<VoteOption> list() throws Exception{
		return voteOptionDaoImpl.list();
	}
	
	public List<VoteOption> list(int voteId) throws Exception{
		return voteOptionDaoImpl.list(voteId);
	}

    public List<VoteOption> list(int start,int pageSize) throws Exception{
		
		return voteOptionDaoImpl.list(start,pageSize);
		
	}
    
    public int count(int voteId)throws Exception{
    	return voteOptionDaoImpl.count(voteId);
    }
	
	public int count()throws Exception{
		
		return voteOptionDaoImpl.count();
		
	}

	public void put(VoteOption obj) throws Exception{
		
		voteOptionDaoImpl.put(obj);
		
	}

	public void delete(VoteOption obj) throws Exception{
		obj.setStatus(0);
		//voteOptionDaoImpl.delete(obj);
		update(obj);
	}

	public void update(VoteOption obj) throws Exception{
		
		obj.setUpdateTime(new Date());
		voteOptionDaoImpl.update(obj);
		
	}
	
	public boolean confirm(VoteOption obj) throws Exception{
		return voteOptionDaoImpl.confirm(obj);
	}

}
