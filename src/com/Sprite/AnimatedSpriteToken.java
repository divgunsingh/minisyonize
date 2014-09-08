package com.Sprite;

public class AnimatedSpriteToken extends BaseSpriteToken{
    public AnimatedSpriteToken(AnimatedSprite reference, int layer){
        super(reference, layer);
    }

    public void SwitchAnimation(int animationIndex){
        SpriteManager.GetInstance().SwitchSpriteAnimation(animationIndex, this);
    }

    public void FireTemporaryAnimation(int animationIndex){
        SpriteManager.GetInstance().FireTemporarySpriteAnimation(animationIndex, this);
    }
}
