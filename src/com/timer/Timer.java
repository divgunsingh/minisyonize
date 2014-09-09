package com.Timer;

public class Timer {

	float remainingTime;
	
	public Timer(float time){
		remainingTime = time;
	}
	
	public void Reset(float time){
		remainingTime = time;
	}
	
	public boolean isFinished() {
		return remainingTime<=0;
	}
    
	public void update(float elapsedTime){
		remainingTime -= elapsedTime;	
	}			
}


 