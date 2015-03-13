package com.fanfan.manage.service.copyright;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fanfan.manage.api.demo.AbstractService;
import com.fanfan.manage.dao.copyright.CrAuthorDaoImpl;
import com.fanfan.manage.dao.copyright.CrBookDaoImpl;
import com.fanfan.manage.modle.copyright.CrAuthor;
import com.fanfan.manage.modle.copyright.CrBook;

@Service
@Transactional
public class CrAuthorServiceImpl extends AbstractService{
	
	@Autowired
	private CrAuthorDaoImpl crAuthorDaoImpl;
	
	@Autowired
	private CrBookDaoImpl crBookDaoImpl;
	
	public CrAuthor get(int id ){
		return crAuthorDaoImpl.get(id);
	}
	
	public void update(CrAuthor crAuthor){
		crAuthorDaoImpl.update(crAuthor);
		
	}
	
	public void delete(CrAuthor crAuthor){
		crAuthorDaoImpl.delete(crAuthor);
	}

	public void updateAuthorandBook(CrAuthor crAuthor, int bookId) {
		
		if(crAuthor.getId()>0){
			crAuthorDaoImpl.update(crAuthor);
		}else{
			crAuthorDaoImpl.save(crAuthor);
		}
		CrBook crBook = crBookDaoImpl.get(bookId);
		crBook.setAuthorId(crAuthor.getId());
		
	}

	public CrAuthor searchByName(String authorName) {
		return  crAuthorDaoImpl.searcheByName(authorName);
		
	}
	
}
