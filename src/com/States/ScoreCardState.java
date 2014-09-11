package com.States;

import com.threed.jpct.FrameBuffer;

public class ScoreCardState implements IState{

	private IState playState;
	public IState GetNextState(){return playState;}
	
	private boolean isReadyToChangeState;
	public boolean IsReadyToChangeState(){return isReadyToChangeState;}
	
	public void initialize() {
		isReadyToChangeState = false;
	}

	public void update(float elapsetime) {
		
	}

	public void draw(FrameBuffer fb) {
		
	}

	
}
