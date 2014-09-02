package com.Player;

import com.Bullet.BulletManager;
import com.threed.jpct.SimpleVector;

public class Player {
public int health;

public SimpleVector position;

	
	public void Fire(SimpleVector Target){
		
		BulletManager bCreate= new BulletManager();
		bCreate.createBullet(6, 6);
		
		
	}
}
