package com.Sprite;

public class AnimationTracker {
    private float _frameLength;
    private float _timeTillNextFrame;

    private int _currentFrame;
    private int _currentAnimation;

    // each entry defines an animation's number of frames (from start to bottom of the atlas)
    private int[] _framesInAnimation;

    public AnimationTracker(float frameLength, int[] framesInAnimation){
        _frameLength = frameLength;
        _timeTillNextFrame = frameLength;
        _framesInAnimation = framesInAnimation;
    }

    public int GetCurrentFrame(){
        return _currentFrame;
    }

    public int GetCurrentAnimation(){
        return _currentAnimation;
    }

    public void SwitchAnimation(int targetIndex){
        if(targetIndex != _currentAnimation && targetIndex < _framesInAnimation.length) {
            _currentAnimation = targetIndex;
            _currentFrame = 0;
        }
    }

    public void Update(float elapsedTime){
        _timeTillNextFrame -= elapsedTime;

        if(_timeTillNextFrame <= 0){
            TickFrame();
            _timeTillNextFrame = _frameLength;
        }
    }

    private void TickFrame(){
        _currentFrame++;
        if(_currentFrame >= _framesInAnimation[_currentAnimation])
            _currentFrame = 0;
    }
}
