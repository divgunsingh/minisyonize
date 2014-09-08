package com.Sprite;

import com.threed.jpct.SimpleVector;

import java.util.UUID;

public interface ISpriteToken {
    UUID GetId();
    int GetLayer();
    void Delete();

    int GetScaledPixelWidth();
    int GetScaledPixelHeight();

    SimpleVector GetScaledCentre();
}
