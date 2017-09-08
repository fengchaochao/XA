/** 
* @Description: TODO
* @date 2015年12月8日 
* @author 1936
*/
package tutorial.thread;

/** 
* @Description: TODO
* @date 2015年12月8日 
* @author 1936
*/
public class RunnableThread  implements Runnable{

	RunnableThread() {
		
	}
	   
	@Override
	public void run() {
		System.out.println("Inside run() function");
	}
	
	public static void main(String args[]) {
		
		RunnableThread runThread = new RunnableThread();
		Thread thread = new Thread(runThread);
		thread.start();
		System.out.println(thread);
	}

}
