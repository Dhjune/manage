package com.fanfan.manage.dao.admin;

import java.util.List;
import java.util.Map;












import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.fanfan.manage.api.demo.AbstractDao;
import com.fanfan.manage.modle.admin.AdminLoginInfo;
import com.fanfan.manage.modle.admin.AdminUser;

@Repository
public class AdminUserDaoImpl extends AbstractDao{
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public AdminLoginInfo login(Map map) throws Exception{
		AdminLoginInfo info = null;
		List list = null;
		Session session  =  sessionFactory.getCurrentSession();		
		String sql = "select u.admin_id,u.login_name,u.password,role.role_name,role.admin_role_id from app_admin_user u left join app_admin_user_role user_role on u.admin_id = user_role.admin_id left join app_admin_role role on user_role.admin_role_id = role.admin_role_id where u.login_name = :user_name";
		Query query = session.createSQLQuery(sql).setResultTransformer(Transformers.aliasToBean(AdminLoginInfo.class));
		query.setString("user_name", map.get("login_name").toString());
		list =  query.list();
		if(list!=null && list.size()>0){
			info  = (AdminLoginInfo) list.get(0);
			if(info.getPassword().equals(map.get("password").toString())){
				info.setSuccess(true);
				info.setMessage("登录成功，可以进行操作！");
			}else{
				info.setSuccess(false);
				info.setMessage("登录失败，密码错误，请核对密码!");
			}
		}else{
			info = new AdminLoginInfo();
			info.setSuccess(false);
			info.setMessage("登录账号无效，请核对登录账号!");
		}
				
		return info;
	}
	
	public void put(AdminUser user)throws Exception{
		Session session =  sessionFactory.getCurrentSession();
		session.save(user);
	}
	
	public boolean confirm(AdminUser user)throws Exception{
		Session session = sessionFactory.getCurrentSession();
		List<AdminUser> list = null;
		String hql = " from AdminUser aUser where aUser.name ='"+user.getName()+"'";
		Query query = session.createQuery(hql);
		list = query.list();
		if(list!=null && list.size()>0){
			return true;
		}
		return false;
	}
	
	public boolean logout(Map map)throws Exception{
		
		boolean success = false;
		AdminUser info = null;
		Session session = sessionFactory.getCurrentSession();
		info = (AdminUser) session.get(AdminUser.class, Integer.parseInt(map.get("admin_id").toString()));
		if(info != null){
			success = true;
		}
		return success;
		
	}

	public List<AdminUser> list(int start, int pageSize,Map map) throws Exception{
		List<AdminUser> list = null;
		Session session = sessionFactory.getCurrentSession();
		String hql = "from AdminUser aUser where aUser.status = 1 and aUser.id !="+map.get("admin_id");
		Query query  =  session.createQuery(hql);
		query.setFirstResult(start);
		query.setMaxResults(pageSize);	
		list = query.list();
		for(AdminUser item : list){
			item.getRoles().size();
		}
		return list;
	}
	
	public int count(Map map) throws Exception{
		
		int count = 0 ;
		Session session = sessionFactory.getCurrentSession();		
		String  hql  ="select count(*) from AdminUser aUser where aUser.status=1 and aUser.id !="+map.get("admin_id");
		Query query = session.createQuery(hql);
		List list =  query.list();		
		if(list != null){								
			count = Integer.parseInt(list.get(0).toString());
		}	
		return count;
	}

	public AdminUser get(int id) throws Exception{
		AdminUser aUser =null;
		Session session  =sessionFactory.getCurrentSession();
		aUser = (AdminUser) session.get(AdminUser.class, id);
		if(aUser!=null){
			aUser.getRoles().size();
		}
		return aUser;
	}
	
	public void update(AdminUser aUser) throws Exception{
		
		Session session = sessionFactory.getCurrentSession();
		
		session.merge(aUser);
		
		
	}
	
}
