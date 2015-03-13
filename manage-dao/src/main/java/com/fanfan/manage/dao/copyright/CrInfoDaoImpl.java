package com.fanfan.manage.dao.copyright;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.fanfan.manage.api.demo.AbstractDao;
import com.fanfan.manage.modle.copyright.CrInfo;

@Repository
public class CrInfoDaoImpl extends AbstractDao{
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public CrInfo get(int id){
		
		CrInfo crInfo = null;		
		Session session = sessionFactory.getCurrentSession();
		crInfo = (CrInfo) session.get(CrInfo.class, id);
		return crInfo;		
		
	}
		
	public void save(CrInfo crInfo){
		
		Session session =  sessionFactory.getCurrentSession();
		session.save(crInfo);
		
	}
	
	public void update(CrInfo crInfo){
		
		Session session  =  sessionFactory.getCurrentSession();
		session.update(crInfo);
		
		
	}
	
	public void delete(CrInfo crInfo){
		
		Session session  = sessionFactory.getCurrentSession();
		session.delete(crInfo);
		
	}



	public List<CrInfo> getListByBookId(int bookId) {
		
		List<CrInfo> list = null;
		Session session =sessionFactory.getCurrentSession();
		String hql = "from CrInfo crInfo where crInfo.status=1 and crInfo.bookId="+bookId;
		Query query = session.createQuery(hql);
		list= query.list();
		return list;
		
	}
}
