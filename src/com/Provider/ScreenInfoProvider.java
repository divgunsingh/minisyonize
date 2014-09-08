package com.Provider;

import com.threed.jpct.SimpleVector;

public class ScreenInfoProvider {
    private static ScreenInfoProvider _instance = null;
    public static ScreenInfoProvider GetInstance(){
        if(_instance == null)
            _instance = new ScreenInfoProvider();

        return _instance;
    }

    public int ScreenWidth;
    public int ScreenHeight;
    public SimpleVector GetScreenCentre(){ return new SimpleVector(ScreenWidth / 2, ScreenHeight / 2, 0); }

    private ScreenInfoProvider(){}

    public boolean IsCoordinateOutsideOfScreen(SimpleVector coordinate, int bufferZone){
        if(coordinate.x < 0 - bufferZone || coordinate.x > ScreenWidth + bufferZone ||
           coordinate.y < 0 - bufferZone || coordinate.y > ScreenHeight + bufferZone)
            return true;

        return false;
    }

    public void Initialize(int screenWidth, int screenHeight){
        ScreenWidth = screenWidth;
        ScreenHeight = screenHeight;
    }
}
