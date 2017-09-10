package cn.hn.dao.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import cn.hn.dao.BookDao;
import cn.hn.domain.Book;
import cn.hn.utils.JdbcUtils;

public class BookDaoImpl implements BookDao {

	/* (non-Javadoc)
	 * @see cn.hn.dao.impl.BookDao#add(cn.hn.domain.Book)
	 */
	@Override
	public void add(Book book) {
		try {
			QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());
			
			String sql = "insert into book(id,name,author,description,price,imageName,category_id) values(?,?,?,?,?,?,?)";
			Object[] params = {book.getId(),book.getName(),book.getAuthor(),book.getDescription(),book.getPrice(),book.getImageName(),book.getCategory_id()};
			runner.update(sql,params);

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	/* (non-Javadoc)
	 * @see cn.hn.dao.impl.BookDao#find(java.lang.String)
	 */
	@Override
	public Book find(String id){
		try {
			QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());
			String sql = "select * from book where id=?";
			return runner.query(sql, id, new BeanHandler<>(Book.class));
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	
	/* (non-Javadoc)
	 * @see cn.hn.dao.impl.BookDao#getAll()
	 */
	@Override
	public List<Book> getAll(){
		
		try {
			QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());
			
			String sql = "select * from book";
			return runner.query(sql, new BeanListHandler<>(Book.class));
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	/* (non-Javadoc)
	 * @see cn.hn.dao.impl.BookDao#delete(java.lang.String)
	 */
	@Override
	public void delete(String id){
		try {
			QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());
			
			String sql = "delete from book where id=?";
			runner.update(sql, id);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
		
	}

	@Override
	public List<Book> getPageData(int startindex, int pagesize, String category_id) {
		try {
			QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());
			if(category_id == null || category_id.trim().equals("")){	//如果没有带着分类id过来就显示全部书籍
				String sql = "select * from book limit ?,?";
				Object params[] = {startindex,pagesize};
				return runner.query(sql, params, new BeanListHandler<>(Book.class));
			}else{
				String sql = "select * from book where category_id=? limit ?,?";
				Object params[] = {category_id,startindex,pagesize};
				return runner.query(sql, params, new BeanListHandler<>(Book.class));
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public int getTotalRecord(String category_id){
		try {
			
			QueryRunner runner = new QueryRunner(JdbcUtils.getDataSource());
			if(category_id == null || category_id.trim().equals("")){
				String sql = "select count(*) from book";
				long count = runner.query(sql, new ScalarHandler<>());
				return (int)count;
			}else{
				String sql = "select count(*) from book where category_id=?";
				long count = runner.query(sql, category_id, new ScalarHandler<>());
				return (int)count;
			}

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
