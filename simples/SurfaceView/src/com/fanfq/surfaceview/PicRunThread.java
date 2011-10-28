package com.fanfq.surfaceview;

public class PicRunThread extends Thread{

	MySurfaceView msv;
	private float picX ;
	private float picY ;
	boolean yRunFlag = false;
	int picAlphaNum ;
	
	public PicRunThread(MySurfaceView msv){
		super();
		this.msv = msv;
		picX = 0;
		picY = Constant.SCREENHEIGHT-Constant.PICHEIGHT;
		yRunFlag = false;
		picAlphaNum = 0;
	}

	@Override
	public void run() {
		while(Constant.ISRUN){
			System.out.println("1111111");
			while(this.picX<Constant.SCREENWIDTH){
				msv.setPicX(picX);
				msv.setPicY(picY);
				picX=picX+Constant.PICXSPEED;
				if(yRunFlag){
					picY=picY-Constant.PICYSPEED;//向上运动，自减
				}else{
					picY=picY+Constant.PICYSPEED;//向下运动，自加
				}
				if(picY<=0){
					yRunFlag=false;
				}else if(picY >Constant.SCREENHEIGHT-Constant.PICHEIGHT){
					yRunFlag=true;
				}
				
				try{
					Thread.sleep(Constant.PICRUNSPEED);
				}catch(Exception e){
					e.printStackTrace();
				}
			}
			msv.picAlphaFlag=true;
			for(picAlphaNum=0;picAlphaNum<=255;picAlphaNum++){
				if(picAlphaNum==255){
					msv.picAlphaFlag=false;
					picX=0;
					picY=Constant.SCREENHEIGHT-Constant.PICHEIGHT;
					System.out.println(msv.picAlphaFlag+"picX:"+picX+"picY:"+picY);
				}
				msv.setPicAlphaNum(picAlphaNum);
				try{
					Thread.sleep(Constant.PICALPHASPEED);
				}catch(Exception e){
					e.printStackTrace();
				}
			}
		}
	}
	
	
}
