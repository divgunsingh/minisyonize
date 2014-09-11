package com.Messaging;

import android.util.Pair;

import com.threed.jpct.Logger;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Messager {
	private static Messager _instance = null;
	public static Messager GetInstance(){
		if(_instance == null)
			_instance = new Messager();
		
		return _instance;
	}
	
    ConcurrentHashMap<Type, ActionQueue> queueMap;

    private Messager(){
        queueMap = new ConcurrentHashMap<Type, ActionQueue>();
    }

    public void Publish(IMessage data){
        if(queueMap.containsKey(data.getClass()))
            queueMap.get(data.getClass()).Fire(data);
        else {
            Logger.log("message type " + data.getClass().getCanonicalName() + " not found!");
            for(Type key: queueMap.keySet())
                Logger.log("type " + key.getClass().getCanonicalName() + " found");
        }
    }

    public void Subscribe(Type type, IAction action) {
        if (queueMap.containsKey(type))
            queueMap.get(type).AddToQueue(action);
        else{
            queueMap.put(type, new ActionQueue());
            ActionQueue target = queueMap.get(type);
            target.AddToQueue(action);
        }
    }
}
