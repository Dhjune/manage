package com.fanfan.manage.dao.book;

import com.fanfan.manage.api.book.dao.BookDaoItl;
import com.fanfan.manage.modle.book.Author;
import com.fanfan.manage.modle.book.Book;
import com.fanfan.manage.modle.book.BookType;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.jdbc.Work;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Created with IntelliJ IDEA.
 * User: fanfan
 * Date: 14-3-3
 * Time: 上午9:42
 * To change this template use File | Settings | File Templates.
 */
@Repository
public class BookDaoImpl implements BookDaoItl{

	@Autowired
	private SessionFactory sessionFactory;
	
    public Book get() {
    	
        Book book = null ;       
        return book;  
    }
    
    public Book  get(int id) throws Exception{
    	Book book = null;
    	Session session  =  sessionFactory.getCurrentSession();
    	book = (Book) session.get(Book.class, id);
    	return book;
    	
    }
    
    /**
     *  @这样的连接查询，效率较低，而且只能是作为单个进行查询，通常用在一对多时查询。当需要多对多查询时，就完全不在控制范围了。
     */
    public Book get(int id,int typeshow) throws Exception{
        Book book = null ;
        Session session = sessionFactory.getCurrentSession();
        //去除掉if语句还是可以用的，不过相对来说还是有些不足之处。
		//Query query = session.createQuery("select * from Book as book left join fetch book.booktypes as types where if types.id >0 then  types.status = 1 or types.status=0 and  endif  book.id="+id);
		//Query query =  session.createSQLQuery("select book.book_id from app_book_list book left join app_book_type_ref ref on book.book_id=ref.book_id left join app_book_type type on ref.type_id = type.type_id  where book.book_id = 2 and type.status =1");
		//query.setFirstResult(1);
		//query.setMaxResults(1);	
		//System.out.println("debug");
		//@SuppressWarnings("unchecked")
		//List list =  query.list();
		
		//Iterator it=list.iterator(); 
		//System.out.println("the list size :"+list.size());
        
        
	//if(list.size()>0){			
			//list.get(0);	
			//System.out.println("the list size :"+list.get(0).toString());
	//}
		
//		System.out.println("the list size :"+ book);
//		while(it.hasNext()){  
//
//             book=(Book)it.next();  
//             System.out.println("teh book get one");
//              
//            //System.out.println("the book booktitle is :" +book.getBookTitle());        
//           // System.out.println("the book types size : "+book.getBooktypes().size());  
//
//           
//		}		
		
//		while(it.hasNext()){  
//
//            Object[] obj=(Object[])it.next();  
//            Book boo=(Book)obj[0];  
//            BookType type=(BookType)obj[1];  
//            System.out.println("the book title is"+boo.getBookTitle());  
//            if(type!=null){  
//             System.out.println("the type name is "+type.getName());  
//             }else{  
//                   System.out.println();  
//             }
//		}
		book =  (Book) session.get(Book.class, id);
		if(typeshow>0){
			book.getBooktypes();		
		//容器中的数据，必须通过初次使用才能初始化		
			book.getBooktypes().size();	
		}
        return book; 
        
    }
    
    public Book get(int id,boolean show) throws Exception{
        Book book = null ;
        Session session = sessionFactory.getCurrentSession();		
		book =  (Book) session.get(Book.class, id);		
		if(show){
			book.getAuthor();
			book.getTransTeam();
		}								
        return book; 
        
    }

    public List<Book> list() throws Exception{
    	
        List<Book> list = null;
      
        return null;  
    }
    
    public List<Book> list(Map map) throws Exception{
    	
    	List<Book> list = null;
    	Session session  = sessionFactory.getCurrentSession();
    	String hql="from Book book  where 1=1 ";
    	if(map.size()>0 && map.get("typeId")!=null){
    		hql +=" and types.id="+map.get("typeId");
    	}    	
    	Query query = session.createQuery(hql);
    	list = query.list();
    	
    	return list;
    }
    
    public List<Book> list(int start,int pageSize,Map map) throws Exception{
    	List<Book> list = null;
    	Session session  = sessionFactory.getCurrentSession();
    	String hql ="from Book book  where 1=1 ";
    	for (Object key : map.keySet()) {
    		if(!key.toString().equals("pageIndex")){
    			
	    		if(key.toString().equals("search")){
					
					hql += " and book.bookTitle like :book_title";
					
				}else{
					
					hql +=" and book."+key.toString()+"="+map.get(key.toString());
					
				}
    		}	
    	} 
    	
    	hql += " order by book.id desc";
    	Query query = session.createQuery(hql);
    	if(map.get("search")!=null){
    		query.setString("book_title", "%"+map.get("search")+"%");
    	}
    	query.setFirstResult(start);
		query.setMaxResults(pageSize);	
    	list = query.list();
    	return list;
    }
    
