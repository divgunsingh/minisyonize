package com.threed.jpct.Player;

import com.threed.jpct.SimpleVector;
import com.threed.jpct.example.Bullet.BulletManager;

public class Player {
public int health;

public SimpleVector position;

	
	public void Fire(SimpleVector Target){
		
		BulletManager bCreate= new BulletManager();
		bCreate.createBullet(6, 6);
		
		
	}
}
