package com.Messaging;

import com.Input.TouchSession;

public class TouchFinishedMessage implements IMessage {
    public TouchSession SessionData;

    public TouchFinishedMessage(TouchSession session){
        SessionData = session;
    }
}
