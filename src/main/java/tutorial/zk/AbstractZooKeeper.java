package tutorial.zk;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.Watcher.Event.EventType;
import org.apache.zookeeper.Watcher.Event.KeeperState;
import org.apache.zookeeper.ZooKeeper;

/**
 * @Description: TODO
 * @date 2016年8月8日
 * @author 1936
 */
public class AbstractZooKeeper implements Watcher {

	// 缓存时间
	private static final int SESSION_TIME = 2000;
	protected ZooKeeper zooKeeper;
	protected CountDownLatch countDownLatch = new CountDownLatch(1);
	AtomicInteger seq = new AtomicInteger();
	
	public void connect(String hosts) throws IOException, InterruptedException {
		zooKeeper = new ZooKeeper(hosts, SESSION_TIME, this);
		countDownLatch.await();
	}

	@Override
	public void process(WatchedEvent event) {
		
		// 连接状态
		KeeperState keeperState = event.getState();
		// 事件类型
		EventType eventType = event.getType();
		// 受影响的path
		String path = event.getPath();
		// 
		String logPrefix = "【Watcher-" + this.seq.incrementAndGet() + "】";

		System.out.println("\n "+logPrefix+"收到Watcher通知 状态："+event.getState() + "; 类型："+ event.getType());

		if ( KeeperState.SyncConnected == keeperState ) {
			// 成功连接上ZK服务器
			if ( EventType.None == eventType ) {
				System.out.println( logPrefix + "成功连接上ZK服务器" );
				countDownLatch.countDown();
			} else if ( EventType.NodeCreated == eventType ) {
				System.out.println( logPrefix + "节点创建" );
			} else if ( EventType.NodeDataChanged == eventType ) {
				System.out.println( logPrefix + "节点数据更新" );
			} else if ( EventType.NodeChildrenChanged == eventType ) {
				System.out.println( logPrefix + "子节点变更" );
			} else if ( EventType.NodeDeleted == eventType ) {
				System.out.println( logPrefix + "节点 " + path + " 被删除" );
			}

		} else if ( KeeperState.Disconnected == keeperState ) {
			System.out.println( logPrefix + "与ZK服务器断开连接" );
		} else if ( KeeperState.AuthFailed == keeperState ) {
			System.out.println( logPrefix + "权限检查失败" );
		} else if ( KeeperState.Expired == keeperState ) {
			System.out.println( logPrefix + "会话失效" );
		}
	}

	public void close() throws InterruptedException {
		zooKeeper.close();
	}

}
