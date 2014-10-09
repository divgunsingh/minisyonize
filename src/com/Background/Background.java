package com.Background;

import com.Provider.ScreenInfoProvider;
import com.Sprite.ScrollingSpriteToken;
import com.Sprite.SimpleSpriteToken;
import com.Sprite.SpriteBlueprintProvider;
import com.Sprite.SpriteManager;
import com.threed.jpct.SimpleVector;

public class Background {
	SimpleSpriteToken token;
	public Background(float scaledSpritePixelSize) {
		for (int y = 0; y < ScreenInfoProvider.GetInstance().ScreenHeight; y += scaledSpritePixelSize+50) {
			for(int x = 0; x < ScreenInfoProvider.GetInstance().ScreenWidth; x += scaledSpritePixelSize){
				// add new scrolling sprite
				 ScrollingSpriteToken token = SpriteManager.GetInstance().AddScrollingSprite("background_blueprint", -2);
				// move scrolling sprite to (x,y)
				token.SetPosition(new SimpleVector(x, y, 0));
			} 
		}
	}
}
