package com.Sora.RPGStat.Stat;

public class StatUtile {
	int statCnt = 10;// 
	
	int lv1 = 70;
	int lv2 = 140;
	int lv3 = 210;
	
	int sp = 3;// 스탯포인트
	
	double dm = 0.125;// 근접공격력
	double hp = 0.5;// 체력
	double speed = 0.2/500;// 이속
	double ad = 0.1;// 화살공격력
	double df = 0.1;// 방어력
	
	public int getLv1() {
		return lv1;
	}

	public int getLv2() {
		return lv2;
	}

	public int getLv3() {
		return lv3;
	}

	public int getSp() {
		return sp;
	}
	
	public double getDm(int lv) {
		return dm*am(lv);
	}

	public double getDm() {
		return dm;
	}
	
	public double getHp(int lv) {
		return hp*am(lv);
	}

	public double getHp() {
		return hp;
	}

	public double getSpeed(int lv) {
		return speed;
	}

	public double getSpeed() {
		return speed;
	}
	
	public double getAd(int lv) {
		return ad*am(lv);
	}

	public double getAd() {
		return ad;
	}
	
	public double getDf() {
		return df;
	}
	
	public double getDf(int lv) {
		return df*am(lv);
	}

	public int getStatCnt() {
		return statCnt;
	}

	public void setStatCnt(int statCnt) {
		this.statCnt = statCnt;
	}
	private double am(double lv){
		double am = 1.5*(int)(lv/50);
		if(am <= 0)  am = 1;
		return 1;
	}
}
