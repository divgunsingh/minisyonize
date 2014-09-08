package com.Sprite;
import com.threed.jpct.SimpleVector;

public class ScrollingSpriteBlueprint {
    public String TextureName;
    public SimpleVector Position;
    public float Scale;
    public float FrameTime;
    public int FrameDeltaX;
    public int FrameDeltaY;

    public ScrollingSpriteBlueprint(String tex, SimpleVector position, float scale, float frameTime, int frameDeltaX, int frameDeltaY){
        TextureName = tex;
        Position = position;
        Scale = scale;
        FrameTime = frameTime;
        FrameDeltaX = frameDeltaX;
        FrameDeltaY = frameDeltaY;
    }
}
