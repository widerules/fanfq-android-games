package com.fanfq.firstgame;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

public class TimedThread extends Thread{
	
	SurfaceHolder holder;
	TimedModeView mTimedModeView;
	
	public TimedThread(TimedModeView timedModeView){
		this.mTimedModeView =timedModeView;
		this.holder = timedModeView.getHolder();
	}
	
	public void run(){
		Canvas canvas;
		while (Constant.TIMED_THREAD_FLAG) {
			canvas = null;
			if(true){
				try{
					canvas = this.holder.lockCanvas();
					synchronized (this.holder) {
						mTimedModeView.time--;
						mTimedModeView.onDraw(canvas);
					}
				}catch(Exception e){
					e.printStackTrace();
				}finally{
					if(canvas !=null){
						this.holder.unlockCanvasAndPost(canvas);
					}
				}
			}
			try{
				sleep(1000);
			}catch(Exception e ){
				e.printStackTrace();
			}
		}
	}

}
