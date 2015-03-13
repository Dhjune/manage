package com.fanfan.manage.dao.datacube.book;

import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.fanfan.manage.api.demo.AbstractDao;
import com.fanfan.manage.modle.book.BookStatCount;
import com.fanfan.manage.modle.book.StatCountbook;
import com.fanfan.manage.modle.user.UserEntity;
import com.fanfan.manage.modle.user.UserRead;

@Repository
public class StatCountDaoImpl extends AbstractDao{
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public BookStatCount get (int bookId, int platId) throws Exception{		
		
		BookStatCount statCount = null;
		Session session  =  sessionFactory.getCurrentSession();
		String sql =String.format("select statCount.* , (select book.book_title from app_book_list book where book_id = %s) bookName ,(select plat.platform_name from app_base_platform plat where book_id = %s) platName from app_book_stat_count statCount where statCount.book_id=%s and statCount.platfrom_id=%s",bookId,platId,bookId,platId);
		Query query =  session.createQuery(sql);
		List<BookStatCount> list = query.list();
		if(list!=null && list.size()>0){
			statCount =  list.get(0);
		}
		return statCount;	
	}
	
	public List<StatCountbook> list(int platId ,int start ,int pageSize) throws Exception{
    	
		List<StatCountbook> list =null;
		Session session = sessionFactory.getCurrentSession();
		String sql =String.format("select statCount.praise_num ,statCount.comment_num ,statCount.down_num ,statCount.read_num ,statCount.click_num ,book.book_title from app_book_stat_count statCount  left join app_book_list book on statCount.book_id = book.book_id where statCount.platform_id=%s",platId);
		//Query query = session.createQuery("from BookStatCount statCount where statCount.pk.platForm =" + platId +" order by statCount.pk.book asc");
		Query query = session.createSQLQuery(sql).setResultTransformer(Transformers.aliasToBean(StatCountbook.class));
		query.setFirstResult(start);
		query.setMaxResults(pageSize);		
		list = query.list();
		
		return list;
		
	}
	
	public List<StatCountbook> list(int start,int pageSize,Map map) throws Exception{
		List<StatCountbook> list = null;
		Session session = sessionFactory.getCurrentSession();
		String sql = "select statCount.praise_num ,statCount.comment_num ,statCount.down_num ,statCount.read_num ,statCount.click_num ,book.book_title ,plat.platform_name from app_book_stat_count statCount  left join app_book_list book on statCount.book_id = book.book_id left join app_base_platform plat on statCount.platform_id=plat.platform_id where 1=1";
		String order = "";
		
		for (Object key : map.keySet()) {
    		
    		if(!key.toString().equals("pageIndex")){
    			
    			if(key.toString().equals("search")){
					
	    			sql += " and book.book_title like :book_title";
					
				}else if(key.toString().equals("book")){
					
					sql +=" and statCount.book_id="+map.get(key.toString());
					
				}else if(key.toString().equals("platform_id")){
					
					sql += " and statCount."+key.toString()+"="+map.get(key.toString()); 
					
				}
    		}	
    	} 

    	Query query = session.createSQLQuery(sql).setResultTransformer(Transformers.aliasToBean(StatCountbook.class));
    	if(map.get("search")!=null){
    		query.setString("book_title", "%"+map.get("search")+"%");
    	}
    	query.setFirstResult(start);
		query.setMaxResults(pageSize);			
    	list = query.list();
		return list;
	}
	
	
	public int count(Map map) throws Exception{
		int count = 0;
		Session session = sessionFactory.getCurrentSession();
		String sql = "select count(*) from app_book_stat_count statCount  left join app_book_list book on statCount.book_id = book.book_id left join app_base_platform plat on statCount.platform_id=plat.platform_id where 1=1";
		for (Object key : map.keySet()) {
    		
    		if(!key.toString().equals("pageIndex")){
    			
    			if(key.toString().equals("search")){
					
	    			sql += " and book.book_title like :book_title";
					
				}else if(key.toString().equals("book")){
					
					sql +=" and statCount.book_id="+map.get(key.toString());
					
				}else if(key.toString().equals("platform_id")){
					
					sql += " and statCount."+key.toString()+"="+map.get(key.toString()); 
					
				}
    		}	
    	} 
		Query query = session.createSQLQuery(sql);
		if(map.get("search")!=null){
    		query.setString("book_title", "%"+map.get("search")+"%");
    	}
		List list =  query.list();		
		if(list != null){								
			count = Integer.parseInt(list.get(0).toString());
		}	
		return count;
	}
	
	 public int count(int platId) throws Exception{
			int count = 0;
			Session session = sessionFactory.getCurrentSession();			
			String  hql  ="select count(*) from BookStatCount statCount where statCount.pk.platForm ="+ platId;
			Query query = session.createQuery(hql);
			List list =  query.list();		
			if(list != null){								
				count = Integer.parseInt(list.get(0).toString());
			}			
			
			return count;
		}

	public StatCountbook bookStatCount(int bookId, int platId) throws Exception{
		StatCountbook  stat = null;
		Session session  =  sessionFactory.getCurrentSession();
		String sql = "";
		Query query = session.createSQLQuery(sql).setResultTransformer(Transformers.aliasToBean(StatCountbook.class));
		List<StatCountbook> list = null;
		list = query.list();
		if(list!=null && list.size()>0){
			stat = list.get(0);
		}
		return stat;
	}
	
}
