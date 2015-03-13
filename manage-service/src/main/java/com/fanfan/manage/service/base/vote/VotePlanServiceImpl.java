package com.fanfan.manage.service.base.vote;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fanfan.manage.api.base.service.VotePlanServiceItl;
import com.fanfan.manage.dao.base.vote.VotePlanDaoImpl;
import com.fanfan.manage.modle.base.PlatForm;
import com.fanfan.manage.modle.base.vote.Vote;
import com.fanfan.manage.modle.base.vote.VotePlan;
import com.fanfan.manage.modle.book.Book;
import com.fanfan.manage.modle.book.BookInfo;
import com.fanfan.manage.modle.book.BookPart;
import com.fanfan.manage.service.base.PlatFormServiceImpl;
import com.fanfan.manage.service.book.BookInfoServiceImpl;
import com.fanfan.manage.service.book.BookPartServiceImpl;
import com.fanfan.manage.service.book.BookServiceImpl;

@Service
@Transactional
public class VotePlanServiceImpl implements VotePlanServiceItl{

	@Autowired
	private VotePlanDaoImpl votePlanDaoImpl;
	
	@Autowired
	private BookPartServiceImpl bookPartServiceImpl;
	
	
	@Autowired
	private BookServiceImpl  bookServiceImpl;
	
	@Autowired
	private BookInfoServiceImpl bookInfoServiceImpl;
	
	@Autowired
	private PlatFormServiceImpl platFormServiceImpl;
	
	public VotePlan get() {
		
		return null;
	}
	
	public VotePlan get(int id )throws Exception{
		
	    return votePlanDaoImpl.get(id);
	    
	}
	
	public VotePlan get(int id,boolean show)throws Exception{
		return votePlanDaoImpl.get(id,show);
		
	}

	    

    public List<VotePlan> list(int start,int pageSize,String refType) throws Exception{
    	    	
		return votePlanDaoImpl.list(start,pageSize,refType);
		
	}
	
	public int count()throws Exception{
		
		return votePlanDaoImpl.count();
		
	}
	
	public List<VotePlan> list(String refType,int refObjId,int refSubId)throws Exception{
		return votePlanDaoImpl.list(refType,refObjId,refSubId);
	}

	public List<VotePlan> list() throws Exception{
		
		// TODO Auto-generated method stub
		return votePlanDaoImpl.list();
	}

	public void put(VotePlan obj) throws Exception{
		
		
		votePlanDaoImpl.put(obj);
		if(obj.getRefType().equals("part")){	
			
			BookPart bookPart =  bookPartServiceImpl.get(obj.getRefSubId());
			bookPart.setVotePlanId(obj.getId());				
		}else if(obj.getRefType().equals("book")){
			put(obj,obj.getPlatForm().getId());
		}
		
	}
	
	public void put(VotePlan obj,int platId) throws Exception{
		PlatForm platForm =  platFormServiceImpl.get(platId);
		obj.setPlatForm(platForm);
		votePlanDaoImpl.put(obj);
		if(obj.getRefType().equals("book")){
			BookInfo bookInfo = bookInfoServiceImpl.get(obj.getRefObjId(),platId,false);
			bookInfo.setVotePlanId(obj.getId());
		}else if(obj.getRefType().equals("part")){
			BookPart bookPart =  bookPartServiceImpl.get(obj.getRefSubId());
			bookPart.setVotePlanId(obj.getId());	
		}
		
	}

	public void delete(VotePlan obj) throws Exception{
		
		if(obj.getRefType().equals("part")){
			BookPart part = bookPartServiceImpl.get(obj.getRefSubId());
			part.setVotePlanId(0);
			bookPartServiceImpl.update(part);
		}else if (obj.getRefType().equals("book")){
			BookInfo info =  bookInfoServiceImpl.get(obj.getRefObjId(), obj.getPlatForm().getId(),false);
			info.setVotePlanId(0);
			bookInfoServiceImpl.update(info);
		}		
		//votePlanDaoImpl.delete(obj);
		
	}

	public void update(VotePlan obj) throws Exception{
		obj.setUpdateTime(new Date());
		votePlanDaoImpl.update(obj);
		
	}
	
	public void update(VotePlan obj,int platId) throws Exception{
		obj.setUpdateTime(new Date());
		PlatForm platForm =  platFormServiceImpl.get(platId);
		obj.setPlatForm(platForm);
		votePlanDaoImpl.update(obj);
	}
	
	public void merge(VotePlan obj) throws Exception{
		
		java.sql.Date sd =  new java.sql.Date(new Date().getTime());
		obj.setUpdateTime(sd);
		votePlanDaoImpl.merge(obj);
		
	}



	

}
