package com.States;

import com.Collision.CollisionManager;
import com.Enemy.Enemy;
import com.Enemy.EnemyManager;
import com.Player.Player;
import com.Provider.ScreenInfoProvider;
import com.Sprite.SpriteManager;
import com.States.IState;
import com.threed.jpct.FrameBuffer;
import com.threed.jpct.SimpleVector;

public class PlayState implements IState {
	int w,h;

	Player player;
	EnemyManager enemymanager;

	public void Init() {
		player = new Player();
		h=ScreenInfoProvider.GetInstance().ScreenHeight;
		w=ScreenInfoProvider.GetInstance().ScreenWidth;
		enemymanager = new EnemyManager(player.position,h,w);
	}

	public void update(float elapsetime) {
		player.update(elapsetime);
		enemymanager.Update(elapsetime);
		CollisionManager.GetInstance().Update(elapsetime);
		SpriteManager.GetInstance().Update(elapsetime);
	}

	public void draw(FrameBuffer fb) {
		SpriteManager.GetInstance().Draw(fb);

	}
}
