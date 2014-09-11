package com.Messaging;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class ActionQueue {
    private CopyOnWriteArrayList<IAction> _payload;

    public ActionQueue (){
        _payload = new CopyOnWriteArrayList<IAction>();
    }

    public void AddToQueue(IAction action){
        _payload.add(action);
    }

    public void Fire(IMessage m){
        for(IAction action : _payload){
            action.Invoke(m);
        }
    }
}
