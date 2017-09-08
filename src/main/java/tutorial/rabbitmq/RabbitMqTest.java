package tutorial.rabbitmq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/** 
* @Description: TODO
* @date 2016年9月26日 
* @author 1936
*/
public class RabbitMqTest {

	/**
	 * @Description: TODO
	 * @param args
	 * 
	 * @date 2016年9月26日 
	 * @author 1936
	 */
	public static void main(String[] args) throws Exception {
		
		Connection connection = null;
		Channel channel = null;		
		try {
			ConnectionFactory factory = new ConnectionFactory();
			factory.setHost("10.101.0.68");
			factory.setPort(5672);
			factory.setUsername("app");
			factory.setPassword("app12345");
			connection = factory.newConnection();
			channel = connection.createChannel();
            System.out.println("\n RabbitMQ 连接成功");
            
        } catch (Exception e) {
        	e.printStackTrace();
        	System.out.println("\n RabbitMQ 测试异常："+e);
        }
		finally{
			if(channel!=null) channel.close();
			if(connection!=null) connection.close();
		}

	}

}
