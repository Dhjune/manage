package com.fanfan.manage.service.activity;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fanfan.manage.api.activity.service.ActivityImageServiceItl;
import com.fanfan.manage.dao.activity.ActivityImageDaoImpl;
import com.fanfan.manage.modle.activity.ActivityImage;
import com.fanfan.manage.modle.book.BookPart;

@Service
@Transactional
public class ActivityImageServiceImpl implements ActivityImageServiceItl{

	@Autowired
	private ActivityImageDaoImpl activityImageDaoImpl;
	
	public ActivityImage get() {
		// TODO Auto-generated method stub
		return null;
	}

	public ActivityImage get(int id)  throws Exception{
		return activityImageDaoImpl.get(id);
	}

	public List<ActivityImage> list() {
		
		return null;
	}
	
    public List<ActivityImage> list(int bookId ,int start,int pageSize)  throws Exception{
		
		return activityImageDaoImpl.list(bookId,start,pageSize);
		
	}
	
	public List<ActivityImage> list(int start,int pageSize)  throws Exception{
		
		return activityImageDaoImpl.list(start,pageSize);
		
	}
	
	public int count(int acId)  throws Exception{
		
		return activityImageDaoImpl.count(acId);
	}
	
	public int count()  throws Exception{
		
		return activityImageDaoImpl.count();
	}

	public void put(ActivityImage obj)  throws Exception{
		int last = last(obj);	
		obj.setDispOrder(last+1);
		activityImageDaoImpl.put(obj);	
		
	}

	public void delete(ActivityImage obj)  throws Exception{
		obj.setStatus(0);
		//activityImageDaoImpl.delete(obj);
		update(obj);
		
	}

	public void update(ActivityImage obj)  throws Exception{
		activityImageDaoImpl.update(obj);
		
	}
	
	public int last(ActivityImage obj)  throws Exception{
		return activityImageDaoImpl.last(obj);
	}

}
