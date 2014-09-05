package com.Enemy;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.Sprite.SimpleSpriteToken;
import com.Sprite.SpriteManager;
import com.threed.jpct.SimpleVector;

public class EnemyManager {
	int screenwidth,screenheight;
	int w,h;
	int max, min;
	float enemySpeed;
	SimpleVector playerPosition;
	
	public static List<Enemy> enemies;
		
	
	public EnemyManager(SimpleVector PlayerPosition){
		enemies = new ArrayList<Enemy>();	
		playerPosition = PlayerPosition;
	}
	
	public void Update(float elapsedTime){
		for(Enemy e : enemies){
			e.Update(elapsedTime);
		}
	}
	public void SpawnEnergy(){
		SpriteManager.GetInstance();
		SimpleVector positionOfEnemy = RandomlyGenerateSpawnLocation();
		
		SimpleVector enemyVelocity = playerPosition;
		enemyVelocity.sub(positionOfEnemy);
		enemyVelocity.normalize();
		enemyVelocity.scalarMul(enemySpeed);
		
		Enemy newEnemy = new Enemy();
		newEnemy.health = 100;
		newEnemy.velocity = enemyVelocity;
		newEnemy.position = positionOfEnemy;
		
		enemies.add(newEnemy);
	}	
	
public SimpleVector RandomlyGenerateSpawnLocation(){
       max=w+h;
       
      Random r= new Random();
     int randomLocation = r.nextInt(max);
      
      if(randomLocation <= w)
      {
    	  // return a new SimpleVector with x = randomLocation and y = 0
    	  return new SimpleVector(randomLocation, 0, 0);
      }else{  
    	  return new SimpleVector(0, randomLocation - w, 0);
    	  // return a new SimpleVector with x = 0 and y = randomLocation
       }
     
}
}
