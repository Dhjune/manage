package com.fanfan.manage.dao.datacube.activity;

import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.fanfan.manage.api.demo.AbstractDao;
import com.fanfan.manage.modle.activity.ActivityStat;
import com.fanfan.manage.modle.user.UserStat;

@Repository
public class ActivityStatDaoImpl extends AbstractDao{
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public List<ActivityStat> list(int start,int pageSize,Map map) throws Exception{
		List<ActivityStat> list = null;
		Session session = sessionFactory.getCurrentSession();
		
		String sql = "select count(*) count ,acti.platform_id,plat.platform_name ,to_char(appl.create_time,'%s')as stat_day,acti.activity_id,acti.subject from app_activity_apply appl left join app_activity_list acti on appl.activity_id = acti.activity_id left join app_base_platform plat on acti.platform_id = plat.platform_id group by acti.activity_id,acti.platform_id,plat.platform_name,to_char(appl.create_time,'%s'),acti.subject having 1=1 ";
		for (Object key : map.keySet()) {
			if(!key.toString().equals("pageIndex")){
				if(key.toString().equals("stat_month")){
	    			
	    			sql += " and to_char(appl.create_time,'yyyy-mm')='"+map.get(key)+"'";
	    			
	    			sql =  String.format(sql,"yyyy-mm","yyyy-mm");
	    			
	    		}
	    		else if(key.toString().equals("stat_day")){
	    			
	    			sql += " and to_char(appl.create_time,'yyyy-mm-dd') like :find_month";
	    			
	    			sql =  String.format(sql,"yyyy-mm-dd","yyyy-mm-dd");
	    			
	    		}else{
	    			    			      			
	        		sql += " and acti."+ key.toString()+"="+map.get(key);
	        			        		
	    		}
	    		
			}
    		    		
 			   		
    	} 
		
		sql += " order by stat_day";
		Query query = session.createSQLQuery(sql).setResultTransformer(Transformers.aliasToBean(ActivityStat.class));
		query.setFirstResult(start);
		query.setMaxResults(pageSize);
		if(map.get("stat_day")!=null){
			
			query.setString("find_month", "%"+map.get("stat_day").toString()+"%");
			
		}
		list = query.list();
		return list;
		
	}
	
	
	public int count(Map map) throws Exception{
		int count = 0;
		Session  session =  sessionFactory.getCurrentSession();
		String sql = "select count(*) count  from app_activity_apply appl left join app_activity_list acti on appl.activity_id = acti.activity_id left join app_base_platform plat on acti.platform_id = plat.platform_id group by acti.activity_id,acti.platform_id,plat.platform_name,to_char(appl.create_time,'%s'),acti.subject having 1=1";
		for (Object key : map.keySet()) {
			if(!key.toString().equals("pageIndex")){
    		
	    		if(key.toString().equals("stat_month")){
	    			
	    			sql += " and to_char(appl.create_time,'yyyy-mm')='"+map.get(key)+"'";
	    			
	    			sql =  String.format(sql,"yyyy-mm","yyyy-mm");
	    			
	    		}
	    		else if(key.toString().equals("stat_day")){
	    			
	    			sql += " and to_char(appl.create_time,'yyyy-mm-dd') like :find_month";
	    			
	    			sql =  String.format(sql,"yyyy-mm-dd","yyyy-mm-dd");
	    			
	    		}else{
	    			    			      			
	        		sql += " and acti."+ key.toString()+"="+map.get(key);
	        			        		
	    		}
			}
    					   		
    	} 
		sql ="select count(*) from ("+sql+")"; 
		Query query = session.createSQLQuery(sql);
		if(map.get("stat_day")!=null){
			
			query.setString("find_month", "%"+map.get("stat_day").toString()+"%");
			
		}
		List list = query.list();
		if(list != null){								
			count = Integer.parseInt(list.get(0).toString());
		}
		return count;
		
	}
}
