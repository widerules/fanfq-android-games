package com.fanfq.firstgame;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class Block {
	
	private int style;
	private boolean isFlag;
	private int color;
	private Bitmap mBitmap;
	private MainActivity mMainActivity;
	
	public Bitmap getBitmap(){
		return mBitmap;
	}
	public int getStyle() {
		return style;
	}
	public void setStyle(int style) {
		this.style = style;
	}
	public boolean isFlag() {
		return isFlag;
	}
	public void setFlag(boolean isFlag) {
		this.isFlag = isFlag;
	}
	public int getColor() {
		return color;
	}
	public void setColor(int color) {
		this.color = color;
	}

	BitmapFactory.Options options = new BitmapFactory.Options();
	
	
	public Block(MainActivity activity){
		mMainActivity = activity;
		options.inSampleSize = 2;
		options.inPurgeable = true;
		
		this.setFlag(false);
		switch ((int)(Math.random()*4)) {
		case 0:
			this.mBitmap = BitmapFactory.decodeResource(mMainActivity.getResources(),  R.drawable.o11,options);
			this.color = 0;
			break;
		case 1:
			this.mBitmap = BitmapFactory.decodeResource(mMainActivity.getResources(),  R.drawable.g11,options);
			this.color = 1;
			break;
		case 2:
			this.mBitmap = BitmapFactory.decodeResource(mMainActivity.getResources(),  R.drawable.r11,options);
			this.color = 2;
			break;
		case 3:
			this.mBitmap = BitmapFactory.decodeResource(mMainActivity.getResources(),  R.drawable.y11,options);
			this.color = 3;
			break;
		}
	}
	
	public void openBlock(){
		if(!this.isFlag()){
			options.inSampleSize = 2;
			options.inPurgeable = true;
			this.setFlag(true);
			switch (this.getColor()) {
			case 0:
				this.mBitmap = BitmapFactory.decodeResource(mMainActivity.getResources(),  R.drawable.o22,options);
				break;
			case 1:
				this.mBitmap = BitmapFactory.decodeResource(mMainActivity.getResources(),  R.drawable.g22,options);
				break;
			case 2:
				this.mBitmap = BitmapFactory.decodeResource(mMainActivity.getResources(),  R.drawable.r22,options);
				break;
			case 3:
				this.mBitmap = BitmapFactory.decodeResource(mMainActivity.getResources(),  R.drawable.y22,options);
				break;
			}
		}
	}
	
	public void closeBlock(){
		if(this.isFlag()){
			options.inSampleSize = 2;
			options.inPurgeable = true;
			this.setFlag(false);
			switch (this.getColor()) {
			case 0:
				this.mBitmap = BitmapFactory.decodeResource(mMainActivity.getResources(),  R.drawable.o11,options);
				break;
			case 1:
				this.mBitmap = BitmapFactory.decodeResource(mMainActivity.getResources(),  R.drawable.g11,options);
				break;
			case 2:
				this.mBitmap = BitmapFactory.decodeResource(mMainActivity.getResources(),  R.drawable.r11,options);
				break;
			case 3:
				this.mBitmap = BitmapFactory.decodeResource(mMainActivity.getResources(),  R.drawable.y11,options);
				break;
			}
		}
	}
	
	public void moveBlock(int color){
		options.inSampleSize = 2;
		options.inPurgeable = true;
		this.setFlag(false);
		switch (color) {
		case 0:
			this.mBitmap = BitmapFactory.decodeResource(mMainActivity.getResources(),  R.drawable.o11,options);
			this.color = 0;
			break;
		case 1:
			this.mBitmap = BitmapFactory.decodeResource(mMainActivity.getResources(),  R.drawable.g11,options);
			this.color = 1;
			break;
		case 2:
			this.mBitmap = BitmapFactory.decodeResource(mMainActivity.getResources(),  R.drawable.r11,options);
			this.color = 2;
			break;
		case 3:
			this.mBitmap = BitmapFactory.decodeResource(mMainActivity.getResources(),  R.drawable.y11,options);
			this.color = 3;
			break;
		}
	}
	
	public void deleteBlock(){
		this.mBitmap = null;
		this.color = -1;
	}

}
