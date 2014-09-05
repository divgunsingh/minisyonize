package com.Enemy;
import com.Sprite.SimpleSpriteToken;
import com.Sprite.SpriteManager;
import com.threed.jpct.SimpleVector;
import java.util.Vector;

public class Enemy {
	SimpleVector position;
	SimpleVector velocity;
	int health;
	SimpleSpriteToken token;
	public Enemy()
	{
		//token=SpriteManager.GetInstance().AddSimpleSpriteBlueprint("sprite_blueprint_label");	
		
	}
	
	public void Update(float elapsedtime){
		
			SimpleVector adjustedVelocity = velocity;
			adjustedVelocity.scalarMul(elapsedtime * 60.0f);
			
			position.add(adjustedVelocity);
			token.SetPosition(position);
}	
}
