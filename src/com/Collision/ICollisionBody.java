package com.Collision;

import com.CollisionPayload.ICollisionPayload;
import com.threed.jpct.SimpleVector;

import java.util.UUID;

public interface ICollisionBody {
    public UUID GetId();

    public ICollisionPayload GetPayload();
    public void SetPayloadCollisionDirection(SimpleVector direction);

    public void SetPosition(SimpleVector position);
    public SimpleVector GetPosition();
    
    public void SetRadius(float radius);
    public float GetRadius();

    public boolean DoesCollide(ICollisionBody subject);
}
