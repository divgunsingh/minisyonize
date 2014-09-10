package com.Score;

import com.Bullet.BulletManager;
import com.Enemy.Enemy;
import com.Messaging.BulletCreatedMessage;
import com.Messaging.CollisionMessage;
import com.Messaging.EnemyDeletedMessage;
import com.Messaging.IAction;
import com.Messaging.IMessage;
import com.Messaging.Messager;
import com.Sprite.SpriteManager;
import com.Sprite.TextSprite;
import com.Sprite.TextSpriteToken;
import com.threed.jpct.Logger;

public class ScoreManager {
TextSpriteToken scoreText,scoreText1,scoreDigits,scoreDigits1;
	int bulletcount=1, enemydeleted=1,count;
	static private ScoreManager instance;

	
	static public ScoreManager GetInstance(){
		if(instance==null)
			instance=new ScoreManager();
		
		return instance;
		
	}
	private ScoreManager() {

		
		scoreText=SpriteManager.GetInstance().AddTextSprite("text_blueprint", 0);
		scoreDigits=SpriteManager.GetInstance().AddTextSprite("digits_blueprint", 0);
		scoreText1=SpriteManager.GetInstance().AddTextSprite("text_blueprint1", 0);
		scoreDigits1=SpriteManager.GetInstance().AddTextSprite("digits_blueprint1", 0);
		

		Messager.GetInstance().Subscribe(BulletCreatedMessage.class, new IAction() {
			public void Invoke(IMessage message) {
				buletAdded();
               Logger.log("Message Bullet Created");
               
            
			}
		});

		Messager.GetInstance().Subscribe(EnemyDeletedMessage.class, new IAction() {
			public void Invoke(IMessage message) {
				enemyDeleted();
				 Logger.log("Enemy deleted");
			}
		});
	}

	public void buletAdded() {
		count=count+bulletcount;
		String str = String.valueOf(count);
		scoreDigits.SetMessage(str);
	}

	public void enemyDeleted() {
		count=count+enemydeleted;
		String str = String.valueOf(count);
		scoreDigits1.SetMessage(str);
	}
	public void resetScore() {
		
		count=0;
		
	}

}
