package tutorial.despat.bridge;

/**
* @Description: 客户端调用
* @date 2015年11月19日 
* @author 1936
 */
public class Client  {
	
	public Client() {
		
	}

	public static void main(String[] args) {
		
		// celink 遥控器
		CelinkRemoteControl celinkRemoteControl = new CelinkRemoteControl(new KonkaTV());
		celinkRemoteControl.turnOn();
		celinkRemoteControl.setChannelKeyboard(0);
		
		celinkRemoteControl = new CelinkRemoteControl(new ChanghongTV());
		celinkRemoteControl.turnOn();
		celinkRemoteControl.setChannelKeyboard(0);
		
	}
}