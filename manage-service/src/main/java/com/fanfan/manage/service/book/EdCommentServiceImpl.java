package com.fanfan.manage.service.book;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fanfan.manage.api.book.service.EdCommentServiceItl;
import com.fanfan.manage.dao.book.BookPartDaoImpl;
import com.fanfan.manage.dao.book.EdCommentDaoImpl;
import com.fanfan.manage.modle.book.BookPart;
import com.fanfan.manage.modle.book.EditorComment;
@Service
@Transactional
public class EdCommentServiceImpl implements EdCommentServiceItl{

	@Autowired
	private EdCommentDaoImpl edCommentDaoImpl;
	
	public EditorComment get() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public EditorComment get(int id)  throws Exception{
		return edCommentDaoImpl.get(id);
	}

	public List<EditorComment> list() {
		// TODO Auto-generated method stub
		return null;
	}
	
    public List<EditorComment> list(int bookId ,int start,int pageSize)  throws Exception{
		
		return edCommentDaoImpl.list(bookId,start,pageSize);
		
	}
	
	public List<EditorComment> list(int start,int pageSize)  throws Exception{
		
		return edCommentDaoImpl.list(start,pageSize);
		
	}
	
	public int count(int bookId)  throws Exception{
		
		return edCommentDaoImpl.count(bookId);
	}
	
	public int count()  throws Exception{
		
		return edCommentDaoImpl.count();
	}

	public void put(EditorComment obj)  throws Exception{
		
		
		obj.setCommentTime(new Date());	
		
		edCommentDaoImpl.put(obj);
		
	}

	public void delete(EditorComment obj)  throws Exception{
		obj.setStatus(0);
		//edCommentDaoImpl.delete(obj);
		update(obj);
		
	}

	public void update(EditorComment obj)  throws Exception{
		
		obj.setUpDateTime(new Date());
		edCommentDaoImpl.update(obj);
	}

}
