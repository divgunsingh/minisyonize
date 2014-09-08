package com.Sprite;

import com.threed.jpct.FrameBuffer;
import com.threed.jpct.Logger;
import com.threed.jpct.SimpleVector;
import com.threed.jpct.Texture;
import com.threed.jpct.TextureManager;

import java.util.UUID;

public class AnimatedSprite extends BaseSprite {
    /* BASIC IMAGE DATA */
    private Texture _atlasTexture;
    public Texture GetTexture() {
        return _atlasTexture;
    }

    int _textureWidth;
    int _textureHeight;
    int _scaledPixelWidth;
    int _scaledPixelHeight;

    public int GetScaledPixelWidth(){return _scaledPixelWidth;}
    public int GetScaledPixelHeight() {return _scaledPixelHeight;}

    /* ANIMATION DATA */
    private AnimationTracker _animation;
    public int GetAnimationIndex(){return _animation.GetCurrentAnimation();}
    public void SetAnimationIndex(int animationIndex){_animation.SwitchAnimation(animationIndex);}

    public void FireTemporaryAnimation(int animationIndex){
        _animation.FireTemporaryAnimation(animationIndex);
    }

    /* CONSTRUCTOR */
    public AnimatedSprite(String spriteName){
        super();

        // aquire data
        AnimatedSpriteBlueprint blueprintData = SpriteBlueprintProvider.GetInstance().GetAnimatedSprite(spriteName);

        // set texture
        _atlasTexture = TextureManager.getInstance().getTexture(blueprintData.TextureName);
        SetScale(blueprintData.Scale);
        SetFrameSize(blueprintData.Width, blueprintData.Height);

        // other stuff
        SetPosition(blueprintData.Position);
        _animation = new AnimationTracker(blueprintData.FrameLength, blueprintData.FrameWidths);
    }

    /* IMAGE ADJUSTMENTS */
    public void SetFrameSize(int width, int height){
        _textureWidth = width;
        _textureHeight = height;

        RecalculateAdjustedTextureSize();
    }

    public void RecalculateAdjustedTextureSize(){
        _scaledPixelWidth = (int) (_textureWidth * Scale);
        _scaledPixelHeight = (int) (_textureHeight * Scale);
    }

    /* DRAW HELPERS */
    private TextureCoords IndexToCoordinates(int animation, int frame){
        int atlasWidth = _atlasTexture.getWidth();
        int atlasHeight = _atlasTexture.getHeight();

        // ensure index exists
        int targetX = _textureWidth * frame;
        int targetY = _textureHeight * animation;

        if(targetX < atlasWidth && targetY < atlasHeight)
            return new TextureCoords(targetX, targetY);

        // otherwise, couldn't find the index
        return null;
    }

    /* LOOP */
    public void Update(float elapsedTime){
        _animation.Update(elapsedTime);
    }

    public void Draw(FrameBuffer fb){
        TextureCoords target = IndexToCoordinates(_animation.GetCurrentAnimation(),
                                                  _animation.GetCurrentFrame());

        fb.blit(_atlasTexture, target.x, target.y, (int) Position.x, (int) Position.y,
                _textureWidth, _textureHeight,
                (int) (_textureWidth * Scale), (int) (_textureHeight * Scale),
                255, false);
    }
}
