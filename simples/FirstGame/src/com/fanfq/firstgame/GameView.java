package com.fanfq.firstgame;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class GameView  extends SurfaceView implements SurfaceHolder.Callback{
	
	public int rows = Constant.SCREEN_WIDTH/20;
	public int columns = Constant.SCREEN_HEIGHT/20;
	Block[][] blocks = new Block[rows][columns];
	private Bitmap bmBackground;
	public Bitmap bmSet;
	public Bitmap bmReplay;
	public Bitmap bmHome;
	
	private Paint mPaint;
	private Thread gameThread;
	private MainActivity mMainActivity;
	public int score = 0;
	public int gain = 0;
	public int time = 120;
	
	BitmapFactory.Options options = new BitmapFactory.Options();

	public GameView(MainActivity activity) {
		super(activity);
		getHolder().addCallback(this);
		mMainActivity = activity;
		for(int i = 0;i<rows;i++){
			for(int j = 0;j<columns;j++){
				blocks[i][j] = new Block(activity);
			}
		}
		bmBackground = BitmapFactory.decodeResource(this.getResources(), R.drawable.block_bg);
		
		options.inSampleSize = 4;
		options.inPurgeable = true;
		bmSet = BitmapFactory.decodeResource(this.getResources(), R.drawable.set,options);
		bmReplay = BitmapFactory.decodeResource(this.getResources(), R.drawable.replay,options);
		bmHome = BitmapFactory.decodeResource(this.getResources(), R.drawable.home,options);
		
		mPaint=new Paint();
		mPaint.setAntiAlias(true);
		
		gameThread = new GameThread(this);
	}
	
	@Override
	public void onDraw(Canvas canvas){
		canvas.drawBitmap(bmBackground,0, 0, mPaint);
		
		for(int i = 0;i<rows;i++){
			for(int j = 0;j<columns;j++){
				if(blocks[i][j].getBitmap()!=null){
					canvas.drawBitmap(blocks[i][j].getBitmap(),i*20, j*20, mPaint);
				}
			}
		}
		
		
		
		mPaint.setColor(Color.WHITE);
		mPaint.setFakeBoldText(true);
		mPaint.setTextSize(15);
		canvas.drawText("Score:"+score, 10, 470, mPaint);
		canvas.drawText("Gain:"+gain, 240, 470, mPaint);
		if(Constant.GAME_MODE == Constant.TIMED_MODE){
			canvas.drawText("Time:"+time, 120, 470, mPaint);
		}
//		mPaint.setAlpha(100);
		canvas.drawBitmap(bmHome,10, 10, mPaint);
		canvas.drawBitmap(bmSet,140, 10, mPaint);
		canvas.drawBitmap(bmReplay,270, 10, mPaint);
	}
	
	public int count;
	
	public void filter(int rowClicked, int columnClicked){
		if(blocks[rowClicked][columnClicked].isFlag()||blocks[rowClicked][columnClicked].getBitmap()==null){
			return;
		}
		count++;

		if (count > 1) {
			blocks[rowClicked][columnClicked].openBlock();//.openBlock(this._context, this, new Point(rowClicked*Config.BLOCK_STYLE,columnClicked*Config.BLOCK_STYLE));
//			System.out.println(rowClicked + "," + columnClicked);
		}

		if ((rowClicked - 1 >= 0)
				&& (columnClicked >= 0)
				&& (blocks[rowClicked - 1][columnClicked].getColor() == blocks[rowClicked][columnClicked]
						.getColor())) {
			filter(rowClicked - 1, columnClicked);
		}
		if ((rowClicked >= 0)
				&& (columnClicked - 1 >= 0)
				&& (blocks[rowClicked][columnClicked - 1].getColor() == blocks[rowClicked][columnClicked]
						.getColor())) {
			filter(rowClicked, columnClicked - 1);
		}
		if ((rowClicked + 1 < rows)
				&& (columnClicked <= columns)
				&& (blocks[rowClicked + 1][columnClicked].getColor() == blocks[rowClicked][columnClicked]
						.getColor())) {
			filter(rowClicked + 1, columnClicked);
		}
		if ((rowClicked <= rows)
				&& (columnClicked + 1 < columns)
				&& (blocks[rowClicked][columnClicked + 1].getColor() == blocks[rowClicked][columnClicked]
						.getColor())) {
			filter(rowClicked, columnClicked + 1);
		}
	}
	
//	@Override
//	public boolean onKeyDown(int keyCode, KeyEvent event) {
//		System.out.println("onKeyUp "+keyCode);
//		if(KeyEvent.KEYCODE_BACK == keyCode){
//			this.mMainActivity.toAnotherView(Constant.ENTER_MENU);
//		}
//		
//		return true;
//	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
		Canvas canvas = holder.lockCanvas();
		try {
			synchronized (holder) {
				onDraw(canvas);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (canvas != null) {
				holder.unlockCanvasAndPost(canvas);
			}
		}
	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		Constant.GAME_THREAD_FLAG = true;
		gameThread.start();
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		Constant.GAME_THREAD_FLAG=false;
	}

}
