package com.fanfan.manage.dao.copyright;

import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.fanfan.manage.api.demo.AbstractDao;
import com.fanfan.manage.modle.book.Book;
import com.fanfan.manage.modle.book.StatCountbook;
import com.fanfan.manage.modle.copyright.CrBook;
import com.fanfan.manage.modle.copyright.CrBookFile;
import com.fanfan.manage.modle.copyright.CrVList;

@Repository
public class CrBookDaoImpl extends AbstractDao{
	@Autowired
	private SessionFactory sessionFactory;
	
	public CrBook get(int id){
		
		CrBook crBook = null;		
		Session session = sessionFactory.getCurrentSession();
		crBook = (CrBook) session.get(CrBook.class, id);
		return crBook;		
		
	}
	
	
	
	public void save(CrBook crBook){
		Session session =  sessionFactory.getCurrentSession();
		session.save(crBook);
	}
	
	public void update(CrBook crBook){
		Session session  =  sessionFactory.getCurrentSession();
		session.update(crBook);
		
	}
	
	public void delete(CrBook crBook){
		Session session  = sessionFactory.getCurrentSession();
		session.delete(crBook);
		
	}



	public List<CrVList> list(int start, int pageSize) {
		List<CrVList> list = null;
		Session session = sessionFactory.getCurrentSession();
		String sql =String.format("select book.book_id, book.book_name ,book.pen_name ,book.book_type ,book.country ,book.book_color ,book.book_length ,book.update_status,info.cr_nature,info.deadline,book.status from app_copyright_book book left join ( select * from app_copyright_info info1 join (select min(info_id)if_id from app_copyright_info group by book_id )info2  on info1.info_id = info2.if_id ) info on book.book_id = info.book_id where book.status=1  order by book.book_id desc");
		
		//String hql = "from CrBook book where book.status=1 order by book.id desc";
		//Query query= session.createQuery(hql);
		Query query = session.createSQLQuery(sql).setResultTransformer(Transformers.aliasToBean(CrVList.class));
		query.setFirstResult(start);
		query.setMaxResults(pageSize);		
		list = query.list();
//		if(list!=null&&list.size()>0){
//			for(CrBook book :list){
//				System.out.println("sgas:"+book.getInfos().size());
//			}
//		}
		return list;
	}
	
	public int count(){
		int count = 0;		
    	String  sql  ="select count(*) from CrBook book where book.status=1";   
    	Session session  =  sessionFactory.getCurrentSession();   	   	
    	Query query = session.createQuery(sql);   	   	
    	List list =  query.list();
    	if(list != null){								
			count = Integer.parseInt(list.get(0).toString());
		}	
    	
		return  count;
	}



	public int count(Map map) {
		int count = 0;
    	String  hql  ="select count(*) from CrBook book where book.status=1";   
    	Session session  =  sessionFactory.getCurrentSession();
    	
    	for (Object key : map.keySet()) {
    		if(!key.toString().equals("pageIndex")){
    			
	    		if(key.toString().equals("search")){
					
	    			hql += " and book.bookName like :book_title or book.penName like :author_title";
					
				}
    		}	
    	} 
    	
    	Query query = session.createQuery(hql);
    	if(map.get("search")!=null){
    		query.setString("book_title", "%"+map.get("search")+"%");
    		query.setString("author_title", "%"+map.get("search")+"%");
    	}
    	
    	List list =  query.list();
    	if(list != null){								
			count = Integer.parseInt(list.get(0).toString());
		}				
		return count;
		
	}



	public List<CrVList> list(int start, int pageSize, Map map) {
		List<CrVList> list =null;
		String sql =String.format("select book.book_id, book.book_name ,book.pen_name ,book.book_type ,book.country ,book.book_color ,book.book_length ,book.update_status,info.cr_nature,info.deadline,book.status from app_copyright_book book left join ( select * from app_copyright_info info1 join (select min(info_id)if_id from app_copyright_info group by book_id )info2  on info1.info_id = info2.if_id ) info on book.book_id = info.book_id where book.status=1 ");
		Session session  = sessionFactory.getCurrentSession();
    	//String sql ="from CrBook book  where book.status =1 ";
    	for (Object key : map.keySet()) {
    		if(!key.toString().equals("pageIndex")){
    			
	    		if(key.toString().equals("search")){
					
	    			sql += " and book.book_name like :book_title or book.pen_name like :author_title";
					
				}
    		}	
    	} 
    	
    	sql += " order by book.book_id desc";
    
    	//sql += " order by book.id desc";
    	//Query query =session.createQuery(sql);
    	
    	Query query = session.createSQLQuery(sql).setResultTransformer(Transformers.aliasToBean(CrVList.class));;
    	if(map.get("search")!=null){
    		query.setString("book_title", "%"+map.get("search")+"%");
    		query.setString("author_title", "%"+map.get("search")+"%");
    	}
    	query.setFirstResult(start);
		query.setMaxResults(pageSize);	
    	list = query.list();
//    	if(list!=null&&list.size()>0){
//			for(CrBook book :list){
//				System.out.println("sdfhsrth:"+book.getInfos().size());
//			}
//		}
		return list;
	}
}
