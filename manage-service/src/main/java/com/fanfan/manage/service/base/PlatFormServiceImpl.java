package com.fanfan.manage.service.base;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.fanfan.manage.api.base.service.PlatFormServiceItl;
import com.fanfan.manage.dao.base.PlatFormDaoImpl;
import com.fanfan.manage.modle.base.PlatForm;


@Service
@Transactional
public class PlatFormServiceImpl implements PlatFormServiceItl{

	@Autowired
	private PlatFormDaoImpl platFormDaoImpl;	
	
	
	
	public PlatForm get() {
		
		return null;
	}
	
	public PlatForm get(int id) throws Exception{
		
		return platFormDaoImpl.get(id);
		
	}
	
	public PlatForm get(int id,boolean show) throws Exception{
		PlatForm plat = platFormDaoImpl.get(id);
		
		plat.getChildren().size();
		return plat;
		
	}
	
	public void lock(PlatForm obj) throws Exception{
	
		platFormDaoImpl.lock(obj);
		
	}
	
	public List<PlatForm> list() throws Exception{
		
		return platFormDaoImpl.list();
	}
	
    public List<PlatForm> list(int start,int pageSize) throws Exception{
		
		return platFormDaoImpl.list(start,pageSize);
		
	}
	
	public int count() throws Exception{
		
		return platFormDaoImpl.count();		
	}
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor=RuntimeException.class)
	public void put(PlatForm obj) throws Exception{
		
		if(!confirm(obj)){	
			
			java.sql.Date sd =  new Date(new java.util.Date().getTime());	 	 
		    obj.setCreateTime(sd);	
			platFormDaoImpl.put(obj);
			
		}
		
	}

	public void delete(PlatForm obj) throws Exception{
		
		platFormDaoImpl.delete(obj);	
		
	}
	
	
	public void delete(int id) throws Exception{
		PlatForm platform = platFormDaoImpl.get(id);
		//platFormDaoImpl.delete(platform);
		platform.setStatus(0);
		update(platform);
		
	}

	public void update(PlatForm obj) throws Exception{
		
			//lock(obj);
			PlatForm platform = platFormDaoImpl.get(obj.getId());
			platform.setName(obj.getName());
			platform.setWidth(obj.getWidth());
			platform.setHeight(obj.getHeight());
			platform.setStatus(obj.getStatus());
			platFormDaoImpl.update(platform);
		
				
	}
	
	
	public boolean confirm(PlatForm obj) throws Exception{
		
		return platFormDaoImpl.confirem(obj);
		
	}

}
