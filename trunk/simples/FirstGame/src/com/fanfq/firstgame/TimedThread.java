package com.fanfq.firstgame;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

public class TimedThread extends Thread{
	
	TimedModeView mTimedModeView;
	
	public TimedThread(TimedModeView timedModeView){
		this.mTimedModeView = timedModeView;
	}
	
	public void run(){
		while (Constant.TIMED_THREAD_FLAG&&mTimedModeView.time-->1) {
			try{
				sleep(1000);
			}catch(Exception e ){
				e.printStackTrace();
			}
		}
	}

}
