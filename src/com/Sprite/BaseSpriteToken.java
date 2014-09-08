package com.Sprite;

import com.threed.jpct.SimpleVector;
import com.threed.jpct.Texture;

import java.util.UUID;

public abstract class BaseSpriteToken implements ISpriteToken{
    private UUID _id;
    private int _layer;

    public UUID GetId(){return _id;}
    public int GetLayer(){return _layer;}

    public BaseSpriteToken(ISprite reference, int layer){
        _id = reference.GetId();
        _layer = layer;
    }

    public void SetPosition(SimpleVector position){
        SpriteManager.GetInstance().UpdateSpritePosition(position, this);
    }

    public void SetScale(float scale){
        SpriteManager.GetInstance().UpdateSpriteScale(scale, this);
    }

    public int GetScaledPixelWidth(){
        return SpriteManager.GetInstance().GetSpriteScaledPixelWidth(this);
    }
    public int GetScaledPixelHeight(){
        return SpriteManager.GetInstance().GetSpriteScaledPixelHeight(this);
    }

    public SimpleVector GetScaledCentre(){
        return SpriteManager.GetInstance().GetSpriteScaledCentre(this);
    }

    public void Delete(){
        SpriteManager.GetInstance().DeleteSprite(this);
    }
}
