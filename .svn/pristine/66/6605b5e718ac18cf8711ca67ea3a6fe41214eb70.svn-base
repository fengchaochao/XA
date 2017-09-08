package tutorial.zk;

import java.util.List;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.data.Stat;

/** 
* @Description: zookeeper 操作
* @date 2016年8月8日 
* @author 1936
*/
public class ZooKeeperOperator extends AbstractZooKeeper {

	/**
	 * 创建ZK连接
	 * @param connectString	 ZK服务器地址列表
	 * @param sessionTimeout   Session超时时间
	 */
	public void createConnection(String connectString, int sessionTimeout) {
		this.releaseConnection();
		try {
			this.zooKeeper = new ZooKeeper(connectString, sessionTimeout,this);
			countDownLatch.await();
		} catch ( Exception e ) {}
	}
	
	/**
	 * 关闭ZK连接
	 */
	public void releaseConnection() {
		if ( this.zooKeeper != null ) {
			try {
				this.zooKeeper.close();
			} catch ( InterruptedException e ) {}
		}
	}
	
	/**
	 * @Description: 创建持久态的znode,比支持多层创建.比如在创建/parent/child的情况下,无/parent.无法通过
	 * @param path
	 * @param data
	 * @throws KeeperException
	 * @throws InterruptedException
	 * @date 2016年8月9日
	 * @author 1936
	 */
	public void create(String path, String data) throws KeeperException, InterruptedException {
		/**
		 * 此处采用的是CreateMode是PERSISTENT 表示The znode will not be automatically
		 * deleted upon client's disconnect. EPHEMERAL 表示The znode will be
		 * deleted upon the client's disconnect.
		 */
		this.zooKeeper.create(path, data!=null ? data.getBytes() : null, Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
	}

	/**
	 * @Description: 获取节点信息
	 * @param path
	 * @return
	 * @throws KeeperException
	 * @throws InterruptedException
	 * 
	 * @date 2016年8月9日
	 * @author 1936
	 */
	public List<String> getChildren(String path) throws KeeperException, InterruptedException {
		List<String> childrenList = null;
		try {
			childrenList = this.zooKeeper.getChildren(path, false);
		} catch (KeeperException.NoNodeException e) {
			throw e;
		}
		return childrenList;
	}

	/**
	 * 删除指定节点
	 * @param path 节点path
	 */
	public void deleteNode(String path) throws Exception{
		this.zooKeeper.delete(path, -1);
	}
	
	/**
	 * 否存在节点
	 * @param path 节点path
	 */
	public Stat exists(String path) throws Exception{
		return this.zooKeeper.exists(path,false);
	}
	
	/**
	 * @Description: 获取节点数据
	 * @param path
	 * @return
	 * @throws KeeperException
	 * @throws InterruptedException
	 * 
	 * @date 2016年8月9日
	 * @author 1936
	 */
	public String getData(String path) throws KeeperException, InterruptedException {
		return new String(this.zooKeeper.getData(path, false, null));
	}

	/**
	 * 更新指定节点数据内容
	 * @param path 节点path
	 * @param data  数据内容
	 * @return
	 */
	public boolean writeData(String path, String data) {
		try {
		   this.zooKeeper.setData(path,data.getBytes(), -1);
		} catch ( Exception e ) {
			
		}
		return false;
	}
	
	
	public static void main(String[] args) {
		try {
			ZooKeeperOperator zookeeperOpe = new ZooKeeperOperator();
			zookeeperOpe.connect("10.101.0.231:2181");
			
			// 创建节点
			//zookeeperOpe.create("/bruceTest/url", "jdbc:mysql://10.101.2.3:3306/ufdm?allowMultiQueries=true&useUnicode=true&characterEncoding=utf8");
			//zookeeperOpe.create("/bruceTest/name", "root");
			//zookeeperOpe.create("/bruceTest/pass", "88888");

			// 更新数据
			//zookeeperOpe.writeData("/bruceTest/abc1", null);
			
			// 判断节点是否存在
			/**
			Stat stat = zookeeperOpe.exists("/bruceTest");
			if(stat==null){
				System.out.println("\n 节点不存在");
			}
			else{
				System.out.println("\n 节点存在");
			}
			*/
			
			// 删除节点
	        //zookeeperOpe.deleteNode("/bruceTest/abc");
			
			List<String> children = zookeeperOpe.getChildren("/bruceTest");
			System.out.println("节点孩子信息:" + children);

			String data = zookeeperOpe.getData("/bruceTest/url");
			System.out.println("获取数据:" + data);

			zookeeperOpe.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
