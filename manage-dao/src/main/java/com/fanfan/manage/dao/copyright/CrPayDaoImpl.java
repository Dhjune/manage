package com.fanfan.manage.dao.copyright;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.fanfan.manage.api.demo.AbstractDao;
import com.fanfan.manage.modle.copyright.CrInfo;
import com.fanfan.manage.modle.copyright.CrPay;

@Repository
public class CrPayDaoImpl extends AbstractDao{
	@Autowired
	private SessionFactory sessionFactory;
	
	public CrPay get(int id){
		
		CrPay crPay = null;		
		Session session = sessionFactory.getCurrentSession();
		crPay = (CrPay) session.get(CrPay.class, id);
		return crPay;		
		
	}
	
	public void save(CrPay crPay){
		Session session =  sessionFactory.getCurrentSession();
		session.save(crPay);
	}
	
	public void update(CrPay crPay){
		Session session  =  sessionFactory.getCurrentSession();
		session.update(crPay);
		
	}
	
	public void delete(CrPay crPay){
		Session session  = sessionFactory.getCurrentSession();
		session.delete(crPay);
		
	}
	
	public List<CrPay> getListByBookId(int bookId) {
		
		List<CrPay> list = null;
		Session session =sessionFactory.getCurrentSession();
		String hql = "from CrPay crPay where crPay.status=1 and crPay.bookId="+bookId;
		Query query = session.createQuery(hql);
		list= query.list();
		return list;
		
	}
}
