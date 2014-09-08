package com.Sprite;

import com.threed.jpct.FrameBuffer;
import com.threed.jpct.SimpleVector;
import com.threed.jpct.Texture;

import java.util.UUID;

public interface ISprite {
    UUID GetId();
    void SetId(UUID value);

    SimpleVector GetPosition();
    void SetPosition(SimpleVector value);

    float GetScale();
    void SetScale(float value);

    Texture GetTexture();

    String GetMessage();
    void SetMessage(String value);

    int GetAnimationIndex();
    void SetAnimationIndex(int value);

    int GetScaledPixelWidth();
    int GetScaledPixelHeight();

    SimpleVector GetScaledCentre();
    void RecalculateAdjustedTextureSize();

    void FireTemporaryAnimation(int animationIndex);

    void Draw(FrameBuffer fb);
    void Update(float elapsedTime);
}
