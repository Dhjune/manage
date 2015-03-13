package com.fanfan.manage.service.copyright;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fanfan.manage.api.demo.AbstractService;
import com.fanfan.manage.dao.copyright.CrPayDaoImpl;
import com.fanfan.manage.modle.copyright.CrPay;

@Service
@Transactional
public class CrPayServiceImpl extends AbstractService{
	
	@Autowired
	private CrPayDaoImpl crPayDaoImpl;
	
	public CrPay  get(int id){
		return crPayDaoImpl.get(id);
	}
	
	public void save(CrPay crPay){
		crPayDaoImpl.save(crPay);
	}
	
	public List<CrPay> getListByBookId(int bookId){
		return crPayDaoImpl.getListByBookId(bookId);
	}
	
	public void update(CrPay crPay){
		crPayDaoImpl.update(crPay);
		
	}
	
	public void delete(CrPay crPay){
		crPay.setStatus(0);	
		crPayDaoImpl.update(crPay);
		//crPayDaoImpl.delete(crPay);
		
	}
	
}
