package com.Sprite;

import com.threed.jpct.FrameBuffer;
import com.threed.jpct.Texture;
import com.threed.jpct.TextureManager;

public class ScrollingSprite extends BaseSprite {
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

    /* SCROLLING DATA */
    public ScrollingTracker _scrolling;

    /* CONSTRUCTOR */
    public ScrollingSprite(String spriteName){
        super();

        // acquire data
        ScrollingSpriteBlueprint blueprintData = SpriteBlueprintProvider.GetInstance().GetScrollingSprite(spriteName);

        // set image
        image = TextureManager.getInstance().getTexture(blueprintData.TextureName);
        SetScale(blueprintData.Scale);
        RecalculateTextureSize();

        // scrolling
        _scrolling = new ScrollingTracker(blueprintData.FrameTime,
                                          blueprintData.FrameDeltaX * -1, blueprintData.FrameDeltaY,
                                          image.getWidth(), image.getHeight());
        // I'm multiplying the x by -1 cause that makes it make sense *shrug*

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
    public void Update(float elapsedTime){
        _scrolling.Update(elapsedTime);
    }

    public void Draw(FrameBuffer fb){
        TextureCoords target = _scrolling.GetOffset();

        fb.blit(image, target.x, target.y, (int) Position.x, (int) Position.y,
                _textureWidth, _textureHeight,
                (int) (_textureWidth * Scale), (int) (_textureHeight * Scale),
                255, false);
    }
}
