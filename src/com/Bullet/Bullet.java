package com.Bullet;

import com.Sprite.SimpleSpriteToken;
import com.Sprite.SpriteManager;
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
	
	public void setPosition(SimpleVector targetPosition) {
		
		
		bulletPosition=targetPosition;
		token.SetPosition(targetPosition);
	}
	
	
}
