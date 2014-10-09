package com.Messaging;

public class MovePlayerMessage implements IMessage {
	
	
	public float ax;
	public float ay;
	
	public MovePlayerMessage(float X , float Y) {
		
		ax=X;
		ay=Y;
		
		
	}

}
