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
import com.fanfan.manage.modle.book.BookStatMonth;
import com.fanfan.manage.modle.book.StatCountbook;
import com.fanfan.manage.modle.book.StatMonthbook;

@Repository
public class StatMonthDaoImpl extends AbstractDao{
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public BookStatMonth get (int bookId, int platId,String month) throws Exception{		
		
		BookStatMonth statMonth = null;
		Session session  =  sessionFactory.getCurrentSession();
		String sql =String.format("select statCount.* , (select book.book_title from app_book_list book where book_id = %s) bookName ,(select plat.platform_name from app_base_platform plat where book_id = %s) platName from app_book_stat_count statCount where statCount.book_id=%s and statCount.platfrom_id=%s and statCount.statMonth =%s",bookId,platId,bookId,platId,month);
		Query query =  session.createQuery(sql);
		List<BookStatMonth> list = query.list();
		if(list!=null && list.size()>0){
			statMonth =  list.get(0);
		}
		return statMonth;	
	}
	
	public List<StatMonthbook> list(int platId ,int start ,int pageSize,String month) throws Exception{
    	
		List<StatMonthbook> list =null;
		Session session = sessionFactory.getCurrentSession();
		String sql =String.format("select statCount.stat_month,statCount.platform_id,statCount.book_id,statCount.praise_num ,statCount.comment_num ,statCount.down_num ,statCount.read_num ,statCount.click_num ,book.book_title from app_book_stat_month statCount  left join app_book_list book on statCount.book_id = book.book_id where statCount.platform_id=%s and statCount.stat_month=%s",platId,month);
		//Query query = session.createQuery("from BookStatCount statCount where statCount.pk.platForm =" + platId +" order by statCount.pk.book asc");
		Query query = session.createSQLQuery(sql).setResultTransformer(Transformers.aliasToBean(StatMonthbook.class));
		query.setFirstResult(start);
		query.setMaxResults(pageSize);		
		list = query.list();
		return list;
		
	}
	
	public List<StatMonthbook> list(int start,int pageSize,Map map) throws Exception{
		List<StatMonthbook> list = null;
		Session session = sessionFactory.getCurrentSession();
		String sql = "select statCount.praise_num ,statCount.comment_num ,statCount.down_num ,statCount.read_num ,statCount.click_num ,book.book_title ,book.book_id,plat.platform_id,plat.platform_name from app_book_stat_month statCount  left join app_book_list book on statCount.book_id = book.book_id left join app_base_platform plat on statCount.platform_id=plat.platform_id where 1=1";
		
		for (Object key : map.keySet()) {
			
			if(!key.toString().equals("pageIndex")){
				
				sql += " and statCount."+ key.toString()+"="+map.get(key);
		
			}
			
    	} 
		Query query = session.createSQLQuery(sql).setResultTransformer(Transformers.aliasToBean(StatMonthbook.class));
		query.setFirstResult(start);
		query.setMaxResults(pageSize);		
		list = query.list();
		return list;
	}
	
	public int count(Map map) throws Exception{
		int count = 0 ;
		Session session = sessionFactory.getCurrentSession();
		String sql = "select count(*) from app_book_stat_month statCount  left join app_book_list book on statCount.book_id = book.book_id left join app_base_platform plat on statCount.platform_id=plat.platform_id where 1=1";
		for (Object key : map.keySet()) {
    		
    		if(!key.toString().equals("pageIndex")){
    			
    			sql += " and statCount."+ key.toString()+"="+map.get(key);
    			
    		}	
    	} 
		Query query = session.createSQLQuery(sql);
		List list =  query.list();		
		if(list != null){								
			count = Integer.parseInt(list.get(0).toString());
		}	
		
		return count;
	}
	
	
	public List<StatMonthbook> oneList(String bookId,String platId,String start_month,String end_month) throws Exception{
		List<StatMonthbook> list = null;
		Session session = sessionFactory.getCurrentSession();
		String sql =  String.format("select statCount.stat_month,statCount.platform_id,statCount.book_id,statCount.praise_num ,statCount.comment_num ,statCount.down_num ,statCount.read_num ,statCount.click_num ,book.book_title from app_book_stat_month statCount  left join app_book_list book on statCount.book_id = book.book_id where statCount.platform_id=%s and statCount.book_id=%s and statCount.stat_month>=%s and statCount.stat_month<=%s order by statCount.stat_month asc",platId,bookId,start_month,end_month);
		Query query =  session.createSQLQuery(sql).setResultTransformer(Transformers.aliasToBean(StatMonthbook.class));
		list = query.list();
		return list;
	}
	
	 public int count(int platId,String month) throws Exception{
			int count = 0;
			Session session = sessionFactory.getCurrentSession();			
			String  hql  ="select count(*) from app_book_stat_month statCount where statCount.platform_id ="+ platId+" and statCount.stat_month="+month ;
			Query query = session.createSQLQuery(hql);
			List list =  query.list();		
			if(list != null){								
				count = Integer.parseInt(list.get(0).toString());
			}			
			
			return count;
		}

	public StatMonthbook bookStatMonth(int bookId, int platId,String month) throws Exception{
		StatMonthbook  stat = null;
		Session session  =  sessionFactory.getCurrentSession();
		String sql = "";
		Query query = session.createSQLQuery(sql).setResultTransformer(Transformers.aliasToBean(StatMonthbook.class));
		List<StatMonthbook> list = null;
		list = query.list();
		if(list!=null && list.size()>0){
			stat = list.get(0);
		}
		return stat;
	}
	
}
