package com.fanfq.firstgame;

import android.graphics.BitmapFactory;
import android.view.MotionEvent;

public class LoopModeView extends GameView{

	private MainActivity mActivity;
	public LoopModeView(MainActivity activity) {
		super(activity);
		mActivity = activity;
	}
	
	public boolean loopMode(int rowClicked, int columnClicked){
		boolean result = false;
		if(blocks[rowClicked][columnClicked].isFlag()){
			for (int i = 0; i < rows ; i++) {
				for (int j = 0; j < columns ; j++) {
					if(blocks[i][j].isFlag()){
						blocks[i][j].deleteBlock();//deleteBlock(this._context, this, new Point(i*Config.BLOCK_STYLE,j*Config.BLOCK_STYLE));
						int ii;
						for (ii = i ; ii > 0; ii--) {
							blocks[ii][j].moveBlock(blocks[ii-1][j].getColor());//moveBlock(this._context, this, new Point(ii*Config.BLOCK_STYLE,j*Config.BLOCK_STYLE),blocks[ii-1][j].getColor());
						}
						blocks[ii][j] = new Block(mActivity); //createBlock(this._context, this, new Point(ii*Config.BLOCK_STYLE,j*Config.BLOCK_STYLE));
					}
				}
			}
			result = true;
			score += ((count-1)*(count-1));
			gain += (count-1);
		}
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				if(blocks[i][j].isFlag()){
					blocks[i][j].closeBlock();//backBlock(this._context, this, new Point(i*Config.BLOCK_STYLE,j*Config.BLOCK_STYLE));
				}
			}
		}
		return result;
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		int x = (int) event.getX();
		int y = (int) event.getY();
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			if(x>10&&x<10+bmHome.getWidth()&&y>10&&y<bmHome.getHeight()+10){
				bmHome = BitmapFactory.decodeResource(this.getResources(), R.drawable.home_,options);
				this.mActivity.toAnotherView(Constant.WELCOME_VIEW);
				break;
			}else if(x>150&&x<bmSet.getWidth()+150&&y>10&&y<bmSet.getHeight()+10){
				bmSet = BitmapFactory.decodeResource(this.getResources(), R.drawable.set_,options);;
				this.mActivity.toAnotherView(Constant.SET_VIEW);
				break;
			}else if(x>270&&x<bmReplay.getWidth()+270&&y>10&&y<bmReplay.getHeight()+10){
				bmReplay = BitmapFactory.decodeResource(this.getResources(), R.drawable.replay_,options);;
				this.mActivity.toAnotherView(Constant.REPLAY_VIEW);
				break;
			}
			break;
		case MotionEvent.ACTION_UP:
			x = x/20;
			y = y/20;
			if (!loopMode(x,y)){
				count =0;
				filter(x, y);
			}
		}
		
		return true;
	}
	
}
