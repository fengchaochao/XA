package tutorial.thread;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: 线程安全的例子：
 * 初始化长度为10000的List
 * 删除所有元素（分为100个线程，每个线程删除100个）
 * 添加10000个元素（分为100个线程，每个线程添加100个）
 * 线程安全的话，操作后的List长度应为10000，线程不安全的话，操作长度不定
 * 这是因为当多个线程同时操作rm 的时候，有可能两个线程删除的是同一个元素，结果可能出现元素没有全部删除，导致线程不安全
 * @date 2015年12月8日
 * @author 1936
 */
public class ArrayListThread {
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void main(String[] args) {
		
		ArrayListThread mianThread = new ArrayListThread();
		
		// 线程不安全 
		List list = new ArrayList();
		// 线程安全
		// list = new Vector();
		
		// 初始
		for (int i = 0; i < 10000; i++) {
			list.add(i);
		}
		
		// 全部删除
		for (int i = 0; i < 100; i++){
			RmThread rmThread = mianThread.new RmThread(list);
			rmThread.start();
		}
		
		// 全部添加
		for (int i = 0; i < 100; i++){
			AddThread addThread = mianThread.new AddThread(list);
			addThread.start();
		}
		
		try {
			// 等待所有线程执行完
			Thread.sleep(3000);
			
		} catch (InterruptedException e) {
			
		}
		
		System.out.println("操作后数目："+list.size() + "（应为：10000） ");
	}
	
	
	@SuppressWarnings({"rawtypes","unchecked"})
	class AddThread extends Thread {
		List m_list;
		AddThread(List list){
			m_list = list;
		}
		
		@Override
        public void run() {
			for(int i=0;i<100;i++){
				m_list.add(0); 
			}
		}
	}
	
	@SuppressWarnings({"rawtypes"})
	class RmThread extends Thread {
		List m_list;
		
		RmThread(List list){
			m_list = list;
		}

		@Override
        public void run() {
			for(int i=0;i<100;i++){
				m_list.remove(0); 
			}
		}
	}
}