    public List<Book> list(int start ,int pageSize,int status ) throws Exception{
    	
		List<Book> list =null;
		//List<Map> list = null;
		Session session = sessionFactory.getCurrentSession();	
		Query query = session.createQuery("from Book book where book.status ="+ status + " order by book.id desc");
		//Query query = session.createSQLQuery("select * from app_book_list");
		query.setFirstResult(start);
		query.setMaxResults(pageSize);	
		query.setCacheable(true);
		list = query.list();
//		for(int i = 0;i<list.size();i++){
//			Object[] obj=(Object[])list.get(i);
//			System.out.println(" the book is:"+obj[0]);
//		}
		/*
		//JDBC 获取jdbc Connection，执行存储过程！
		session.doWork(new Work(){
			
			public void execute(Connection connection) {
				//JDBC Connection 不能关闭
				try {
					CallableStatement csmt = connection.prepareCall("{call proc_test(?,?)}");
					csmt.setInt(1,8);
					csmt.registerOutParameter(2, Types.INTEGER);
					csmt.execute();
					//打印存储过程的返回值。
					System.out.println("list size is"+ csmt.getInt(2));
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		});
		*/		
//		Query q_test =  session.getNamedQuery("proc_test").setInteger("b_id", 8);
//		List ls= q_test.list();
//		if(ls !=null){
//			System.out.println("list size is"+ ls.size());
//		}
//		
//		for(int i=0;i<list.size();i++){
//			Book bk = list.get(i);
//			bk.getAuthor().getName();
//			System.out.println("/"+bk.getId());
//		}
		
		return list;
		
	}
	
    
    public List<Book> search(int start,int pageSize,Map map) throws Exception{
    	List<Book> list = null;
    	Session session  =  sessionFactory.getCurrentSession();
    	String hql =  "from Book book where book.bookTitle like :book_title or book.briefIntro like :brief_intro  order by book.id desc";
    	Query query = session.createQuery(hql);
    	query.setString("book_title", "%"+map.get("value")+"%");
    	query.setString("brief_intro", "%"+map.get("value")+"%");
    	query.setFirstResult(start);
		query.setMaxResults(pageSize);	
		list = query.list();
    	return list;
    }
	
    public int countSearch(Map map) throws Exception{
    	
    	int count = 0;
    	Session session  =  sessionFactory.getCurrentSession();
    	String  sql  ="select count(*) from app_book_list book where book.book_title like :book_title or book.brief_intro like :brief_intro";   	
    	Query query = session.createSQLQuery(sql);
    	query.setString("book_title", "%"+map.get("value")+"%");
    	query.setString("brief_intro", "%"+map.get("value")+"%");
    	List list =  query.list();
    	if(list != null){
    		
			count = Integer.parseInt(list.get(0).toString());
			
		}				
		return count;
   	
    }
    
    public int count(Map map) throws Exception{
    	
    	int count = 0;
    	String  sql  ="select count(*) from Book book where 1=1";   
    	Session session  =  sessionFactory.getCurrentSession();
    	
    	for (Object key : map.keySet()) {
    		
    		if(!key.toString().equals("pageIndex")){
    			
    			if(key.toString().equals("search")){
    				
    				sql += " and book.bookTitle like :book_title";
    				
    			}else{
    				
    				sql +=" and book."+key.toString()+"="+map.get(key.toString());
    				
    			}
    			
    		}
    	}
    	
    	Query query = session.createQuery(sql);
    	if(map.get("search")!=null){
    		query.setString("book_title", "%"+map.get("search")+"%");
    	}
    	
    	List list =  query.list();
    	if(list != null){								
			count = Integer.parseInt(list.get(0).toString());
		}				
		return count;
   	
    }
    
	public int count() throws Exception{
		int count = 0;
		Session session = sessionFactory.getCurrentSession();		
		String  hql  ="select count(*) from Book book where book.status=";
		Query query = session.createQuery(hql);
		List list =  query.list();		
		if(list != null){								
			count = Integer.parseInt(list.get(0).toString());
		}				
		return count;
		
	}
	
	public int count(int status) throws Exception{
		int count = 0;
		Session session = sessionFactory.getCurrentSession();		
		String  hql  ="select count(*) from Book book where book.status="+status;
		Query query = session.createQuery(hql);
		
		List list =  query.list();		
		if(list != null){								
			count = Integer.parseInt(list.get(0).toString());
		}				
		return count;
		
	}

    public void put(Book obj) throws Exception{
    	
    	Session session = sessionFactory.getCurrentSession();	
		session.save(obj);
		
    }

    public void update(Book obj) throws Exception{
    	Session session = sessionFactory.getCurrentSession();
		
		session.update(obj);
    }
    
    public void merge(Book obj) throws Exception{
    	Session session  = sessionFactory.getCurrentSession();
    	session.merge(obj);
    }

    public void delete(Book obj) throws Exception{
    	Session session = sessionFactory.getCurrentSession();
		
		session.delete(obj);
		
    }
    
    
    
    public boolean confirm(Book obj) throws Exception{
    	
		Session session = sessionFactory.getCurrentSession();		
		Query query = session.createQuery("from Book b where b.bookTitle = '"+obj.getBookTitle()+"'" +" and b.author="+obj.getAuthor().getId());
		List<Author> list = query.list();		
		if ( list != null && list.size()>0){
			return true;
		}else{
			return false;
		}
	}
    
    
}
