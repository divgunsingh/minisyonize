package com.Cloud;

import com.Collision.*;

import com.CollisionPayload.BulletCollisionPayload;
import com.CollisionPayload.CloudCollisionPayload;
import com.CollisionPayload.EnemyCollisionPayload;
import com.CollisionPayload.ICollisionPayload;
import com.CollisionPayload.PlayerCollisionPayload;
import com.Enemy.Enemy;
import com.Messaging.CollisionMessage;
import com.Messaging.IAction;
import com.Messaging.IMessage;
import com.Messaging.Messager;
import com.Sprite.SimpleSpriteToken;
import com.Sprite.SpriteManager;
import com.threed.jpct.Logger;
import com.threed.jpct.SimpleVector;


public class Cloud {
	SimpleSpriteToken token;
	public int damage;
	public SimpleVector CloudPosition;
	public SimpleVector CloudVelocity;
	SphereCollisionToken collisiontoken;
	Boolean IsFlaggedForCleaning;
	public Cloud(){
		
		token=SpriteManager.GetInstance().AddSimpleSprite("cloud_blueprint", 0);	
		token.SetPosition(new SimpleVector(0,5,0));
		collisiontoken=	CollisionManager.GetInstance().AddCollisionSphere(5, new CloudCollisionPayload());
		Messager.GetInstance().Subscribe(CollisionMessage.class, new IAction(){
			public void Invoke(IMessage message){
				OnCollide( ( (CollisionMessage) message ).GetRelevantPayload(collisiontoken) );
			}
		});
		}
		
	public void Update(float elapsedTime){
		SimpleVector adjustedVelocity = CloudVelocity;
		adjustedVelocity.scalarMul(elapsedTime * 60.0f);
	
		CloudPosition.add(adjustedVelocity);
		setPosition(CloudPosition.calcAdd(adjustedVelocity));
		
		
		}
	
	public void setPosition(SimpleVector targetPosition) {
		
		CloudPosition=targetPosition;
		token.SetPosition(targetPosition);
		collisiontoken.SetPosition(targetPosition);
	}
	
	

public void OnCollide(ICollisionPayload payload)
{
	   if(payload == null)
		   return;
	   
	 if(payload instanceof EnemyCollisionPayload ){
		 
		 Delete();
	 }
}

  	public void Delete(){
	
  		collisiontoken.Delete();
  		token.Delete();
  		IsFlaggedForCleaning=true;
  		
	
}
}
