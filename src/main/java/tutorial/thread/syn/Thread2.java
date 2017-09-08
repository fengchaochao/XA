package tutorial.thread.syn;

/**
 * @Description: synchronized(this) 使用 2
 *               当一个线程访问object的一个synchronized(this)同步代码块时，
 *               另一个线程仍然可以访问该object中的非synchronized(this)同步代码块
 * @date 2015年12月10日
 * @author 1936
 */
public class Thread2 {
	
	public synchronized  void synTest() {
			int i = 10;
			while (i-- > 0) {
				System.out.println("synTest: " + Thread.currentThread().getName() + " : " + i);
				try {
					Thread.sleep(1000);
				} catch (InterruptedException ie) {
				}
			}
	}

	public void normalTest() {
		int i = 10;
		while (i-- > 0) {
			System.out.println("normalTest: " + Thread.currentThread().getName() + " : " + i);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException ie) {
			}
		}
	}

	public static void main(String[] args) {
		
		final Thread2 myt2 = new Thread2();
		
		Thread t1 = new Thread(new Runnable() {
			public void run() {
				myt2.normalTest();
				myt2.synTest();
			}
		});
		
		Thread t2 = new Thread(new Runnable() {
			public void run() {
				myt2.normalTest();
				myt2.synTest();
				}
		});
		
		t1.start();
		t2.start();
	}
}
