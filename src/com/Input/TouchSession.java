package com.Input;

import com.threed.jpct.SimpleVector;

public class TouchSession {
    public float InitialX;
    public float InitialY;

    public float LatestStepX;
    public float latestStepY;

    float squiggleZone;

    public TouchSession(float x, float y){
        InitialX = x;
        InitialY = y;

        LatestStepX = InitialX;
        latestStepY = InitialY;
    }

    public SimpleVector Step(float x, float y){
        SimpleVector result =  new SimpleVector(x - LatestStepX, y - latestStepY, 0);

        LatestStepX = x;
        latestStepY = y;

        return result;
    }

    public SimpleVector GetOverallDelta(){
        return new SimpleVector(LatestStepX - InitialX, latestStepY - InitialY, 0);
    }

    public SimpleVector GetOverallDirection(){
        SimpleVector delta = GetOverallDelta();
        float magnitude = delta.length();
        delta.scalarMul(1 / magnitude);

        return delta;
    }

    public CardinalDirection GetCardinalDirection(){
        SimpleVector result = GetOverallDelta();

        // first check for squiggle deadzone
        if(Math.abs(result.x - result.y) < squiggleZone)
            return CardinalDirection.SQUIGGLE;

        // then we check what kind of swipe
        if(Math.abs(result.x) > Math.abs(result.y))
            // more x than y
            if(result.x > 0)
                return CardinalDirection.RIGHT;
            else
                return CardinalDirection.LEFT;
        else
            // more y than x
            if(result.y > 0)
                return CardinalDirection.UP;
            else
                return CardinalDirection.DOWN;
    }
}
