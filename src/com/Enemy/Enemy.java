package com.Enemy;
import com.Sprite.SimpleSpriteToken;
import com.Sprite.SpriteManager;
import com.threed.jpct.Logger;
import com.threed.jpct.SimpleVector;
import java.util.Vector;

public class Enemy {
	SimpleVector position;
	SimpleVector velocity;
	int health;
	SimpleSpriteToken token;
	public Enemy()
	{
		token=SpriteManager.GetInstance().AddSimpleSprite("enemy_blueprint", 0);
		//token=SpriteManager.GetInstance().AddSimpleSpriteBlueprint("sprite_blueprint_label");	
		token.SetPosition(new SimpleVector(400,300,0));
	}
	
	public void Update(float elapsedtime){

		//Logger.log("Enemy Updated");
			SimpleVector adjustedVelocity = new SimpleVector(10,10,0);
			adjustedVelocity.scalarMul(elapsedtime * 60.0f);
			
			position.calcAdd(adjustedVelocity);
			//Logger.log(position.toString());
			token.SetPosition(position);
}	
}
