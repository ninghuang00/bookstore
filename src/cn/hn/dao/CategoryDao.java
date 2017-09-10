package cn.hn.dao;

import java.util.List;

import cn.hn.domain.Category;

public interface CategoryDao {

	void add(Category cate);

	Category find(String id);

	List<Category> getAll();
	
	public void delete(String id);

}