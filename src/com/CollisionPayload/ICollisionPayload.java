package com.CollisionPayload;

import com.threed.jpct.SimpleVector;

import java.util.UUID;

public interface ICollisionPayload {
    UUID GetId();
    void SetId(UUID value);

    SimpleVector GetDirectionOfCollision();
    void SetDirectionOfCollision(SimpleVector direction);
}
