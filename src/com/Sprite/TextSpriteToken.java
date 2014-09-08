package com.Sprite;

import com.threed.jpct.SimpleVector;
import com.threed.jpct.Texture;

import java.util.UUID;

public class TextSpriteToken extends BaseSpriteToken {
    public TextSpriteToken(TextSprite reference, int layer){
        super(reference, layer);
    }

    public void SetMessage(String message){
        SpriteManager.GetInstance().UpdateSpriteMessage(message, this);
    }
}
