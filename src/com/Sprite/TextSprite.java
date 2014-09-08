package com.Sprite;

import com.threed.jpct.FrameBuffer;
import com.threed.jpct.Logger;
import com.threed.jpct.SimpleVector;
import com.threed.jpct.Texture;
import com.threed.jpct.TextureManager;

import java.util.UUID;

public class TextSprite extends BaseSprite{
    /* BASIC IMAGE DATA */
    private Texture _atlasTexture;
    public Texture GetTexture() {return _atlasTexture;}

    int _textureWidth;
    int _textureHeight;
    int _scaledPixelWidth;
    int _scaledPixelHeight;

    public int GetScaledPixelWidth(){return _scaledPixelWidth;}
    public int GetScaledPixelHeight() {return _scaledPixelHeight;}

    /* TEXT DATA */
    private char[] _alphabet;

    private String _message;
    public String GetMessage() {return _message;}
    public void SetMessage(String value) {_message = value;}

    /* CONSTRUCTOR */
    public TextSprite(String spriteName){
        super();

        // aquire data
        TextSpriteBlueprint blueprintData = SpriteBlueprintProvider.GetInstance().GetTextSprite(spriteName);

        // set image
        _atlasTexture = TextureManager.getInstance().getTexture(blueprintData.TextureName);
        SetScale(blueprintData.Scale);
        SetFrameSize(blueprintData.Width, blueprintData.Height);

        // other stuff
        _alphabet = blueprintData.CharOrder;
        _message = blueprintData.Message;
        SetPosition(blueprintData.Position);
    }

    /* IMAGE ADJUSTMENTS */
    private void SetFrameSize(int width, int height){
        _textureWidth = width;
        _textureHeight = height;

        RecalculateAdjustedTextureSize();
    }

    public void RecalculateAdjustedTextureSize(){
        _scaledPixelWidth = (int) (_textureWidth * Scale);
        _scaledPixelHeight = (int) (_textureHeight * Scale);
    }

    /* DRAW HELPERS */
    private TextureCoords IndexToCoordinates(int index){
        int atlasWidth = _atlasTexture.getWidth();
        int atlasHeight = _atlasTexture.getHeight();
        int imagesInAtlasX = atlasWidth / _textureWidth;
        int imagesInAtlasY = atlasHeight / _textureHeight;

        // look for the index
        int iterator = 0;
        for(int y = 0; y < imagesInAtlasY; ++y){
            for(int x = 0; x < imagesInAtlasX; ++x){
                if(index == iterator)
                    return new TextureCoords(x * _textureWidth, y * _textureHeight);

                iterator ++;
            }
        }

        // otherwise, couldn't find the index
        return null;
    }

    private int IndexFromCharacter(char character){
        for(int i = 0; i < _alphabet.length; ++i){
            if(character == _alphabet[i])
                return i;
        }

        return -1;
    }

    /* LOOP */
    public void Update(float elapsedTime){}

    public void Draw(FrameBuffer fb) {
        int targetY = (int) Position.y;

        for(int i = 0; i < _message.length(); ++i){
            int targetX = (int) Position.x + (int) (i * (Scale * _textureWidth));

            int characterIndex = IndexFromCharacter(_message.charAt(i));
            // if -1 then the character doesn't exist in the atlas
            if(characterIndex == -1)
                return;

            TextureCoords atlasCoords = IndexToCoordinates(characterIndex);

            fb.blit(_atlasTexture, atlasCoords.x, atlasCoords.y, targetX, targetY,
                    _textureWidth, _textureHeight,
                    (int) (_textureWidth * Scale), (int) (_textureHeight * Scale),
                    255, false);
        }
    }
}
