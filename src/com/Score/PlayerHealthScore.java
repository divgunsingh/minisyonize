package com.Score;

import com.Messaging.BulletCreatedMessage;
import com.Messaging.IAction;
import com.Messaging.IMessage;
import com.Messaging.Messager;
import com.Messaging.PlayerDamagedMessage;
import com.Messaging.PlayerDeadMessage;
import com.Sprite.SpriteManager;
import com.Sprite.TextSpriteToken;
import com.threed.jpct.Logger;

public class PlayerHealthScore {

	TextSpriteToken playerHp;
	int hpcount=50;
	static private PlayerHealthScore instance;
		
	static public PlayerHealthScore GetInstance(){
		if(instance==null)
			instance=new PlayerHealthScore();
		
		return instance;
		
	}
		
		private PlayerHealthScore(){
			
			playerHp=SpriteManager.GetInstance().AddTextSprite("digits_blueprint1", 0);
			

			Messager.GetInstance().Subscribe(PlayerDamagedMessage.class, new IAction() {
				public void Invoke(IMessage message) {
					healthDecrease();
	             
	               
	            
				}
			});
			
			
		}
	
	public void healthDecrease(){
		
		hpcount=hpcount-1;
		playerHp.SetMessage(Integer.toString(hpcount));
	}
		
		
		
		
}
