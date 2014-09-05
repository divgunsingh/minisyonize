package com.Player;

import com.Bullet.Bullet;
import com.Bullet.BulletManager;
import com.Messaging.IAction;
import com.Messaging.IMessage;
import com.Messaging.Messager;
import com.Messaging.ScreentouchMessage;
import com.Sprite.SimpleSpriteToken;
import com.Sprite.SpriteManager;
import com.threed.jpct.Logger;
import com.threed.jpct.SimpleVector;

public class Player {
	public int health;
	public SimpleVector position;
	SimpleSpriteToken token;
	BulletManager bulletManager;
	Bullet bullet;
	
	public Player(){	
		bulletManager = new BulletManager();
		
		token=SpriteManager.GetInstance().AddSimpleSprite("playerlabel2", 0);	
		token.SetPosition(new SimpleVector(0,1000,0));
		
		position = new SimpleVector(0,0,0);
		
		Messager.GetInstance().Subscribe(ScreentouchMessage.class, new IAction() {
			public void Invoke(IMessage message){
				ScreentouchMessage castMessage = (ScreentouchMessage) message;
				Fire(new SimpleVector(castMessage.x, castMessage.y, 0));
			}});
	}
	
	public void Fire(SimpleVector Target){
		//BulletManager bCreate= new BulletManager();
		//bCreate.createBullet(6, 6);
		//token=SpriteManager.GetInstance().AddSimpleSprite("bullet_blueprint", 0);	
		// token.SetPosition(Target);
		 
		SimpleVector directionOfFire = position;
		directionOfFire.sub(Target);
		directionOfFire.normalize();
		//bullet=new Bullet();
		//Logger.log("TOUCH");
		bulletManager.createBullet(Target, directionOfFire, 2, 5f);
	}
}
