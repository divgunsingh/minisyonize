package com.States;



import android.content.Intent;

import com.Bullet.Bullet;
import com.Bullet.BulletManager;
import com.Collision.CollisionManager;
import com.Enemy.Enemy;
import com.Enemy.EnemyManager;
import com.Main.HelloWorld;
import com.Messaging.IAction;
import com.Messaging.IMessage;
import com.Messaging.Messager;
import com.Messaging.PlayerDeadMessage;
import com.Player.Player;
import com.Provider.ScreenInfoProvider;
import com.Score.PlayerHealthScore;
import com.Score.ScoreManager;
import com.Sprite.SpriteManager;
import com.States.IState;
import com.minisyonize.Splash;
import com.threed.jpct.FrameBuffer;
import com.threed.jpct.SimpleVector;
import com.threed.jpct.Logger;

public class PlayState implements IState {
	int w,h;

	Player player;
	EnemyManager enemymanager;
	ScoreManager scoreManager;
	IState scoreCardState;
	public IState GetNextState(){return scoreCardState;}
	
	boolean isReadyToChangeState;
	public boolean IsReadyToChangeState(){ return isReadyToChangeState;}

	public void initialize() {
		 
		player = new Player();
		h=ScreenInfoProvider.GetInstance().ScreenHeight;
		w=ScreenInfoProvider.GetInstance().ScreenWidth;
		enemymanager = new EnemyManager(player.position,h,w);
		scoreManager.GetInstance();
		PlayerHealthScore.GetInstance();
		scoreCardState = null;
		isReadyToChangeState = false;
		
		Messager.GetInstance().Subscribe(PlayerDeadMessage.class, new IAction(){
			public void Invoke(IMessage message){
				OnPlayerDead((PlayerDeadMessage) message);
			}
		});
	}

	public void update(float elapsedtime) {
		player.update(elapsedtime);
	    //Logger.log("player updated"+player.toString());
		enemymanager.Update(elapsedtime);
		//Logger.log("enemy manager updated "+enemymanager.toString());
		CollisionManager.GetInstance().Update(elapsedtime);
		//Logger.log("collision manager");
		SpriteManager.GetInstance().Update(elapsedtime);
		// Logger.log("Sprite manager");
	}

	public void draw(FrameBuffer fb) {
		SpriteManager.GetInstance().Draw(fb);
	}
	
	private void OnPlayerDead(PlayerDeadMessage message){
		isReadyToChangeState = true;
		scoreCardState = new ScoreCardState();
		
	
		
	}
}
