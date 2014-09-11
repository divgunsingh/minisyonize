package com.States;
import com.threed.jpct.FrameBuffer;
public interface IState {
	public boolean IsReadyToChangeState();
	public IState GetNextState();
	
	public void initialize();
	
	public void update(float elapsetime);
	public void draw(FrameBuffer fb);
}

