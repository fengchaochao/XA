/** 
 * @Description: TODO
 * @date 2016年1月20日 
 * @author 1936
 */
package tutorial.redis;

/**
 * @Description: TODO
 * @date 2016年1月20日
 * @author 1936
 */
public class RedisTest {

	public static void main(String[] args) {

		RedisUtil.getJedis().set("001,A","10");
		RedisUtil.getJedis().set("001,A","100");
		RedisUtil.getJedis().set("001,C","10");
		RedisUtil.getJedis().incrBy("001,A", 11);
		RedisUtil.getJedis().decrBy("001,C", 221);
		String s = RedisUtil.getJedis().get("001,A");
		System.out.print(s);

	}

}
