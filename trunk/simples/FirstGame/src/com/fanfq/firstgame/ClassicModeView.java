package com.fanfq.firstgame;

import android.graphics.BitmapFactory;
import android.view.KeyEvent;
import android.view.MotionEvent;

public class ClassicModeView extends GameView{

	
	private int clreanCount = 0;
	private MainActivity mActivity;
	
	public ClassicModeView(MainActivity activity) {
		super(activity);
		this.mActivity = activity;
	}
	
	public boolean classicMode(int rowClicked, int columnClicked){
		boolean result = false;
		
		if(blocks[rowClicked][columnClicked].isFlag()){
			for (int i = 0; i < rows ; i++) {
				for (int j = 0; j < columns ; j++) {
					if(blocks[i][j].isFlag()){
						int jj;
						for (jj = j ; jj > 0; jj--) {
							if(blocks[i][jj-1].getColor()!=-1){
								blocks[i][jj].moveBlock(blocks[i][jj-1].getColor());//moveBlock(this._context, this, new Point(i*Config.BLOCK_STYLE,jj*Config.BLOCK_STYLE),blocks[i][jj-1].getColor());
							}else{
								break;
							}
						}
						blocks[i][jj].deleteBlock();//deleteBlock(this._context, this, new Point(i*Config.BLOCK_STYLE,jj*Config.BLOCK_STYLE));
					}
				}
				for (int i1 = clreanCount; i1 < rows ; i1++) {
					if(blocks[i1][columns-1].getColor()==-1){
						clreanCount ++;
						int ii;int j1=0;
						for (ii = i1 ; ii > 0; ii--) {
							for (j1 = 0; j1 < columns ; j1++) {
								blocks[ii][j1].moveBlock(blocks[ii-1][j1].getColor());//(this._context, this, new Point(ii*Config.BLOCK_STYLE,j1*Config.BLOCK_STYLE),blocks[ii-1][j1].getColor());
							}
						}
						for (int ii1 = 0; ii1 < columns ; ii1++) {
							blocks[0][ii1].deleteBlock();//(this._context, this, new Point(0*Config.BLOCK_STYLE,ii1*Config.BLOCK_STYLE));
						}
					}
				}
			}
			result = true;
			score += ((count-1)*(count-1));
			gain += (count-1);
		}else{
			for (int i = 0; i < rows; i++) {
				for (int j = 0; j < columns; j++) {
					if(blocks[i][j].isFlag()){
						blocks[i][j].closeBlock();//backBlock(this._context, this, new Point(i*Config.BLOCK_STYLE,j*Config.BLOCK_STYLE));
					}
				}
			}
		}
		
		return result;
	}
	
//	@Override
//	public boolean onKeyDown(int keyCode, KeyEvent event) {
//		System.out.println("sss");
//		return super.onKeyDown(keyCode, event);
//	}
	
	

	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		
		switch (event.getAction()) {
		case MotionEvent.ACTION_UP:
			int x = (int) event.getX();
			int y = (int) event.getY();
			
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
			}else{
				x = x/20;
				y = y/20;
				if (!classicMode(x,y)){
					count =0;
					filter(x, y);
				}
			}
		}
		
		return true;
	}

	
}
