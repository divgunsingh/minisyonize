package com.Enemy;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

import android.util.Log;

import com.Health.Health;
import com.Sprite.SimpleSpriteToken;
import com.Sprite.SpriteManager;
import com.Timer.Timer;
import com.threed.jpct.Logger;
import com.threed.jpct.SimpleVector;

public class EnemyManager {
	int screenwidth, screenheight;
	public int width, height;
	public int max, min;
	float enemySpeed = 1f;
	SimpleVector playerPosition;
	
	Timer spawnTimer;

	public static CopyOnWriteArrayList<Enemy> enemies;

	public EnemyManager(SimpleVector PlayerPosition, int h, int w) {
		enemies = new CopyOnWriteArrayList<Enemy>();
		playerPosition = PlayerPosition;
		width = w;
		height = h;
		
		spawnTimer = new Timer(3);
	}

	public void Update(float elapsedTime) {
		spawnTimer.update(elapsedTime);
		if(spawnTimer.isFinished()){
			SpawnEnemy();
			spawnTimer.Reset(3);
		}
		
		for (Enemy e : enemies) {

			if (e.isReadyForCleanUp) {

				enemies.remove(e);
			} else {

				e.Update(elapsedTime);
			}
		}
	}

	public void SpawnEnemy() {
		SimpleVector position = RandomlyGenerateSpawnLocation();

		SimpleVector direction = playerPosition.calcSub(position);
		direction.scalarMul(1 / direction.length());

		Enemy newEnemy = new Enemy(position, direction);
		enemies.add(newEnemy);
	}

	public SimpleVector RandomlyGenerateSpawnLocation() {
		max = width + height;

		Random r = new Random();
		int randomLocation = r.nextInt(max);

		if (randomLocation <= width) {
			// return a new SimpleVector with x = randomLocation and y = 0
			return new SimpleVector(randomLocation, 0, 0);
		} else {
			return new SimpleVector(0, randomLocation - width, 0);
			// return a new SimpleVector with x = 0 and y = randomLocation
		}

	}
}
