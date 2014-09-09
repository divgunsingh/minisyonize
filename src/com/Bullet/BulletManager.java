package com.Bullet;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import android.view.VelocityTracker;

import com.Enemy.Enemy;
import com.threed.jpct.Logger;
import com.threed.jpct.SimpleVector;

public class BulletManager {
   SimpleVector playerPosition;
	CopyOnWriteArrayList<Bullet> bullets;

	public BulletManager(SimpleVector playerPosition) {
		bullets = new CopyOnWriteArrayList<Bullet>();
		this.playerPosition=playerPosition;
	}

	public void createBullet(SimpleVector positonOnClick) {
		SimpleVector direction = positonOnClick.calcSub(playerPosition);
		direction.scalarMul(1 / direction.length());
		
		Bullet bullet = new Bullet(playerPosition,direction);
		bullets.add(bullet);
	}

	public void update(float elapsedTime) {

		for (Bullet b : bullets) {

		//	Logger.log(b.toString());
			if (b.IsFlaggedForCleaning) {

				bullets.remove(b);

			} else {

				b.Update(elapsedTime);
			}
		}
	}

}
