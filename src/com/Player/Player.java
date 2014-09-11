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
    int h,w;
	BulletManager bulletManager;
	
	SphereCollisionToken collisionToken;
	AnimatedSpriteToken spriteToken;

	public Player() {
		health=new Health(5);
		spriteToken = SpriteManager.GetInstance().AddAnimatedSprite("playerlabel", 0);
		collisionToken = CollisionManager.GetInstance().AddCollisionSphere(5f,
				new PlayerCollisionPayload());
		h=ScreenInfoProvider.GetInstance().ScreenHeight;
		w=ScreenInfoProvider.GetInstance().ScreenWidth;
		setPosition(new SimpleVector(w/2, h-150, 0));
		
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
	}
   public void setPosition(SimpleVector pos ){
	   	position=pos;
	  	collisionToken.SetPosition(pos);
	  	spriteToken.SetPosition(pos);
   	}
	public void Fire(SimpleVector targetPosition) {

		bulletManager.createBullet(targetPosition);
	}

	public void update(float time) {
		if(health.isDead()){
			
			Messager.GetInstance().Publish(new PlayerDeadMessage());
			
		}
		bulletManager.update(1.0f / 60.0f);

	}

	public void OnCollide(ICollisionPayload payload) {
		// Logger.log("Collision Detected");
		if (payload == null)
			return;

		if (payload instanceof EnemyCollisionPayload) {
			EnemyCollisionPayload enemypayload = (EnemyCollisionPayload) payload;
			
			health.Damage(enemypayload.damage);
			
			
		}
	}
}
