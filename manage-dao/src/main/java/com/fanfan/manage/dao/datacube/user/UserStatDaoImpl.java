package com.fanfan.manage.dao.datacube.user;

import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.fanfan.manage.api.demo.AbstractDao;
import com.fanfan.manage.modle.book.StatDaybook;
import com.fanfan.manage.modle.user.UserStat;

@Repository
public class UserStatDaoImpl extends AbstractDao{
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public UserStat get(){
		UserStat stat = null;
		
		return stat;
	}
	
	public List<UserStat> list(int start,int pageSize,Map map) throws Exception{
		List<UserStat> list = null;
		Session session = sessionFactory.getCurrentSession();
		String sql = "select count(*) count ,info.platform_id,plat.platform_name ,to_char(usr.reg_time,'yyyy-mm-dd') as stat_day from app_user_base usr left join app_user_info info on usr.user_id=info.user_id left join app_base_platform plat on info.platform_id = plat.platform_id group by info.platform_id,plat.platform_name,to_char(usr.reg_time,'yyyy-mm-dd') having 1=1 ";
		for (Object key : map.keySet()) {
    		if(key.toString().equals("platform_id")){
    			sql += " and info."+ key.toString()+"="+map.get(key);
    		}
    		if(key.toString().equals("start_day")){
    			sql += " and to_char(usr.reg_time,'yyyy-mm-dd')>='"+ map.get(key)+"'";
    		}
    		if(key.toString().equals("end_day")){
    			sql += " and to_char(usr.reg_time,'yyyy-mm-dd')<='"+map.get(key)+"'";
    		}
    			   		
    	} 
		
		sql += " order by stat_day";
		Query query = session.createSQLQuery(sql).setResultTransformer(Transformers.aliasToBean(UserStat.class));
		query.setFirstResult(start);
		query.setMaxResults(pageSize);	
		list = query.list();
		return list;
	}
	
	public int count(Map map) throws Exception{
		int count = 0 ; 
		Session  session =  sessionFactory.getCurrentSession();
		String sql = "select count(*)  from app_user_base usr left join app_user_info info on usr.user_id=info.user_id left join app_base_platform plat on info.platform_id = plat.platform_id group by info.platform_id,plat.platform_name,to_char(usr.reg_time,'yyyy-mm-dd') having 1=1 ";
		for (Object key : map.keySet()) {
    		if(key.toString().equals("platform_id")){
    			sql += " and info."+ key.toString()+"="+map.get(key);
    		}
    		if(key.toString().equals("start_day")){
    			sql += " and to_char(usr.reg_time,'yyyy-mm-dd')>='"+ map.get(key)+"'";
    		}
    		if(key.toString().equals("end_day")){
    			sql += " and to_char(usr.reg_time,'yyyy-mm-dd')<='"+map.get(key)+"'";
    		}
    			   		
    	} 
		sql ="select count(*) from ("+sql+")"; 
		Query query = session.createSQLQuery(sql);
		List list = query.list();
		if(list != null){								
			count = Integer.parseInt(list.get(0).toString());
		}
		return count;
	}
	
	
}
