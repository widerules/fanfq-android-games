package com.fanfq.firstgame;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Window;
import android.view.WindowManager;

public class MainActivity extends Activity {
	
	private WelcomeView mWelcomeView;
	
	private Handler mHandler;
	private MenuView mMenuView;
	private GameView mGameView;
	private ClassicModeView mClassicModeView;
	private LoopModeView mLoopModeView;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //����Ϊ����
    	this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    	//ȫ��
        requestWindowFeature(Window.FEATURE_NO_TITLE); 
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN ,  
		              WindowManager.LayoutParams.FLAG_FULLSCREEN);
		
		mHandler = new Handler(){
			@Override
			public void handleMessage(Message msg) {
				super.handleMessage(msg);
				switch(msg.what){
				case Constant.ENTER_MENU:
					mMenuView  = new MenuView(MainActivity.this);
					mMenuView.requestFocus();
					mMenuView.setFocusableInTouchMode(true);
					setContentView(mMenuView);
					break;
				case Constant.CLASSIC_MODE:
					mClassicModeView = new ClassicModeView(MainActivity.this);
					mClassicModeView.requestFocus();
					mClassicModeView.setFocusableInTouchMode(true);
					setContentView(mClassicModeView);
					break;
				case Constant.LOOP_MODE:
					mLoopModeView = new LoopModeView(MainActivity.this);
					mLoopModeView.requestFocus();
					mLoopModeView.setFocusableInTouchMode(true);
					setContentView(mLoopModeView);
					break;
				}
			}
			
		};
		
		mWelcomeView = new WelcomeView(this);
		setContentView(mWelcomeView);
		
		new Thread()
		{
			public void run()
			{
				waitTwoSeconds();
				mHandler.sendEmptyMessage(Constant.ENTER_MENU);
			}
		}.start();
    }
    
    public void waitTwoSeconds()
    {
		try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}  
    }
    
    public void toAnotherView(int flag)
    {
    	switch(flag)
    	{
    	case 0:break;
    	case Constant.ENTER_MENU:mHandler.sendEmptyMessage(Constant.ENTER_MENU);break;
    	case Constant.CLASSIC_MODE:mHandler.sendEmptyMessage(Constant.CLASSIC_MODE);break;
    	case Constant.LOOP_MODE:mHandler.sendEmptyMessage(Constant.LOOP_MODE);break;
    	}
    }
}