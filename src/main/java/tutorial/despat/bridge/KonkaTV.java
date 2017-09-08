package tutorial.despat.bridge;

public class KonkaTV implements ITelevision {
	
	public KonkaTV() {
		
	}

	@Override
	public void on() {
		System.out.println("康佳--打开电视");
	}

	@Override
	public void off() {
		System.out.println("康佳--关闭电视");
	}

	@Override
	public void switchChannel(int channel) {
		System.out.println("康佳--换频道");
	}

	
}