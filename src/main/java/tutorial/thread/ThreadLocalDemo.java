package tutorial.thread;

/**
 * @Description: TODO
 * @date 2015年12月9日
 * @author 1936
 */
public class ThreadLocalDemo {

	// 通过匿名内部类覆盖ThreadLocal的initialValue()方法，指定初始值
	private static ThreadLocal<Integer> seqNum = new ThreadLocal<Integer>() {
		protected Integer initialValue() {
			return 0;
		}
	};

	public Integer getNextNum() {
		seqNum.set(seqNum.get() + 1);
		 return seqNum.get();
	}

	public static void main(String[] args) {
		ThreadLocalDemo threadLocal = new ThreadLocalDemo();
		// 3个线程共享sn,各自产生序列号
		TestClient t1 = new TestClient(threadLocal);
		TestClient t2 = new TestClient(threadLocal);
		TestClient t3 = new TestClient(threadLocal);
		t1.start();
		t2.start();
		t3.start();
	}

	private static class TestClient extends Thread {

		private ThreadLocalDemo threadLocalDemo;

		public TestClient(ThreadLocalDemo pthreadLocal) {
			threadLocalDemo = pthreadLocal;
		}

		public void run() {
			// 每个线程打印3个序列号
			for (int i = 0; i < 3; i++) {
				System.out.println("thread[" + Thread.currentThread().getName() + ",sn[" + threadLocalDemo.getNextNum() + "]");
			}
		}
	}
}
