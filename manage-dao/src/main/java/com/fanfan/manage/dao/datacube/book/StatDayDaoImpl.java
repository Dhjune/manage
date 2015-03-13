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
import com.fanfan.manage.modle.book.BookStatDay;
import com.fanfan.manage.modle.book.StatDaybook;

@Repository
public class StatDayDaoImpl extends AbstractDao{

	@Autowired
	private SessionFactory sessionFactory;
	
	public BookStatDay get (int bookId, int platId,String day)throws Exception{		
		
		BookStatDay statDay = null;
		Session session  =  sessionFactory.getCurrentSession();
		String sql =String.format("select statCount.* , (select book.book_title from app_book_list book where book_id = %s) bookName ,(select plat.platform_name from app_base_platform plat where book_id = %s) platName from app_book_stat_day statCount where statCount.book_id=%s and statCount.platfrom_id=%s and statCount.statMonth =%s",bookId,platId,bookId,platId,day);
		Query query =  session.createQuery(sql);
		List<BookStatDay> list = query.list();
		if(list!=null && list.size()>0){
			statDay =  list.get(0);
		}
		return statDay;	
	}
	
	public List<StatDaybook> list(int platId ,int start ,int pageSize,String day) throws Exception{
    	
		List<StatDaybook> list =null;
		Session session = sessionFactory.getCurrentSession();
		String sql =String.format("select statCount.platform_id,statCount.book_id,statCount.praise_num ,statCount.comment_num ,statCount.down_num ,statCount.read_num ,statCount.click_num ,book.book_title from app_book_stat_day statCount  left join app_book_list book on statCount.book_id = book.book_id where statCount.platform_id=%s and statCount.stat_day='%s'",platId,day);
		//Query query = session.createQuery("from BookStatCount statCount where statCount.pk.platForm =" + platId +" order by statCount.pk.book asc");
		Query query = session.createSQLQuery(sql).setResultTransformer(Transformers.aliasToBean(StatDaybook.class));
		query.setFirstResult(start);
		query.setMaxResults(pageSize);		
		list = query.list();
		return list;
		
	}
	
	 public List<StatDaybook> list(int start,int pageSize,Map map) throws Exception{
		 List<StatDaybook> list =null;
			Session session = sessionFactory.getCurrentSession();
			String sql = "select statCount.praise_num ,statCount.comment_num ,statCount.down_num ,statCount.read_num ,statCount.click_num ,book.book_title,book.book_id,plat.platform_id ,plat.platform_name from app_book_stat_day statCount  left join app_book_list book on statCount.book_id = book.book_id left join app_base_platform plat on statCount.platform_id=plat.platform_id where 1=1";
			//Query query = session.createQuery("from BookStatCount statCount where statCount.pk.platForm =" + platId +" order by statCount.pk.book asc");
			for (Object key : map.keySet()) {
				
				if(!key.toString().equals("pageIndex")){
					
					sql += " and statCount."+ key.toString()+"="+map.get(key);
					
				}
				
	    	} 
			Query query = session.createSQLQuery(sql).setResultTransformer(Transformers.aliasToBean(StatDaybook.class));
			query.setFirstResult(start);
			query.setMaxResults(pageSize);		
			list = query.list();
			return list;
	 }
	 
	 public int count(Map map) throws Exception{
		 int count = 0;
			Session session = sessionFactory.getCurrentSession();			
			String sql = "select count(*) from app_book_stat_day statCount  left join app_book_list book on statCount.book_id = book.book_id left join app_base_platform plat on statCount.platform_id=plat.platform_id where 1=1";
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
	
	 public int count(int platId,String day) throws Exception{
			int count = 0;
			Session session = sessionFactory.getCurrentSession();			
			String  hql  ="select count(*) from app_book_stat_day statCount where statCount.platform_id="+ platId+" and statCount.stat_day='"+day +"'";
			Query query = session.createSQLQuery(hql);
			List list =  query.list();		
			if(list != null){								
				count = Integer.parseInt(list.get(0).toString());
			}			
			
			return count;
		}

	public StatDaybook bookStatDay(int bookId, int platId,String day) throws Exception{
		StatDaybook  stat = null;
		Session session  =  sessionFactory.getCurrentSession();
		String sql = "";
		Query query = session.createSQLQuery(sql).setResultTransformer(Transformers.aliasToBean(StatDaybook.class));
		List<StatDaybook> list = null;
		list = query.list();
		if(list!=null && list.size()>0){
			stat = list.get(0);
		}
		return stat;
	}

	public List<StatDaybook> oneList(String bookId, String platId,
			String start_day, String end_day) throws Exception{
		List<StatDaybook> list = null;
		Session session =  sessionFactory.getCurrentSession();
		String sql =String.format("select statCount.stat_day,statCount.platform_id,statCount.book_id,statCount.praise_num ,statCount.comment_num ,statCount.down_num ,statCount.read_num ,statCount.click_num ,book.book_title from app_book_stat_day statCount  left join app_book_list book on statCount.book_id = book.book_id where statCount.platform_id=%s and statCount.book_id=%s and statCount.stat_day>=%s and statCount.stat_day<=%s order by statCount.stat_day asc",platId,bookId,start_day,end_day);
		//Query query = session.createQuery("from BookStatCount statCount where statCount.pk.platForm =" + platId +" order by statCount.pk.book asc");
		Query query = session.createSQLQuery(sql).setResultTransformer(Transformers.aliasToBean(StatDaybook.class));
		list = query.list();
		return list;
	}
	
	
}
