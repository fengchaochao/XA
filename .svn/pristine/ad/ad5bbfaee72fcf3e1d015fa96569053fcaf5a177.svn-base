package tutorial.despat.single;

import java.util.ArrayList;
import java.util.List;

/** 
* @Description: 单例模式下的线程安全
* @date 2015年12月9日 
* @author 1936
*/
public class SingletonThreadSafe {
	
	private static SingletonThreadSafe instance;
	
	public static synchronized SingletonThreadSafe getInstance() {
		if (instance == null) {
			instance = new SingletonThreadSafe();
		}
		return instance;
	}
	
	@SuppressWarnings({ "rawtypes", "unused" })
	private static ThreadLocal<List> threadLocalList = new ThreadLocal<List>(){
		protected List initialValue() {
			return new ArrayList();
		}
	};


	
	// 单例全局变量 线程不安全
	@SuppressWarnings("rawtypes")
	private List list;
	/**
	 * @Description: 全局变量导致线程不安全示例
	 * 如果A、B两个线程先后执行"new ArrayList()"
	 * A 执行 new ArrayList(6)
	 * B 执行 new ArrayList(4)
	 * 此时候 B 线程暂停
	 * 因为 list 为成员变量，成员变量存储在堆中的对象里面，由垃圾回收器负责回收。（局部变量的数据存在于栈内存中。栈内存中的局部变量随着方法的消失而消失）
	 * 当 A 执行 add(5) 的时候，由于 list 的大小长度已经设置为4，此时会报数据越界。
	 * 线程不安全
	 * 解决方案：把成员变量改成局部变量 或者 ArrayList 换成 Vector , 或者用 ThreadLocal
	 * @param size
	 * @return
	 * @date 2015年12月9日 
	 * @author 1936
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List addList(int size){
		// 如果A、B两个线程同时执行（new ArrayList()），A 执行 new ArrayList(6)，B 执行 new ArrayList(4)
		list = new ArrayList(size);
		
		// 使用 Vector 可以解决线程安全问题
		// list = new Vector();
		// 使用 ThreadLocal 可以解决线程安全问题
		//threadLocalList.set(new ArrayList(size));
		
		try
		{
			for(int i=0;i<size;i++){
				list.add(i);
				//threadLocalList.get().add(i);
			}
		}
		catch(Exception er){
			er.printStackTrace();
		}
		
		// list = threadLocalList.get();
		
		return list;
	}
}
