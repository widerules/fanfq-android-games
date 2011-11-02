package com.fanfq.firstgame;

import android.view.MotionEvent;

public class LoopModeView extends GameView{

	private MainActivity mMainActivity;
	public LoopModeView(MainActivity activity) {
		super(activity);
		mMainActivity = activity;
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
						blocks[ii][j] = new Block(mMainActivity); //createBlock(this._context, this, new Point(ii*Config.BLOCK_STYLE,j*Config.BLOCK_STYLE));
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
		
		switch (event.getAction()) {
		case MotionEvent.ACTION_UP:
			int x = (int) event.getX()/20;
			int y = (int) event.getY()/20;
			if (!loopMode(x,y)){
				count =0;
				filter(x, y);
			}
		}
		
		return true;
	}
	
}
