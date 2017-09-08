package com.prj.utils.random;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: Fengc
 * @date:2017-7-26 下午4:15:14
 * @version :0.0.1
 * @dis:
 */
public class RedPacketUtil {

	private float MINMONEY;
	private float MAXMONEY;
	
	
	private float TIMES = 2.1f;
	

	/**
	 * 
	 * @param MINMONEY 随机红包的最小金额
	 * @param MAXMONEY 随机红包的最大金额
	 */
	public RedPacketUtil(float MINMONEY, float MAXMONEY) {
		this.MINMONEY = MINMONEY;
		this.MAXMONEY = MAXMONEY;
	}

	public float getMINMONEY() {
		return MINMONEY;
	}

	public void setMINMONEY(float mINMONEY) {
		MINMONEY = mINMONEY;
	}

	public float getMAXMONEY() {
		return MAXMONEY;
	}

	public void setMAXMONEY(float mAXMONEY) {
		MAXMONEY = mAXMONEY;
	}

	private boolean isRight(float money, int count) {
		double avg = money / count;
		if (avg < MINMONEY) {
			return false;
		} else if (avg > MAXMONEY) {
			return false;
		}
		return true;
	}

	/**
	 * 
	 * @param money 总金额
	 * @param count 红包个数
	 * @return
	 */
	public List<Float> splitRedPackets(float money, int count) {
		if (!isRight(money, count)) {
			return null;
		}
		List<Float> list = new ArrayList<Float>();
		float max = (float) (money * TIMES / count);

		max = max > MAXMONEY ? MAXMONEY : max;
		for (int i = 0; i < count; i++) {
			float one = randomRedPacket(money, MINMONEY, max, count - i);
			list.add(one);
			money -= one;
		}
		return list;
	}

	private float randomRedPacket(float money, float mins, float maxs, int count) {
		if (count == 1) {
			return (float) (Math.round(money * 100)) / 100;
		}
		if (mins == maxs) {
			return mins;// 如果最大值和最小值一样，就返回mins
		}
		float max = maxs > money ? money : maxs;
		float one = ((float) Math.random() * (max - mins) + mins);
		one = (float) (Math.round(one * 100)) / 100;
		float moneyOther = money - one;
		if (isRight(moneyOther, count - 1)) {
			return one;
		} else {
			// 重新分配
			float avg = moneyOther / (count - 1);
			if (avg < MINMONEY) {
				return randomRedPacket(money, mins, one, count);
			} else if (avg > MAXMONEY) {
				return randomRedPacket(money, one, maxs, count);
			}
		}
		return one;
	}

	public static void main(String[] args) {
		RedPacketUtil util = new RedPacketUtil(0.1f, 200f);
		System.out.println(util.splitRedPackets(10, 50));
	}
}
