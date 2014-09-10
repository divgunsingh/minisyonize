package com.States;
import com.threed.jpct.FrameBuffer;
public interface IState {

	public void Init();
		
		
	
	public void update(float elapsetime);
		
		
	

	public void draw(FrameBuffer fb);
		
	
}

