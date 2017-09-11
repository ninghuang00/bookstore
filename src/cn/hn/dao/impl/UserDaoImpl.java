package cn.hn.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import cn.hn.dao.UserDao;
import cn.hn.domain.Privilege;
import cn.hn.domain.User;
import cn.hn.utils.JdbcUtils;

public class UserDaoImpl implements UserDao {
	/* (non-Javadoc)
	 * @see cn.hn.dao.impl.UserDao#add(cn.hn.domain.User)
	 */
	@Override
	public void add(User user){
		try {
			QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());
			String sql = "insert into user values(?,?,?,?,?,?)";
			Object[] params = {user.getId(),user.getUsername(),user.getPassword(),user.getPhonenumber(),user.getAddress(),user.getEmail()};
			runner.update(sql,params);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	/* (non-Javadoc)
	 * @see cn.hn.dao.impl.UserDao#find(java.lang.String)
	 */
	@Override
	public User find(String id){
		
		try {
			QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());
			String sql = "select * from user where id=?";
			return runner.query(sql, id, new BeanHandler<>(User.class));
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	/* (non-Javadoc)
	 * @see cn.hn.dao.impl.UserDao#find(java.lang.String, java.lang.String)
	 */
	@Override
	public User find(String username,String password){
		try {
			QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());
			String sql = "select * from user where username=? and password=?";
			Object[] params = {username,password};
			return runner.query(sql, params, new BeanHandler<>(User.class));
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
	}
	
	/* (non-Javadoc)
	 * @see cn.hn.dao.impl.UserDao#remove(java.lang.String)
	 */
	@Override
	public void remove(String id){
		try {
			QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());
			String sql = "delete from user where id=?";
			runner.update(sql,id);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	@Override
	public List<Privilege> getPrivileges(String id){
		
		try {
			QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());
			String sql = "select p.* from privilege p,user_privilege up where up.user_id=? and up.privilege_id = p.id";
			return runner.query(sql, id, new BeanListHandler<>(Privilege.class));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
	}
}
