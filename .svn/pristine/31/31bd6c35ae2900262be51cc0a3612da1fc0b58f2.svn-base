package tutorial.thread;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: ArrayList 线程不安全例子
 * 
 * @date 2015年12月8日
 * @author 1936
 */
public class ArrayListThreadGroup implements Runnable  {
	
	// 线程不安全
	List<String> arrList = new ArrayList<String>();
	
	// 线程安全
	//List<String> arrList = new Vector<String>();
	// 线程安全
	// List<String> arrList = Collections.synchronizedList(new ArrayList<String>());

	public void run() {
		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		arrList.add(Thread.currentThread().getName());
	}

	public static void main(String[] args) throws InterruptedException {
		
		ThreadGroup group = new ThreadGroup("testgroup");
		// 例：线程A先将元素存放在位置 0,但是此时CPU调度线程A暂停,线程B得到运行的机会.线程B也向此ArrayList添加元素,因为此时Size仍然等于0（注意哦，我们假设的是添加一个元素是要两个步骤哦，而线程A仅仅完成了步骤1），所以线程B也将元素存放在位置0。
		ArrayListThreadGroup mthread = new ArrayListThreadGroup();
		
		for (int i = 0; i < 10000; i++) {
			Thread th = new Thread(group, mthread, String.valueOf(i));
			th.start();
		}
		
		while (group.activeCount() > 0) {
			Thread.sleep(3000);
		}
		
		System.out.println("操作后数目："+mthread.arrList.size() + "（应为：10000）" ); 
	}
}
