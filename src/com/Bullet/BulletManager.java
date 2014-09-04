package com.Bullet;

import java.util.ArrayList;
import java.util.List;


import android.view.VelocityTracker;

import com.threed.jpct.SimpleVector;

public class BulletManager  {
 SimpleVector one,two;
	
  List<Bullet> bullets = new ArrayList<Bullet>();
	
	public void createBullet(SimpleVector positon, SimpleVector direction,int damage, float speed){
		SimpleVector a,b;
		
		Bullet bullet=new Bullet();
		
		bullet.damage= 20;
	//	bullet.position = a;
		
		//bullet.velocity= b;
		SimpleVector adjustedVelocity= bullet.velocity;
		
		//speed.scalarMul(veloity*direction);
		
		
		bullets.add(bullet);
		
			
		
		
		
	}
	
	
	public void update(float elapsedTime){
		Bullet bullet=new Bullet();
	 	
		 //elapsedtime=1/60 then multiply v by 1 
	 	
	
		SimpleVector adjustedVelocity= bullet.velocity;
		adjustedVelocity.scalarMul(elapsedTime*60);
		 bullet.position.add(bullet.velocity);
	}
	
	
}
