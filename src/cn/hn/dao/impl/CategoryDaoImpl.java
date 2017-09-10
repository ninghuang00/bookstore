package cn.hn.dao.impl;

import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import cn.hn.dao.CategoryDao;
import cn.hn.domain.Category;
import cn.hn.utils.JdbcUtils;

public class CategoryDaoImpl implements CategoryDao {
	
	
	/* (non-Javadoc)
	 * @see cn.hn.dao.impl.CategoryDao#add(cn.hn.domain.Category)
	 */
	@Override
	public void add(Category cate) {
		try {
			QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());
			String sql = "insert into category(id,name,description) values(?,?,?)";
			Object[] params = { cate.getId(), cate.getName(), cate.getDescription() };
			runner.update(sql, params);

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/* (non-Javadoc)
	 * @see cn.hn.dao.impl.CategoryDao#find(java.lang.String)
	 */
	@Override
	public Category find(String id) {

		try {
			QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());
			String sql = "select * from category where id=?";
			return runner.query(sql, id, new BeanHandler<>(Category.class));

		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}

	/* (non-Javadoc)
	 * @see cn.hn.dao.impl.CategoryDao#getAll()
	 */
	@Override
	public List<Category> getAll() {

		try {
			QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());
			String sql = "select * from category";
			return runner.query(sql, new BeanListHandler<>(Category.class));
			
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

		
	}

	@Override
	public void delete(String id) {
		try {
			QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());
			String sql = "delete from category where id=?";
			runner.update(sql, id);

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
	}

}
