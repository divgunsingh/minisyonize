package com.Sprite;

public class ScrollingTracker {
    private float _frameLength;
    private int _xSize;
    private int _ySize;
    private int _frameDeltaX;
    private int _frameDeltaY;

    private float _timeTillNextFrame;
    private int _xOffset;
    private int _yOffset;

    public TextureCoords GetOffset(){
        return new TextureCoords(_xOffset, _yOffset);
    }

    public ScrollingTracker(float frameLength, int frameDeltaX, int frameDeltaY, int xSize, int ySize){
        _frameLength = frameLength;
        _frameDeltaX = frameDeltaX;
        _frameDeltaY = frameDeltaY;
        _xSize = xSize;
        _ySize = ySize;

        _timeTillNextFrame = frameLength;
        _xOffset = 0;
        _yOffset = 0;
    }

    public void Update(float elapsedTime){
        _timeTillNextFrame -= elapsedTime;

        if(_timeTillNextFrame <= 0) {
            TickFrame();
            _timeTillNextFrame = _frameLength;
        }
    }

    public void TickFrame(){
        _xOffset += _frameDeltaX;
        _yOffset += _frameDeltaY;

        if(_xOffset >= _xSize)
            _xOffset = _xSize - _xOffset;

        if(_yOffset >= _ySize)
            _yOffset = _ySize - _yOffset;
    }
}
