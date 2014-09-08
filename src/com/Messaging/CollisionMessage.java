package com.Messaging;

import com.Collision.ICollisionToken;
import com.CollisionPayload.ICollisionPayload;

import java.util.UUID;

public class CollisionMessage implements IMessage {
    public ICollisionPayload PartyA;
    public ICollisionPayload PartyB;

    public CollisionMessage(ICollisionPayload partyA, ICollisionPayload partyB){
        PartyA = partyA;
        PartyB = partyB;
    }

    public ICollisionPayload GetRelevantPayload(ICollisionToken token){
        if(PartyA.GetId() != token.GetId()){
            if(PartyB.GetId() == token.GetId())
                return PartyA;
        } else { // partyA == myId
            if(PartyB.GetId() != token.GetId())
                return PartyB;
        }

        return null;
    }
}
