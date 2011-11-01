package com.fanfq.firstgame;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

public class MenuThread extends Thread{

	MenuView mMenuView;
	SurfaceHolder holder;
	
	public MenuThread(MenuView menuView){
		this.mMenuView = menuView;
		this.holder = menuView.getHolder();
	}
	
	public void run(){
		Canvas canvas;
		while (Constant.threadFlag) {
			canvas = null;
			if(true){
				try{
					canvas = this.holder.lockCanvas();
					synchronized (this.holder) {
						mMenuView.onDraw(canvas);
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
				sleep(100);
			}catch(Exception e ){
				e.printStackTrace();
			}
		}
	}
}
