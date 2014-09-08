package com.Collision;

import com.threed.jpct.SimpleVector;

import java.util.UUID;

public class SphereCollisionToken implements ICollisionToken{
    private UUID _id;
    public UUID GetId(){return _id;}

    public SphereCollisionToken(ICollisionBody fab){
        _id = fab.GetId();
    }

    public void SetPosition(SimpleVector position){
        CollisionManager.GetInstance().SetBodyPosition(position, this);
    }

    public void Delete(){
        CollisionManager.GetInstance().DeleteCollisionBody(this);
    }
}
