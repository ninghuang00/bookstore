package cn.hn.dao;

import java.util.List;

import cn.hn.domain.Book;

public interface BookDao {

	void add(Book book);

	Book find(String id);

	List<Book> getAll();

	void delete(String id);
	
	List<Book> getPageData(int startindex, int pagesize, String category_id);

	int getTotalRecord(String category_id);

}