package com.fanfan.manage.service.client;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fanfan.manage.api.client.service.SliderShowServiceItl;
import com.fanfan.manage.dao.client.SliderShowDaoImpl;
import com.fanfan.manage.modle.client.Recommend;
import com.fanfan.manage.modle.client.SliderShow;


@Service
@Transactional
public class SliderShowServiceImpl implements SliderShowServiceItl{

	@Autowired
	private SliderShowDaoImpl sliderShowDaoImpl;
	
	public SliderShow get() {
		// TODO Auto-generated method stub
		return null;
	}

	public SliderShow get(int id)  throws Exception{
		return sliderShowDaoImpl.get(id);
	}

	public List<SliderShow> list()  throws Exception{
		sliderShowDaoImpl.list();

		return null;
	}
	
	public List<SliderShow> list(int start,int pageSize)  throws Exception{
			
		return sliderShowDaoImpl.list(start,pageSize);
			
	}

	public void put(SliderShow obj)  throws Exception{
		obj.setDispOrder(count()+1);
		sliderShowDaoImpl.put(obj);
	}

	public void delete(SliderShow obj) {
		// TODO Auto-generated method stub
		
	}

	public void update(SliderShow obj)  throws Exception{
		obj.setUpdateTime(new Date());
		sliderShowDaoImpl.update(obj);
		
	}
	
	
	public int count()  throws Exception{
		return sliderShowDaoImpl.count();
	}
	
	public void disp(int up,int down)  throws Exception{
		SliderShow slid1 = get(up);
		SliderShow slid2 = get(down);
		int order = slid1.getDispOrder();
		slid1.setDispOrder(slid2.getDispOrder());
		slid2.setDispOrder(order);
	}

	

}
