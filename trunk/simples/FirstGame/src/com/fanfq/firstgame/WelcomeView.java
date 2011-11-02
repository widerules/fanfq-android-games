package com.fanfq.firstgame;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class WelcomeView extends SurfaceView implements SurfaceHolder.Callback{

	private MainActivity mActivity;
	private Paint paint;
	private int currentAlpha=0;//当前的不透明值
	private Bitmap mBitmap;
	private int sleepSpan=50;//动画的时延ms
	private int currentX;
	private int currentY;
	
	public WelcomeView(MainActivity activity) {
		super(activity);
		this.mActivity = activity;
		this.getHolder().addCallback(this);//设置生命周期回调接口的实现者
		paint = new Paint();//创建画笔
		paint.setAntiAlias(true);//打开抗锯齿
		mBitmap = BitmapFactory.decodeResource(this.mActivity.getResources(), R.drawable.xiaoban_bg);
	}
	
	public void onDraw(Canvas canvas){	
		//绘制黑填充矩形清背景
		paint.setColor(Color.BLACK);//设置画笔颜色
		paint.setAlpha(255);
		canvas.drawRect(0, 0, Constant.SCREEN_WIDTH, Constant.SCREEN_WIDTH, paint);
		
		//进行平面贴图
		if(mBitmap==null)return;
		paint.setAlpha(currentAlpha);		
		canvas.drawBitmap(mBitmap, currentX, currentY, paint);	
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		new Thread(){
			public void run(){
				currentX = Constant.SCREEN_WIDTH/2 - mBitmap.getWidth()/2;
				currentY = Constant.SCREEN_HEIGHT/2 - mBitmap.getHeight()/2;
				
				for(int i=255;i>-10;i=i-10)
				{//动态更改图片的透明度值并不断重绘	
					currentAlpha=i;
					if(currentAlpha<0)
					{
						currentAlpha=0;
					}
					SurfaceHolder myholder=WelcomeView.this.getHolder();
					Canvas canvas = myholder.lockCanvas();//获取画布
					try{
						synchronized(myholder){
							onDraw(canvas);//绘制
						}
					}
					catch(Exception e){
						e.printStackTrace();
					}
					finally{
						if(canvas != null){
							myholder.unlockCanvasAndPost(canvas);
						}
					}
					
					try
					{
						if(i==255)
						{//若是新图片，多等待一会
							Thread.sleep(1000);
						}
						Thread.sleep(sleepSpan);
					}
					catch(Exception e)
					{
						e.printStackTrace();
					}
				}
			}
		}.start();
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		// TODO Auto-generated method stub
		
	}

}
