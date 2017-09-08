package tutorial.thread;

import org.jivesoftware.smack.Chat;
import org.jivesoftware.smack.ChatManager;
import org.jivesoftware.smack.ChatManagerListener;
import org.jivesoftware.smack.MessageListener;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.packet.Message;

import tutorial.openfire.XmppTool;

/** 
* @Description: TODO
* @date 2015年12月17日 
* @author 1936
*/
public class Test {

	/**
	 * @Description: TODO
	 * @param args
	 * 
	 * @date 2015年12月17日 
	 * @author 1936
	 */
	public static void main(String [] args) throws Exception{

		XMPPConnection conn = XmppTool.getConnection();
		conn.login("test1", "test1");
		ChatManager chatManager = conn.getChatManager();
		
		Chat chat = chatManager.createChat("test1@localhost.localdomain", null);  
		chat.sendMessage("ttttttttttttttttttttt");
		System.out.println("ttttttttttttttttttttt");
		
		chatManager.addChatListener(new ChatManagerListener() {
			@Override
			public void chatCreated(Chat chat, boolean create) {
				chat.addMessageListener(new MessageListener() {
					@Override
					public void processMessage(Chat chat, Message msg) {
						
						System.out.println(chat.getParticipant() + ":" + msg.getBody());
						
						try {
							chat.sendMessage("你刚才说的是：" + msg.getBody()); // 发送消息
						} catch (XMPPException e) {

							e.printStackTrace();
						}
					}
				});
			}
		});
		while(true);  
	}
}
