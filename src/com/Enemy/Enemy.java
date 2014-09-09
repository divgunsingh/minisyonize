package com.Enemy;

import android.os.Message;

import com.Collision.CollisionManager;
import com.Collision.ICollisionToken;
import com.Collision.SphereCollisionToken;
import com.CollisionPayload.BulletCollisionPayload;
import com.CollisionPayload.ICollisionPayload;
import com.CollisionPayload.PlayerCollisionPayload;
import com.Health.Health;
import com.CollisionPayload.EnemyCollisionPayload;
import com.Messaging.CollisionMessage;
import com.Messaging.IAction;
import com.Messaging.IMessage;
import com.Messaging.Messager;
import com.Provider.ScreenInfoProvider;
import com.Sprite.SimpleSpriteToken;
import com.Sprite.SpriteManager;
import com.threed.jpct.Logger;
import com.threed.jpct.SimpleVector;
import java.util.Vector;

public class Enemy {
	SimpleVector position;
	SimpleVector velocity;
	Health health;
	boolean isReadyForCleanUp;
	SimpleSpriteToken token;
	int health2;

	float speed = 5;

	SphereCollisionToken collisionToken;

	public Enemy(SimpleVector position, SimpleVector direction) {
		health = new Health(1);

		isReadyForCleanUp = false;
		collisionToken = CollisionManager.GetInstance().AddCollisionSphere(50f,
				new EnemyCollisionPayload(10));
		token = SpriteManager.GetInstance().AddSimpleSprite("enemy_blueprint",
				0);

		direction.scalarMul(speed);
		this.velocity = direction;
		this.setPosition(position);

		Messager.GetInstance().Subscribe(CollisionMessage.class, new IAction() {
			public void Invoke(IMessage message) {
				OnCollide(((CollisionMessage) message)
						.GetRelevantPayload(collisionToken));
			}
		});
	}

	public void Update(float elapsedtime) {
		SimpleVector adjustedVelocity = velocity;
		adjustedVelocity.scalarMul(elapsedtime * 60.0f);

		token.SetPosition(position.calcAdd(adjustedVelocity));

		if (health.isDead())
			delete();

		if (ScreenInfoProvider.GetInstance().IsCoordinateOutsideOfScreen(
				position, token.GetScaledPixelWidth()))
			delete();
	}

	public void OnCollide(ICollisionPayload payload) {
		// Logger.log("Collision Detected");
		if (payload == null)
			return;

		if (payload instanceof PlayerCollisionPayload) {
			Logger.log("ENEMY MET PLAYER");
			delete();
		}
		if (payload instanceof BulletCollisionPayload) {
			Logger.log("ENEMY FOUND BULLET (" + position + ")");
			BulletCollisionPayload bulletpayload = (BulletCollisionPayload) payload;
			health.Damage(bulletpayload.damage);
		}

	}

	public void setPosition(SimpleVector pos) {
		collisionToken.SetPosition(pos);
		token.SetPosition(pos);
		position = pos;
	}

	public void delete() {
		token.Delete();
		collisionToken.Delete();

		isReadyForCleanUp = true;
	}

}
