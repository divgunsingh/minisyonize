package com.Timer;

public class Timer {

	float remainingTime;
	
	private boolean isFinished() {
		return remainingTime<=0;
	}
    
	public void update(float elapsedTime){
		remainingTime -= elapsedTime;	
	}			
}


 