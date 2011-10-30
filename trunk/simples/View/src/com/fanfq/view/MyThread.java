package com.fanfq.view;

public class MyThread extends Thread{

	int sleepSpan = 30;
	MyView myView;
	public MyThread(MyView myView){
		this.myView = myView;
	}
	
	public void run(){
		while(true){
			myView.angle =  myView.angle+myView.angleChange;
			if(myView.angle>MyView.ANGLE_MAX){
				myView.angleChange = -3;
			}else if(myView.angle<0){
				myView.angleChange =+3;
			}
			
			switch (myView.direction) {
			case MyView.RIGHT:
				myView.centerX = myView.centerX+myView.SPEED;
				break;

			case MyView.UP:
				myView.centerY = myView.centerY-myView.SPEED;
				break;
			case MyView.LEFT:
				myView.centerX = myView.centerX-myView.SPEED;
				break;
			case MyView.DOWN:
				myView.centerY = myView.centerY+myView.SPEED;
				break;
			}
			
			if(myView.centerY+myView.radius>MyView.SCREEN_HEIGHT){//出了下边界
				myView.centerY=myView.centerY-MyView.SPEED;
				myView.direction=MyView.LEFT;
			}
			if(myView.centerY-myView.radius<0){//出了上边界
				myView.centerY=myView.centerY+MyView.SPEED;
				myView.direction=MyView.RIGHT;
			}
			if(myView.centerX-myView.radius<0){//出了左边界
				myView.centerX=myView.radius;
				myView.direction=MyView.UP;
			}
			if(myView.centerX+myView.radius>MyView.SCREEN_WIDTH){//出了右边界
				myView.centerX=myView.centerX-MyView.SPEED;
				myView.direction=MyView.DOWN;
			}
			
			myView.postInvalidate();
			try{
				Thread.sleep(sleepSpan);
			}catch(Exception e){
				
			}
		}
	}
}
