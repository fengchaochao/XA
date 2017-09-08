/** 
* @Description: TODO
* @date 2015年12月10日 
* @author 1936
*/
package tutorial.thread.syn;

/**
 * @Description: TODO
 * @date 2015年12月10日
 * @author 1936
 */
public class TextThread {

	public static void main(String[] args) {
		
		TextThread textThread = new TextThread();

		TxtThread tt = textThread.new TxtThread();
		new Thread(tt).start();
		new Thread(tt).start();
		new Thread(tt).start();
		new Thread(tt).start();
	}

	class TxtThread implements Runnable {
		
		int num = 10;
		String str = new String();

		public void run() {

				//synchronized (str) {
					while (num > 0) {
						try {
							Thread.sleep(1000);
						} catch (Exception e) {
							e.getMessage();
						}
						System.out.println(Thread.currentThread().getName() + "this is " + num--);
					}
				//}

		}
	}

}
