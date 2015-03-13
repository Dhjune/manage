package com.fanfan.manage.service.client;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fanfan.manage.api.client.service.RecommendServiceItl;
import com.fanfan.manage.dao.client.RecommendDaoImpl;
import com.fanfan.manage.modle.base.PlatForm;
import com.fanfan.manage.modle.client.Recommend;

@Service
@Transactional
public class RecommendServiceImpl implements RecommendServiceItl{

	@Autowired
	private RecommendDaoImpl recommendDaoImpl;
	
	public Recommend get() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public Recommend get(int id)  throws Exception{
		return recommendDaoImpl.get(id);
	}

	public List<Recommend> list()  throws Exception{
		recommendDaoImpl.list();

		return null;
	}
	
	public List<Recommend> list(int start,int pageSize)  throws Exception{
			
		return recommendDaoImpl.list(start,pageSize);
			
	}

	public void put(Recommend obj)  throws Exception{
		obj.setDispOrder(count()+1);
		recommendDaoImpl.put(obj);
	}

	public void delete(Recommend obj) {
		// TODO Auto-generated method stub
		
	}

	public void update(Recommend obj)  throws Exception{
		obj.setUpdateTime(new Date());
		recommendDaoImpl.update(obj);
		
	}
	
	
	public int count()  throws Exception{
		return recommendDaoImpl.count();
	}
	
	public boolean confirm(Recommend obj){
		return recommendDaoImpl.confirm(obj);
	}
	
	public void disp(int up,int down)  throws Exception{
		Recommend rec1 = get(up);
		Recommend rec2 = get(down);
		int order = rec1.getDispOrder();
		rec1.setDispOrder(rec2.getDispOrder());
		rec2.setDispOrder(order);
	}

}
