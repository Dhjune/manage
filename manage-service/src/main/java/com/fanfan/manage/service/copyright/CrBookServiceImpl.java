package com.fanfan.manage.service.copyright;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fanfan.manage.api.demo.AbstractService;
import com.fanfan.manage.dao.copyright.CrBookDaoImpl;
import com.fanfan.manage.modle.book.Book;
import com.fanfan.manage.modle.copyright.CrBook;
import com.fanfan.manage.modle.copyright.CrVList;

@Service
@Transactional
public class CrBookServiceImpl extends AbstractService{
	
	@Autowired
	private CrBookDaoImpl crBookDaoImpl;
	
	public CrBook get(int id ){
		return crBookDaoImpl.get(id);
	}
	
	public void save(CrBook crBook){
		crBookDaoImpl.save(crBook);
	}
	
	public void update(CrBook crBook){
		crBook.setUpdateTime(new Date());
		crBookDaoImpl.update(crBook);
	}
	
	public void delete (CrBook crBook){
		//crBookDaoImpl.delete(crBook);
		crBook.setStatus(0);
		crBookDaoImpl.update(crBook);
	}

	public List<CrVList> list(int start, int pageSize) {
		
		return crBookDaoImpl.list(start,pageSize);
	}

	public int count() {
		
		return crBookDaoImpl.count();
	}

	public List<CrVList> list(int start, int pageSize, Map map) {
		// TODO Auto-generated method stub
		return crBookDaoImpl.list(start,pageSize,map);
	}

	public int count(Map map) {
		// TODO Auto-generated method stub
		return crBookDaoImpl.count(map);
	}
	
	
}
