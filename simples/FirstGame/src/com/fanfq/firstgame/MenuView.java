package com.fanfq.firstgame;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class MenuView extends SurfaceView implements SurfaceHolder.Callback{

	private MainActivity mActivity;
	
	private Bitmap bmBackground;
	private Bitmap bmClassicMode;
	private Bitmap bmLoopMode;
	private Bitmap bmTimedMode;
	private Bitmap bmHelp;
	private Bitmap bmExit;
	
	private Paint mPaint;
	
	private MenuThread mMenuThread;
	
	public MenuView(MainActivity activity) {
		super(activity);
		this.mActivity = activity;
		this.getHolder().addCallback(this);
		
		bmBackground = BitmapFactory.decodeResource(this.getResources(), R.drawable.welcome_bg);
		bmClassicMode = BitmapFactory.decodeResource(this.getResources(), R.drawable.classic);
		bmLoopMode = BitmapFactory.decodeResource(this.getResources(), R.drawable.loop);
		bmTimedMode = BitmapFactory.decodeResource(this.getResources(), R.drawable.timed);
		
		mPaint = new Paint();
		mPaint.setAntiAlias(true);
		mMenuThread = new MenuThread(this);
	}
	
	
	@Override
	public void onDraw(Canvas canvas){
		canvas.drawBitmap(bmBackground, 0, 0,mPaint);
		canvas.drawBitmap(bmClassicMode, 60, 150,mPaint);
		canvas.drawBitmap(bmLoopMode, 60, 250,mPaint);
		canvas.drawBitmap(bmTimedMode, 60, 350,mPaint);
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent e) {
		switch (e.getAction()) {
		case MotionEvent.ACTION_DOWN:
			int x = (int) e.getX();
			int y = (int) e.getY();

			if (x > 60 && x < 60 + bmClassicMode.getWidth() && y > 150
					&& y < 150 + bmClassicMode.getHeight()) {
				Constant.GAME_MODE = Constant.CLASSIC_MODE;
			} else if (x > 60 && x < 60 + bmLoopMode.getWidth() && y > 250
					&& y < 250 + bmLoopMode.getHeight()) {
				Constant.GAME_MODE = Constant.LOOP_MODE;
			} else if (x > 60 && x < 60 + bmTimedMode.getWidth() && y > 350
					&& y < 350 + bmTimedMode.getHeight()) {
				Constant.GAME_MODE = Constant.TIMED_MODE;
			}
			this.mActivity.toAnotherView(Constant.GAME_MODE);
			// switch(status)
			// {
			// case
			// 0:choserX0=0;choserX1=-20;choserX2=-30;choserX3=-40;choserX4=-50;keyStatus=status;
			// activity.toAnotherView(START_GAME); break;
			// case
			// 1:choserX0=-40;choserX1=0;choserX2=-30;choserX3=-40;choserX4=-50;keyStatus=status;
			// activity.toAnotherView(ENTER_SETTING);break;
			// case
			// 2:choserX0=-40;choserX1=-20;choserX2=0;choserX3=-40;choserX4=-50;keyStatus=status;
			// surfaceId=0;activity.toAnotherView(ENTER_HELP);
			// break;
			// case
			// 3:choserX0=-40;choserX1=-20;choserX2=-30;choserX3=0;choserX4=-50;keyStatus=status;
			// surfaceId=1;activity.toAnotherView(ENTER_HELP);break;
			// case
			// 4:choserX0=-40;choserX1=-20;choserX2=-30;choserX3=-40;choserX4=0;keyStatus=status;
			// System.exit(0);break;
			// }
			break;
		case MotionEvent.ACTION_UP:

			break;
		}
System.out.println(Constant.GAME_MODE);
		return true;
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		Constant.MENU_THREAD_FLAG=true;
		mMenuThread.start();			
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		Constant.MENU_THREAD_FLAG=false;
	}

}
