package com.fanfq.surfaceview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.Display;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class MySurfaceView extends SurfaceView implements SurfaceHolder.Callback{

	int dy = Display.DEFAULT_DISPLAY;
	MainActivity ma;
	Paint paint;
	OnDrawThread odt;
	PicRunThread prt;
	private float picX = 0;
	private float picY = 0;
	boolean picAlphaFlag = false;
	int picAlphaNum = 0;
	
	public MySurfaceView(Context context) {
		super(context);
		this.ma = (MainActivity)context;
		this.getHolder().addCallback(this);//注册回调接口
		paint = new Paint();
		System.out.println("MySurfaceView");
	}
	
	public void setPicX(float picX) {
		this.picX = picX;
	}

	public void setPicY(float picY) {
		this.picY = picY;
	}

	public void setPicAlphaFlag(boolean picAlphaFlag) {
		this.picAlphaFlag = picAlphaFlag;
	}

	public void setPicAlphaNum(int picAlphaNum) {
		this.picAlphaNum = picAlphaNum;
	}

	@Override
	protected void onDraw(Canvas canvas) {//绘制方法，此方法用于绘制图形图像等
		super.onDraw(canvas);
		paint.setColor(Color.WHITE);
		canvas.drawRect(0,0,Constant.SCREENWIDTH, Constant.SCREENHEIGHT,paint);
		Bitmap bitmapDuck = BitmapFactory.decodeResource(ma.getResources(), R.drawable.duke);
		canvas.drawBitmap(bitmapDuck, picX,picY, paint);
		if(picAlphaFlag){
			Bitmap bitmapBG = BitmapFactory.decodeResource(ma.getResources(), R.drawable.jpg1);
			paint.setAlpha(picAlphaNum);
			canvas.drawBitmap(bitmapBG, 0,0, paint);
		}
//		System.out.println("onDraw");
	}

	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
		// TODO Auto-generated method stub
		System.out.println("surfaceChanged");
	}

	//此方法在surfaceview创建是调用
	public void surfaceCreated(SurfaceHolder holder){
		
		Constant.ISRUN = true;
		prt = new PicRunThread(this);
		prt.start();
		odt = new OnDrawThread(this);
		odt.start();//启动onDraw的绘制线程
		System.out.println("surfaceCreated");
	}

	public void surfaceDestroyed(SurfaceHolder holder) {
		Constant.ISRUN = false;
		System.out.println("surfaceDestroyed");
	}

}
