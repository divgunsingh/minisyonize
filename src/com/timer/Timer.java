package com.timer;

public class Timer {

	float remainingTime;
	
		private boolean isFinished() {
			return remainingTime<=0; 
		}
    
		public void update(float elapsedTime){
			while(remainingTime!=0) {
				elapsedTime=elapsedTime-1;
			}
		}
    
}

 