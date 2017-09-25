package cn.hn.utils;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.List;

import cn.hn.domain.Privilege;
import cn.hn.domain.User;
import cn.hn.service.BusinessService;

/*
	权限拦截服务工厂,动态代理技术
 */
public class ServiceFactory {
	private static final ServiceFactory instance = new ServiceFactory();

	private ServiceFactory() {
	}

	public static ServiceFactory getInstance() {
		return instance;
	}

	@SuppressWarnings("unchecked")
	/*
	 * title:动态代理service
	 * explanation:
	 * example:
	 * param:传入类的名字,还有用户(看是否有权限)
	 * return:
	 */
	public <T> T createService(String className, Class<T> clazz, final User user) {
		try {

			final T t = (T) Class.forName(className).newInstance();
			return (T) Proxy.newProxyInstance(ServiceFactory.class.getClassLoader(), t.getClass().getInterfaces(),
					new InvocationHandler() {

						@Override
						public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
							
							// 1 判断用户调用的方法是什么
							String methodName = method.getName();
							//System.out.println(methodName);
							// 2 反射出对象相应的方法
							Method m = t.getClass().getMethod(methodName, method.getParameterTypes());
							// 3 看真实对象的方法上是否有权限注解
							Permission p = m.getAnnotation(Permission.class);
							// 4 如果没有 说明访问该方法不需要权限
							if (p == null) {
								return method.invoke(t, args);
							}
							// 5 有则说明访问该方法需要权限，得到该权限，检查用户是否有相应的权限
							if(user == null){
								throw new PrivilegeException("sorry you need to sign in at first.");
							}
							String name = p.value();
							//System.out.println(name);
							Privilege privilege = new Privilege();
							privilege.setName(name);
							BusinessService service = (BusinessService) t;
							List<Privilege> privileges = service.getPrivileges(user.getId());

							// 6 有权限就放行
							for(Privilege pp : privileges){
								//System.out.println(pp.getName());
							}

							if (privileges.contains(privilege)) {
								System.out.println("find function adCategory()");
								return method.invoke(t, args);
							}

							// 没有权限则不能抛出编译时异常
							throw new PrivilegeException("sorry you do not have the privilege.");

							
						}
					});

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
