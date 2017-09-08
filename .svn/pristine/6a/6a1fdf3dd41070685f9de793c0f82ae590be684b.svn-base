package tutorial.despat.single;

import java.util.List;

/** 
* @Description: 单例模式下的线程安全测试
* @date 2015年12月9日 
* @author 1936
*/
public class SingletonThreadSafeMain{
	
	SingletonThreadSafe singletom = SingletonThreadSafe.getInstance();
	
	public static void main(String args[]) throws Exception {
		
		SingletonThreadSafeMain sm = new SingletonThreadSafeMain();
		System.out.println("开始执行.....");
		for(int i=0;i<10000;i++)
		{
			AddOneListThread oneListThread = sm.new AddOneListThread();
			oneListThread.start();
			
			AddFourListThread fourListThread = sm.new AddFourListThread();
			fourListThread.start();
			
			AddSevenListThread sevenListThread = sm.new AddSevenListThread();
			sevenListThread.start();
			
			AddNineListThread nineListThread = sm.new AddNineListThread();
			nineListThread.start();
		}
		Thread.sleep(1000);
		System.out.println("执行结束.....");
	}
	
	
	class AddOneListThread extends Thread {
		@SuppressWarnings({ "rawtypes", "unused" })
		@Override
        public void run() {
			List list = singletom.addList(1);
		}
	}
	
	class AddFourListThread extends Thread {
		@SuppressWarnings({ "rawtypes", "unused" })
		@Override
        public void run() {
			List list = singletom.addList(4);
			// System.out.println("Add 4 list:"+list);
		}
	}
	
	class AddSevenListThread extends Thread {
		@SuppressWarnings({ "rawtypes", "unused" })
		@Override
        public void run() {
			List list = singletom.addList(7);
		}
	}
	
	class AddNineListThread extends Thread {
		@SuppressWarnings({ "rawtypes", "unused" })
		@Override
        public void run() {
			List list = singletom.addList(9);
		}
	}
}
