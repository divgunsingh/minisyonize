package com.Sprite;

import com.threed.jpct.Logger;
import com.threed.jpct.SimpleVector;

import java.util.UUID;

public abstract class BaseSprite implements ISprite{
    /* BASIC */
    protected UUID Id;
    public UUID GetId() {return Id;}
    public void SetId(UUID value) {Id = value;}

    /* POSITIONING */
    protected SimpleVector Position;
    public SimpleVector GetPosition(){
        return Position;
    }
    public void SetPosition(SimpleVector value){
        Position = value;
    }

    protected float Scale;
    public float GetScale() {return Scale;}
    public void SetScale(float value) {
        Scale = value;
        RecalculateAdjustedTextureSize();
    }

    public SimpleVector GetScaledCentre(){
        return new SimpleVector(GetScaledPixelWidth(), GetScaledPixelHeight() / 2, 0);
    }

    /* DEFAULT SPECIAL REQUESTS */
    public String GetMessage() {
        Logger.log("WARNING! GetMessage called on non-TextSprite"); return null;}
    public void SetMessage(String value) {Logger.log("WARNING! SetMessage called on non-TextSprite");}

    public int GetAnimationIndex() {Logger.log("WARNING! GetAnimationIndex called on non-AnimatedSprite"); return -1;}
    public void SetAnimationIndex(int value) {Logger.log("WARNING! SetMessage called on non-AnimatedSprite");}

    public void FireTemporaryAnimation(int animationIndex){Logger.log("WARNING! FireTemporaryAnimation called on non-AnimatedSprite");}

    /* CONSTRUCTOR */
    public BaseSprite(){
        Id = UUID.randomUUID();
    }
}
