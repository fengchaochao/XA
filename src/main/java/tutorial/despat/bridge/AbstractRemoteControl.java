package tutorial.despat.bridge;

/**
* @Description: 遥控器的抽象类
* @date 2015年11月19日 
* @author 1936
 */
public abstract class AbstractRemoteControl {
	
	ITelevision tv;
	
	public AbstractRemoteControl(ITelevision tv){
        this.tv = tv;
    }

	public void turnOn(){
        tv.on();
    }
 
    public void turnOff(){
        tv.off();
    }
 
    public void setChannel(int channel){
        tv.switchChannel(channel);
    }
    
}