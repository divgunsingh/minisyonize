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
	float sspeed;
	
	public Player(){	
		bulletManager = new BulletManager();
		 sspeed=2f;
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
		//position = new SimpleVector(0,200,0);
		SimpleVector directionOfFire=new SimpleVector(0,0,0) ;
		SimpleVector Target2=new SimpleVector();
		Target2=Target;
		//directionOfFire.sub(Target);
		//directionOfFire.normalize();
		Target2.sub(directionOfFire);
		Target2.normalize();
		//bullet=new Bullet();
		Logger.log(Target2.toString());
		//bulletManager.createBullet(Target, directionOfFire, 2, 5f);
		
		
		bulletManager.createBullet( Target,Target2, 2, 0.01f);
	}
	public void update(float time){
		
		bulletManager.update(1.0f / 60.0f);
		
	}
}
