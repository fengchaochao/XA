package tutorial.redis;

import org.apache.commons.lang.StringUtils;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;

import com.prj.biz.bean.sysuser.SysUser;
import com.prj.utils.ObjectUtil;

/**
 * @author: Fengc
 * @date:2017-7-31 下午2:19:33
 * @version :0.0.1
 * @dis:
 */
public class main1 {

	public static void main(String[] args) {
		new lock().start();
		new lock().start();
		new lock().start();
		new lock().start();
		new lock().start();
		new lock().start();
		new lock().start();
		new lock().start();
		new lock().start();
		new lock().start();
		new lock().start();
		new lock().start();
		new lock().start();
		new lock().start();
		new lock().start();
		new lock().start();
		new lock().start();
		new lock().start();
		new lock().start();
		new lock().start();
		new lock().start();
		new lock().start();
		new lock().start();
		new lock().start();
		new lock().start();
		new lock().start();
		new lock().start();
		new lock().start();
		new lock().start();
		new lock().start();
		new lock().start();
		new lock().start();
		new lock().start();
		new lock().start();
		new lock().start();
		new lock().start();
		new lock().start();
		new lock().start();
		new lock().start();
		new lock().start();
		new lock().start();
		new lock().start();
		new lock().start();
		new lock().start();
		new lock().start();
		new lock().start();
		new lock().start();
		new lock().start();
		new lock().start();
		new lock().start();
		new lock().start();
		new lock().start();
		new lock().start();
		new lock().start();
		new lock().start();
		new lock().start();
		new lock().start();
		new lock().start();
		new lock().start();
		new lock().start();
		new lock().start();
		new lock().start();
		new lock().start();
		new lock().start();
		new lock().start();
		new lock().start();
		new lock().start();
		new lock().start();
		new lock().start();
		new lock().start();
		new lock().start();
		new lock().start();
		new lock().start();
		new lock().start();
		new lock().start();
		new lock().start();
		new lock().start();
		new lock().start();
		new lock().start();
		new lock().start();
		new lock().start();
		new lock().start();
		new lock().start();
		new lock().start();
		new lock().start();
		new lock().start();
		new lock().start();
		new lock().start();
		new lock().start();
		new lock().start();
		new lock().start();
		new lock().start();
		new lock().start();
		new lock().start();
		new lock().start();
		new lock().start();
		new lock().start();
		new lock().start();
		new lock().start();
		new lock().start();
		new lock().start();
		new lock().start();
		new lock().start();
	}
}

class lock extends Thread {

	@Override
	public void run() {
		// if (!RedisUtil.getJedis().sismember("RedPacketDet-003", "张三")) {
		
		String s = RedisUtil.getJedis().lpop("RedPacket008");

		if (StringUtils.isNotEmpty(s)) {
			System.out.println("你成功领取了红包" + s);

			RedisUtil.getJedis().sadd("RedPacketDet-003", "张三"); // **
																	// 给红包记录表插入领取人
		} else {
			System.out.println("该红包已经被领取完成!");
		}

		/*
		 * } else { System.out.println("你已经领取过红包了！"); }
		 */
	}

}
