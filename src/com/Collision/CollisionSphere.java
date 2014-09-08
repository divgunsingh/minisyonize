package com.Collision;
import com.CollisionPayload.ICollisionPayload;
import com.threed.jpct.SimpleVector;

import java.util.UUID;

public class CollisionSphere implements ICollisionBody{
    private float _radius;
    public float GetRadius(){return _radius;}

    private ICollisionPayload _payload;
    public ICollisionPayload GetPayload(){return _payload;}
    public void SetPayloadCollisionDirection(SimpleVector direction){
        _payload.SetDirectionOfCollision(direction);
    }

    private UUID _id;
    public UUID GetId() {return _id;}

    private SimpleVector _position;
    public SimpleVector GetPosition(){return _position;}
    public void SetPosition(SimpleVector position){
        _position = position;
    }

    public CollisionSphere(float radius, ICollisionPayload payload){
        _id = UUID.randomUUID();
        _radius = radius;
        _position = new SimpleVector(0,0,0);
        _payload = payload;
        _payload.SetId(_id);
    }

    public boolean DoesCollide(ICollisionBody body){
        if(body instanceof CollisionSphere){
            CollisionSphere sphereBody = (CollisionSphere) body;
            float distanceBetweenBodies = _position.distance(sphereBody.GetPosition());
            float sumOfRadii = _radius + sphereBody.GetRadius();

            if(sumOfRadii >= distanceBetweenBodies)
                return true;
        }

        return false;
    }
}
