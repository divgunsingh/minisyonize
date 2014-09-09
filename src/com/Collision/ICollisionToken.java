package com.Collision;

import java.util.UUID;

import com.threed.jpct.SimpleVector;

public interface ICollisionToken {
    UUID GetId();
    void Delete();
    
    
    public void SetPosition(SimpleVector position);
}
