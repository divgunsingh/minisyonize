package com.Sprite;

import com.threed.jpct.FrameBuffer;
import com.threed.jpct.Logger;
import com.threed.jpct.SimpleVector;
import com.threed.jpct.Texture;
import com.threed.jpct.TextureManager;

import java.util.UUID;

public class SimpleSprite extends BaseSprite {
    /* BASIC IMAGE DATA */
    private Texture image;
    public Texture GetTexture() {return image;}
    public void SetTexture(Texture value) {image = value; RecalculateTextureSize();}

    private int _textureWidth;
    private int _textureHeight;
    private int _scaledPixelWidth;
    private int _scaledPixelHeight;

    public int GetScaledPixelWidth(){return _scaledPixelWidth;}
    public int GetScaledPixelHeight() {return _scaledPixelHeight;}

    /* CONSTRUCTOR */
    public SimpleSprite(String spriteName){
        super();

        // aquire data
        SimpleSpriteBlueprint blueprintData = SpriteBlueprintProvider.GetInstance().GetSimpleSprite(spriteName);

        // set image
        image = TextureManager.getInstance().getTexture(blueprintData.TextureName);
        SetScale(blueprintData.Scale);
        RecalculateTextureSize();

        // other stuff
        SetPosition(blueprintData.Position);
    }

    /* IMAGE ADJUSTMENTS */
    private void RecalculateTextureSize(){
        _textureWidth = image.getWidth();
        _textureHeight = image.getHeight();

        RecalculateAdjustedTextureSize();
    }

    public void RecalculateAdjustedTextureSize(){
        _scaledPixelWidth = (int) (image.getWidth() * Scale);
        _scaledPixelHeight = (int) (image.getHeight() * Scale);
    }

    /* LOOP */
    public void Update(float elapsedTime){} // just a placeholder for animated sprite to use...
    // I figure the performance hit of an unnecessary function call is preferable to a cast attempt for every sprite.

    public void Draw(FrameBuffer fb){
        fb.blit(image, 0, 0, (int) Position.x, (int) Position.y, _textureWidth, _textureHeight,
                _scaledPixelWidth, _scaledPixelHeight, 255, false);
    }
}
