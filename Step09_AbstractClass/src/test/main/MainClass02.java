package test.main;

import test.mypac.Weapon;
import test.mypac.YourWeapon;

public class MainClass02 {
	public static void main(String[] args) {
		
		Weapon w1=new YourWeapon();
		w1.prepare();
		w1.attack(); //이 메소드가 공중 공격을 하도록 프로그래밍해 보세요.
	}
}
