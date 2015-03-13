package com.fanfan.manage.service.base;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fanfan.manage.api.base.service.TransTeamServiceItl;
import com.fanfan.manage.dao.base.TransTeamDaoImpl;
import com.fanfan.manage.modle.base.TransTeam;
@Service
@Transactional
public class TransTeamServiceImpl implements TransTeamServiceItl{

	@Autowired
	private TransTeamDaoImpl transTeamDaoImpl;	
	
	public TransTeam get() {
		
		return null;
	}
	
	public TransTeam get(int id) throws Exception{
		
		return transTeamDaoImpl.get(id);
		
	}

	public List<TransTeam> list() throws Exception{
		
		return transTeamDaoImpl.list();
	}
	
    public List<TransTeam> list(int start,int pageSize) throws Exception{
		
		return transTeamDaoImpl.list(start,pageSize);
		
	}
	
	public int count()throws Exception{
		
		return transTeamDaoImpl.count();		
	}
	
	public void put(TransTeam obj) throws Exception{
		
		if(!confirm(obj)){		
			
			transTeamDaoImpl.put(obj);
			
		}
		
	}

	public void delete(TransTeam obj) throws Exception{
		obj.setStatus(0);
		//transTeamDaoImpl.delete(obj);
		update(obj);
		
	}

	public void update(TransTeam obj) throws Exception{
		obj.setUpDateTime(new Date());
		transTeamDaoImpl.update(obj);		
	}
	
	
	public boolean confirm(TransTeam obj)throws Exception{
		
		return transTeamDaoImpl.confirem(obj);
		
	}

}
