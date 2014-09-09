package com.Bullet;

import com.Collision.CollisionManager;
import com.Collision.ICollisionToken;
import com.Collision.SphereCollisionToken;
import com.CollisionPayload.BulletCollisionPayload;
import com.CollisionPayload.ICollisionPayload;
import com.Enemy.Enemy;
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

public class Bullet {
	public SimpleVector position;
	public SimpleVector velocity;
	Boolean IsFlaggedForCleaning;

	SphereCollisionToken collisionToken;
	SimpleSpriteToken spriteToken;

	float speed = 15;
	public int damage = 1;

	public Bullet(SimpleVector position, SimpleVector direction) {
		spriteToken = SpriteManager.GetInstance().AddSimpleSprite(
				"bullet_blueprint", 0);
		collisionToken = CollisionManager.GetInstance().AddCollisionSphere(50f,
				new BulletCollisionPayload(damage));

		direction.scalarMul(speed);
		this.velocity = direction;

		setPosition(position);
		
		IsFlaggedForCleaning = false;

		Messager.GetInstance().Subscribe(CollisionMessage.class, new IAction() {
			public void Invoke(IMessage message) {
				OnCollide(((CollisionMessage) message)
						.GetRelevantPayload(collisionToken));
			}
		});
	}

	public void Update(float elapsedTime) {
		SimpleVector adjustedVelocity = velocity;
		adjustedVelocity.scalarMul(elapsedTime * 60.0f);

		setPosition(position.calcAdd(adjustedVelocity));

		if (ScreenInfoProvider.GetInstance().IsCoordinateOutsideOfScreen(
				position, spriteToken.GetScaledPixelWidth()))
			delete();
	}

	public void delete() {
		spriteToken.Delete();
		collisionToken.Delete();
		IsFlaggedForCleaning = true;
	}

	public void setPosition(SimpleVector targetPosition) {
		position = targetPosition;
		spriteToken.SetPosition(targetPosition);
		collisionToken.SetPosition(targetPosition);
	}

	public void OnCollide(ICollisionPayload payload) {
		if (payload == null)
			return;

		if (payload instanceof EnemyCollisionPayload) {
			Logger.log("BULLET FOUND ENEMY (" + position + ")");
			delete();
		}

	}
}
