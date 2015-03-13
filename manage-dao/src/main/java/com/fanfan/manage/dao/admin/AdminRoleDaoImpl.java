package com.fanfan.manage.dao.admin;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.fanfan.manage.api.demo.AbstractDao;
import com.fanfan.manage.modle.admin.AdminRole;
import com.fanfan.manage.modle.admin.AdminUser;

@Repository
public class AdminRoleDaoImpl extends AbstractDao{
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public AdminRole get(int id) throws Exception{
		AdminRole role = null ;
		Session session  =sessionFactory.getCurrentSession();
		role = (AdminRole) session.get(AdminRole.class, id);
		return role;
	}
	
	
	/*
	 * hibernate面向对象查询方式
	 *1HQL TDO 映射
	 *2DTO 查询结果的另一种形式
 	 *3非DTO BEAN映射形式
	*/
	public List<AdminRole> list(int start, int pageSize) throws Exception{
		List<AdminRole> list = null;
		Session session = sessionFactory.getCurrentSession();
		String sql ="select {aRole.*} from app_admin_role aRole where aRole.status=1 ";
		//String hql = "from AdminRole aRole connect by prior aRole.id = aRole.pid where aRole.status = 1";
		Query query  =  session.createSQLQuery(sql).addEntity("aRole", AdminRole.class);
		query.setFirstResult(start);
		query.setMaxResults(pageSize);	
		list = query.list();
		return list;
	}

	public int count() {
		int count = 0 ;
		Session session = sessionFactory.getCurrentSession();		
		String  hql  ="select count(*) from AdminRole aRole where aRole.status=1";
		Query query = session.createQuery(hql);
		List list =  query.list();		
		if(list != null){								
			count = Integer.parseInt(list.get(0).toString());
		}	
		return count;
		
	}

	public boolean confirm(AdminRole role) throws Exception{
		Session session = sessionFactory.getCurrentSession();
		List<AdminRole> list = null;
		String hql = " from AdminRole aRole where aRole.name ='"+role.getName()+"'";
		Query query = session.createQuery(hql);
		list = query.list();
		if(list!=null && list.size()>0){
			return true;
		}
		return false;
	}

	public void put(AdminRole role) throws Exception{
		Session session  =  sessionFactory.getCurrentSession();
		session.save(role);
		
	}


	public void update(AdminRole role) throws Exception{
		Session session = sessionFactory.getCurrentSession();
		session.merge(role);
		
	}
	
	
}
