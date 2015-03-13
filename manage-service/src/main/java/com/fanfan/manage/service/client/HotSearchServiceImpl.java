package com.fanfan.manage.service.client;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fanfan.manage.api.client.service.HotSearchServiceItl;
import com.fanfan.manage.dao.client.HotSearchDaoImpl;
import com.fanfan.manage.modle.client.HotSearch;
import com.fanfan.manage.modle.client.HotSearch;

@Service
@Transactional
public class HotSearchServiceImpl implements HotSearchServiceItl{

	@Autowired
	private HotSearchDaoImpl hotSearchDaoImpl;
	
	public HotSearch get() {
		// TODO Auto-generated method stub
		return null;
	}

	public HotSearch get(int id){
		return hotSearchDaoImpl.get(id);
	}
	
	public List<HotSearch> list() {
		// TODO Auto-generated method stub
		return null;
	}

	public List<HotSearch> list(int start,int pageSize){
		return hotSearchDaoImpl.list(start,pageSize);
	}
	
	public void put(HotSearch obj)  throws Exception{
		if(!confirm(obj)){
			obj.setDispOrder(count()+1);
			hotSearchDaoImpl.put(obj);
		}
	}

	public void delete(HotSearch obj) {
		// TODO Auto-generated method stub
		
	}

	public void update(HotSearch obj)  throws Exception{
		obj.setUpdateTime(new Date());
		hotSearchDaoImpl.update(obj);
		
	}
	
	public boolean confirm(HotSearch obj)  throws Exception{
		return hotSearchDaoImpl.confirm(obj);
	}
	
	public int count()  throws Exception{
		return hotSearchDaoImpl.count();
	}
	
	
	
	public void disp(int up,int down) {
		HotSearch rec1 = get(up);
		HotSearch rec2 = get(down);
		int order = rec1.getDispOrder();
		rec1.setDispOrder(rec2.getDispOrder());
		rec2.setDispOrder(order);
	}

}
