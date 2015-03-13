package com.fanfan.manage.service.copyright;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fanfan.manage.dao.copyright.CrLogDaoImpl;
import com.fanfan.manage.modle.copyright.CrLog;

@Service
@Transactional
public class CrLogServiceImpl {
	
	@Autowired
	private CrLogDaoImpl crLogDaoImpl;
	
	public void save(CrLog crLog){
		crLogDaoImpl.save(crLog);
	}

	public List<CrLog> getListByBookId(int start, int pageSize, int bookId) {
		return crLogDaoImpl.getListByBookId(start, pageSize, bookId);
	}
	
}
