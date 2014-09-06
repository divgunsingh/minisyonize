package com.Bullet;

import java.util.ArrayList;
import java.util.List;


import android.view.VelocityTracker;

import com.Enemy.Enemy;
import com.threed.jpct.Logger;
import com.threed.jpct.SimpleVector;

public class BulletManager  {

	
  List<Bullet> bullets;
  
  public BulletManager(){
	  bullets = new ArrayList<Bullet>();
  }
	
	public void createBullet(SimpleVector positonOnClick, SimpleVector direction,int damage, float speed){
		//.damage= 20;
	    //	bullet.position = a;
		//bullet.velocity= b;
		//speed.scalarMul(veloity*direction);
				//bullets.add(bullet);
		
		Bullet bullet=new Bullet();
		bullet.bulletVelocity = direction;
		direction=new SimpleVector(1,1,0);
		bullet.bulletVelocity.scalarMul(speed);
		bullet.damage = damage;
		Logger.log("CreateBullet");
		Logger.log(positonOnClick.toString());
		Logger.log(bullet.bulletVelocity.toString());
		bullet.setPosition(positonOnClick);
		bullets.add(bullet);
	}
	
	
	public void update(float elapsedTime){
		
		//SimpleVector adjustedVelocity= bullet.bulletVelocity;
		//adjustedVelocity.scalarMul(elapsedTime*60);
		// bullet.bulletPosition.add(bullet.bulletVelocity);
		
		for(Bullet e : bullets){
			e.Update(elapsedTime);
		}
	}
	
	
}
