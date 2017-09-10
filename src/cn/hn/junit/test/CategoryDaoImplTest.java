package cn.hn.junit.test;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import cn.hn.dao.CategoryDao;
import cn.hn.dao.impl.CategoryDaoImpl;
import cn.hn.domain.Category;
import sun.misc.BASE64Encoder;

public class CategoryDaoImplTest {

	private CategoryDao dao = new CategoryDaoImpl();
	@Test
	public void testAdd() {
		Category cate = new Category();
		cate.setDescription("haishi有没有乱码");
		cate.setName("jiusi");
		cate.setId("8040202");
		dao.add(cate);
	}

	@Test
	public void testFind() {
		Category cate = new Category();
		cate = dao.find("8050438045");
		System.out.println("name is " + cate.getName());
	}

	@Test
	public void testGetAll() {
		List<Category> list = new ArrayList<>();
		list = dao.getAll();
		for(Category cate:list){
			System.out.println("name is " + cate.getName());
		}
			
	}
	
	@Test
	public void base64() throws IOException{
		BASE64Encoder encoder = new BASE64Encoder();
		System.out.println("input your username");
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		String username = reader.readLine();
		System.out.println("input your password ");
		String password = reader.readLine();
		System.out.println(encoder.encode(username.getBytes()));
		System.out.println(encoder.encode(password.getBytes()));
		
	}

}
