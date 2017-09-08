package tutorial.openfire;

import org.jivesoftware.smack.Chat;
import org.jivesoftware.smack.ChatManager;
import org.jivesoftware.smack.MessageListener;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.packet.Message;

/** 
* @Description: User2 用户信息
* @date 2015年12月17日 
* @author 1936
*/
public class User2Chat {

	public static void main(String [] args) throws Exception
	{
		try 
		{
			
			XMPPConnection conn = XmppTool.getConnection();
			conn.login("test2", "test2");
			ChatManager chatmanager = conn.getChatManager();
			
			System.out.println("Test2 等待接收信息");
			while(true){
				chatmanager.createChat("test1@localhost.localdomain/Smack", new MessageListener() {
				    public void processMessage(Chat chat, Message message) {
				        System.out.println("Test2 接收到的信息: " + message.getBody());
				        try {
							chat.sendMessage("Test2 信息已接收到！");
						} catch (XMPPException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
				    }
				});
			}
			
			
		}
		catch (XMPPException e) {
		    e.printStackTrace();
		}
	}

}
