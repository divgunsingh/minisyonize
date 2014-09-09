package com.Collision;

import android.util.SparseArray;

import com.CollisionPayload.ICollisionPayload;
import com.Messaging.CollisionMessage;
import com.Messaging.Messager;
import com.threed.jpct.SimpleVector;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class CollisionManager {
    private static CollisionManager _instance = null;
    public static CollisionManager GetInstance(){
        if(_instance == null)
            _instance = new CollisionManager();

        return _instance;
    }

    ConcurrentHashMap<UUID, ICollisionBody> _bodies;

    private CollisionManager(){
        _bodies = new ConcurrentHashMap<UUID, ICollisionBody>();
    }

    public SphereCollisionToken AddCollisionSphere(float radius, ICollisionPayload payload){
            ICollisionBody fab = new CollisionSphere(radius, payload);
            _bodies.put(fab.GetId(), fab);

            return new SphereCollisionToken(fab);
    }

    public void Update(float elapsedTime){
        // COLLISION CHECKING TIME
        // we are comparing continuums to each other,
        // therefore we do not need to compare the last one to anything
        // (hence the size() - 1)
        for(int i = 0; i < _bodies.size() - 1; ++i){
            // we get our subject
            ICollisionBody subject = _bodies.get(_bodies.keySet().toArray()[i]);
            if(subject != null) {
                // all previous i indices have already been compared to it,
                // so we can proceed to only the indices past i.
                // we do not need to check collision on the last object.
                for (int j = i + 1; j < _bodies.size(); j++)
                    // now we run our collision checks
                    if (subject.DoesCollide(_bodies.get(_bodies.keySet().toArray()[j])))
                        HandleCollision(_bodies.get(_bodies.keySet().toArray()[i]),
                                _bodies.get(_bodies.keySet().toArray()[j]));
            }
        }
    }

    private void HandleCollision(ICollisionBody partyA, ICollisionBody partyB){
        // we get the directions of the collisions
        SimpleVector partyACollisionDirection = partyB.GetPosition().calcSub(partyA.GetPosition());
        SimpleVector partyBCollisionDirection = partyA.GetPosition().calcSub(partyB.GetPosition());

        // normalize them
        partyACollisionDirection.scalarMul(1 / partyACollisionDirection.length());
        partyBCollisionDirection.scalarMul(1 / partyBCollisionDirection.length());

        // and then inject them into the payloads
        partyA.SetPayloadCollisionDirection(partyACollisionDirection);
        partyB.SetPayloadCollisionDirection(partyBCollisionDirection);

        Messager.GetInstance().Publish(new CollisionMessage(partyA.GetPayload(), partyB.GetPayload()));
    }

    public void SetBodyPosition(SimpleVector position, ICollisionToken token){
        if(_bodies.containsKey(token.GetId()))
            _bodies.get(token.GetId()).SetPosition(position);
    }

    public void DeleteCollisionBody(ICollisionToken token){
        if(_bodies.containsKey(token.GetId()))
            _bodies.remove(token.GetId());
    }
}
