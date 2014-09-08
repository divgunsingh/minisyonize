package com.CollisionPayload;

import com.threed.jpct.SimpleVector;

import java.util.UUID;

public abstract class BaseCollisionPayload implements ICollisionPayload {
    private UUID _id;
    public UUID GetId(){return _id;}
    public void SetId(UUID value){_id = value;}

    private SimpleVector _directionOfCollision;
    public SimpleVector GetDirectionOfCollision(){
        return _directionOfCollision;
    }
    public void SetDirectionOfCollision(SimpleVector direction){
        _directionOfCollision = direction;
    }
}
