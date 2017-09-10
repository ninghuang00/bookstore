package cn.hn.utils;

public class DaoFactory {
	private static DaoFactory factory = new DaoFactory();
	public DaoFactory(){}
	public static DaoFactory getInstance(){
		return factory;
	}
	
	public <T> T createDao(String className,Class<T> clazz){
		try{
			T t = (T) Class.forName(className).newInstance();
			return t;
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}
	

}
