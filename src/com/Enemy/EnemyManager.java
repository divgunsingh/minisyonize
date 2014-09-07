package com.Enemy;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import android.util.Log;

import com.Sprite.SimpleSpriteToken;
import com.Sprite.SpriteManager;
import com.threed.jpct.Logger;
import com.threed.jpct.SimpleVector;

public class EnemyManager {
	int screenwidth,screenheight;
	 public int width,height;
	public int max, min;
	float enemySpeed;
	SimpleVector playerPosition;
	
	public static List<Enemy> enemies;
		
	
	public EnemyManager(SimpleVector PlayerPosition,int h, int w){
		enemies = new ArrayList<Enemy>();	
		playerPosition = PlayerPosition;
		width=w;
		height=h;
		String str = String.valueOf(h);
		Logger.log(str);
		Logger.log("In Enemy Manager");
		SpawnEnemy();
	
	}
	
	public void Update(float elapsedTime){
		for(Enemy e : enemies){
			e.Update(elapsedTime);
		}
	}
	public void SpawnEnemy(){
		SpriteManager.GetInstance();
		SimpleVector positionOfEnemy = RandomlyGenerateSpawnLocation();
		
		SimpleVector enemyVelocity = playerPosition;
		enemyVelocity.sub(positionOfEnemy);
		//enemyVelocity.normalize();
		float magnitude = enemyVelocity.length();
		enemyVelocity.scalarMul(magnitude);
		enemyVelocity.scalarMul(enemySpeed);
		
		Logger.log("enemy -> player vector magnitude: " + magnitude);
		Logger.log("enemy position: " + positionOfEnemy.toString());
		Logger.log("player position: " + playerPosition.toString());
		Logger.log("enemy velocity: " + enemyVelocity.toString());
		
		Enemy newEnemy = new Enemy();
		newEnemy.health = 100;
		newEnemy.velocity = enemyVelocity;
		newEnemy.position = positionOfEnemy;
		
		enemies.add(newEnemy);
	}	
	
	
	
public SimpleVector RandomlyGenerateSpawnLocation(){
       max=width+height;
       
      Random r= new Random();
     int randomLocation = r.nextInt(max);
      
      if(randomLocation <= width)
      {
    	  // return a new SimpleVector with x = randomLocation and y = 0
    	  return new SimpleVector(randomLocation, 0, 0);
      }else{  
    	  return new SimpleVector(0, randomLocation - width, 0);
    	  // return a new SimpleVector with x = 0 and y = randomLocation
       }
     
}
}
