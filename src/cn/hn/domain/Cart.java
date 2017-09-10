package cn.hn.domain;

import java.util.HashMap;
import java.util.Map;

public class Cart {
	private Map<String,CartItem> map = new HashMap<>();
	private double price;
	
	public void add(Book book){
		CartItem item = map.get(book.getId());
		if(item == null){
			item = new CartItem();
			item.setQuantity(1);
			item.setBook(book);
			map.put(book.getId(), item);
		}else{
			item.setQuantity(item.getQuantity()+1);
			
		}
		
	}
	
	public void remove(Book book){
		CartItem item = map.get(book.getId());
		if(item == null) return;
		map.remove(book.getId());
	}
	
	public Map<String, CartItem> getMap() {
		return map;
	}
	public void setMap(Map<String, CartItem> map) {
		this.map = map;
	}
	public double getPrice() {
		double totalPrice = 0;
		for(Map.Entry<String, CartItem> me:map.entrySet()){
			CartItem item = me.getValue();
			totalPrice += item.getPrice();
		}
		price = totalPrice;
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	
	
}
