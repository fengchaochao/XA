package tutorial.openfire;

import org.jivesoftware.smack.ConnectionConfiguration;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPException;
/** 
* @Description: XMPP 连接
* @date 2015年12月16日 
* @author 1936
*/
public class XmppTool {
	
	private static XMPPConnection con = null;
	private static void openConnection() {
		try {
			ConnectionConfiguration config = new ConnectionConfiguration("192.168.86.128", 5222);
			config.setCompressionEnabled(false);
			config.setSelfSignedCertificateEnabled(false);
			config.setSASLAuthenticationEnabled(false);
			config.setVerifyChainEnabled(false);
			con = new XMPPConnection(config);
			con.connect();
		} catch (XMPPException xe) {
			xe.printStackTrace();
		}
	}

	public static XMPPConnection getConnection() {
		if (con == null) {
			openConnection();
			System.out.println("连接状态："+con.isConnected());
		}
		return con;
	}

	public static void closeConnection() {
		con.disconnect();
		con = null;
	}
}
