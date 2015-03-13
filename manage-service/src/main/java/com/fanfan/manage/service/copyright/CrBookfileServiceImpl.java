package com.fanfan.manage.service.copyright;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fanfan.manage.api.demo.AbstractService;
import com.fanfan.manage.dao.copyright.CrBookfileDaoImpl;
import com.fanfan.manage.modle.copyright.CrBookFile;

@Service
@Transactional
public class CrBookfileServiceImpl extends AbstractService{

	@Autowired
	private CrBookfileDaoImpl crBookfileDaoImpl;
	
	public CrBookFile get(int id){
		
		return crBookfileDaoImpl.get(id);
		
	}
	
	public CrBookFile getByBookId(int bookId){
		
		return crBookfileDaoImpl.getByBookId(bookId);
		
	}
	
	public void update(CrBookFile crBookfile){
		crBookfileDaoImpl.update(crBookfile);
	}
	
	public void delete(CrBookFile crBookfile){
		crBookfileDaoImpl.delete(crBookfile);
	}

	public void save(CrBookFile bookfile) {
		if(bookfile.getId()>0){
			bookfile.setUpdateTime(new Date());
			crBookfileDaoImpl.update(bookfile);
		}else{
			crBookfileDaoImpl.save(bookfile);
		}
	}
}
