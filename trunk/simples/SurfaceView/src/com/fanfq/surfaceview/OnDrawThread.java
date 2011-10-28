package com.fanfq.surfaceview;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

public class OnDrawThread extends Thread{
	
	MySurfaceView msv;;
	SurfaceHolder sh;
	public OnDrawThread(MySurfaceView msv){
		super();
		this.msv = msv;
		sh = msv.getHolder();
	}
	
	@Override
	public void run() {
		Canvas canvas = null;
		while(Constant.ISRUN){
			System.out.println("2222222222");
			try{
				canvas = sh.lockCanvas();
				//将canvas的引用指向surfaceview的canvas对象
				//绘制过程，可能带来同步方面的问题，加锁
				synchronized (this.sh) {
					if(canvas!=null){
						msv.onDraw(canvas);
					}
				}
			}finally{
				try{
					if(sh!=null){
						sh.unlockCanvasAndPost(canvas);//绘制完成后解锁
					}
				}catch(Exception e){
					e.printStackTrace();
				}
			}
			try{
				Thread.sleep(Constant.ONDRAWSPEED);
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}
	
	
}
