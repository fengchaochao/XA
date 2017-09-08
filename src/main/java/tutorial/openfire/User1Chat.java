package tutorial.openfire;

import org.jivesoftware.smack.Chat;
import org.jivesoftware.smack.ChatManager;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPException;

/**
* @Description: User1 用户信息
* @date 2015年12月17日 
* @author 1936
 */
public class User1Chat {
	
	public static void main(String [] args) throws Exception{
		try 
		{
			XMPPConnection conn = XmppTool.getConnection();
			conn.login("test1", "test1");
			while(true){
				ChatManager chatmanager = conn.getChatManager();
				Chat newChat = chatmanager.createChat("test2@localhost.localdomain/Smack",null);
				newChat.sendMessage("Test1 发送的信息!");
			}
		}
		catch (XMPPException e) {
			e.printStackTrace();
		}
	}
	
}
