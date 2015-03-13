package com.fanfan.manage.service.copyright;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fanfan.manage.api.demo.AbstractService;
import com.fanfan.manage.dao.copyright.CrInfoDaoImpl;
import com.fanfan.manage.modle.copyright.CrInfo;

@Service
@Transactional
public class CrInfoServiceImpl extends AbstractService{
	
	@Autowired
	private CrInfoDaoImpl crInfoDaoImpl;
	
	public CrInfo get(int id){
		
		return crInfoDaoImpl.get(id);
		
	}
	
	public void save(CrInfo crInfo){
		crInfoDaoImpl.save(crInfo);
	}
	
	public void update(CrInfo crInfo){
		crInfoDaoImpl.update(crInfo);
	}
	
	public List<CrInfo> getListByBookId(int bookId){
		return crInfoDaoImpl.getListByBookId(bookId);
	}

	public void delete(CrInfo crInfo) {
		
		crInfo.setStatus(0);
		crInfoDaoImpl.update(crInfo);
		//crInfoDaoImpl.delete(crInfo);
		
	}
	
	
}
