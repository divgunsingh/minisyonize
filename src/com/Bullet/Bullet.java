package com.Bullet;

import com.Enemy.Enemy;
import com.Sprite.SimpleSpriteToken;
import com.Sprite.SpriteManager;
import com.threed.jpct.Logger;
import com.threed.jpct.SimpleVector;


public class Bullet {
	SimpleSpriteToken token;
	public int damage;
	public SimpleVector bulletPosition;
	public SimpleVector bulletVelocity;
	
	public Bullet(){
		
		token=SpriteManager.GetInstance().AddSimpleSprite("bullet_blueprint", 0);	
		token.SetPosition(new SimpleVector(0,5,0));
		}
	public void Update(float elapsedTime){
		SimpleVector adjustedVelocity = bulletVelocity;
		adjustedVelocity.scalarMul(elapsedTime * 60.0f);
		//Logger.log("BULLET>UPDATE");
		bulletPosition.add(adjustedVelocity);
		setPosition(bulletPosition.calcAdd(adjustedVelocity));
		//Logger.log(bulletPosition.toString());
		//Logger.log(adjustedVelocity.toString());
		//String val=Float.toString(elapsedTime);
		//Logger.log(val);
		
		}
	
	public void setPosition(SimpleVector targetPosition) {
		
		bulletPosition=targetPosition;
		token.SetPosition(targetPosition);
	}
	
	
}
