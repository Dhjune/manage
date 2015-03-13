package com.fanfan.manage.dao.admin;

import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.fanfan.manage.api.demo.AbstractDao;
import com.fanfan.manage.modle.admin.AdminUserRole;

@Repository
public class AdminUserRoleDaoImpl extends AbstractDao{
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public AdminUserRole get(int adminId ) throws Exception{
		AdminUserRole userRole = null;
		List<AdminUserRole> list = null;
		Session session = sessionFactory.getCurrentSession();
		String hql = "from AdminUserRole uRole where uRole.uk.user="+adminId;
		Query query = session.createQuery(hql);
		list = query.list();
		if(list!=null && list.size()>0){
			userRole = list.get(0);
		}
		return userRole;
	}
	
	public void put(AdminUserRole userRole) throws Exception{
		
		Session session =  sessionFactory.getCurrentSession();
		session.save(userRole);
		
	}
	
	public boolean confirm(AdminUserRole userRole)throws Exception{
		AdminUserRole uRole = null;
		Session session = sessionFactory.getCurrentSession();
		String sql = "select count(*) from app_admin_user_role user_role where user_role.admin_id="+userRole.getUk().getUser().getId();		
		Query query = session.createSQLQuery(sql);
		List list =  query.list();
		if(list!=null &&list.size()>0){
			Object object = list.get(0);
			if(Integer.parseInt(object.toString())>0){
				return true;
			}
			
		}	
		System.out.println("bucunzai");
		return false;
	}
	
	public AdminUserRole update(AdminUserRole userRole) throws Exception{
		AdminUserRole uRole = null;
		
		Session session =sessionFactory.getCurrentSession();
		String sql = "update app_admin_user_role uRole set uRole.admin_role_id=:role_id,uRole.update_time=:update_time where uRole.admin_id =:admin_id";
		Query query = session.createSQLQuery(sql);
		query.setInteger("role_id", userRole.getUk().getRole().getId());
		query.setDate("update_time", new Date());
		query.setInteger("admin_id", userRole.getUk().getUser().getId());
		query.executeUpdate();
		uRole =  get(userRole.getUk().getUser().getId());
		return uRole;
	}
	
}
