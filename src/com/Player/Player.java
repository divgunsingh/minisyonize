package com.Player;

import com.Bullet.Bullet;
import com.Bullet.BulletManager;
import com.Collision.CollisionManager;
import com.Collision.ICollisionToken;
import com.Collision.SphereCollisionToken;
import com.CollisionPayload.*;
import com.Health.Health;
import com.Messaging.CollisionMessage;
import com.Messaging.EnemyDeletedMessage;
import com.Messaging.IAction;
import com.Messaging.IMessage;
import com.Messaging.Messager;
import com.Messaging.MovePlayerMessage;
import com.Messaging.PlayerDamagedMessage;
import com.Messaging.PlayerDeadMessage;
import com.Messaging.ScreentouchMessage;
import com.Provider.ScreenInfoProvider;
import com.Sprite.AnimatedSpriteToken;
import com.Sprite.SimpleSpriteToken;
import com.Sprite.SpriteManager;
import com.threed.jpct.Logger;
import com.threed.jpct.SimpleVector;

public class Player {
	Health health;
	public SimpleVector position;
	private SimpleVector velocity;
	float friction;
    int h,w;
	BulletManager bulletManager;
	
	SphereCollisionToken collisionToken;
	AnimatedSpriteToken spriteToken;

	public Player() {
		health=new Health(50);
		spriteToken = SpriteManager.GetInstance().AddAnimatedSprite("playerlabel", 0);
		collisionToken = CollisionManager.GetInstance().AddCollisionSphere(5f,
				new PlayerCollisionPayload());
		
		h=ScreenInfoProvider.GetInstance().ScreenHeight;
		w=ScreenInfoProvider.GetInstance().ScreenWidth;
		setPosition(new SimpleVector(w/2, h-150, 0));
		
		velocity = new SimpleVector(0,0,0);
		friction = 1f;
		
		bulletManager = new BulletManager(position);

		Messager.GetInstance().Subscribe(ScreentouchMessage.class,
				new IAction() {
					public void Invoke(IMessage message) {
						ScreentouchMessage castMessage = (ScreentouchMessage) message;
						Fire(new SimpleVector(castMessage.x, castMessage.y, 0));
					}
				});
		
		Messager.GetInstance().Subscribe(CollisionMessage.class, new IAction() {

			@Override
			public void Invoke(IMessage message) {
				OnCollide(((CollisionMessage) message)
						.GetRelevantPayload(collisionToken));
			}
		});
		
		Messager.GetInstance().Subscribe(MovePlayerMessage.class, new IAction() {

			@Override
			public void Invoke(IMessage message) {
				
			
				movePlayer((MovePlayerMessage) message);
				
			}
		});
	}
   public void setPosition(SimpleVector pos ){
	   SimpleVector spritePosition = pos;
	   spritePosition.sub(spriteToken.GetScaledCentre());
	   
	   	position=pos;
	  	collisionToken.SetPosition(pos);
	  	spriteToken.SetPosition(spritePosition);
   	}
	public void Fire(SimpleVector targetPosition) {

		bulletManager.createBullet(targetPosition);
	}

	public void update(float time) {
		if(health.isDead())
		{
			Messager.GetInstance().Publish(new PlayerDeadMessage());
		}
		
		bulletManager.update(1.0f / 60.0f);
		
		// calculate new position
		SimpleVector newPosition = position;
		/*
		newPosition.add(velocity);
		// bind to screen
		ScreenInfoProvider screen = ScreenInfoProvider.GetInstance();
		if(newPosition.x > screen.ScreenWidth);
			newPosition.x = screen.ScreenWidth;
		if(newPosition.x < 0 )
			newPosition.x = 0;
		if(newPosition.y > screen.ScreenHeight)
			newPosition.y = screen.ScreenHeight;
		if(newPosition.y < 0 )
			newPosition.y = 1000;
		
		// update position
		
		setPosition(newPosition);
		*/
		// apply friction
		//velocity.scalarMul(friction);
		//setPosition(newPosition);
		
	}

	public void OnCollide(ICollisionPayload payload) {
		// Logger.log("Collision Detected");
		if (payload == null)
			return;

		if (payload instanceof EnemyCollisionPayload) {
			EnemyCollisionPayload enemypayload = (EnemyCollisionPayload) payload;
			Messager.GetInstance().Publish(new PlayerDamagedMessage());
			health.Damage(enemypayload.damage);
			
			
		}
	}
	
	public void movePlayer(MovePlayerMessage message) {
	
		velocity.add(new SimpleVector(message.ax/100, message.ay/100, 0));
		//setPosition(new SimpleVector(message.ax,message.ay,0));
		Logger.log(velocity.toString());
		
	}
}
