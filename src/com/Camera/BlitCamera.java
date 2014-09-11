package com.Camera;

import com.threed.jpct.SimpleVector;

public class BlitCamera {
    private static BlitCamera _instance;
    public static BlitCamera GetInstance(){
        if(_instance == null)
            _instance = new BlitCamera();

        return _instance;
    }

    public SimpleVector Position;
    public float Zoom;

    private BlitCamera(){
        Position = new SimpleVector(0,0,0);
        Zoom = 1.0f;
    }
}
