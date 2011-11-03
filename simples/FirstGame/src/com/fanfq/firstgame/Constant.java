package com.fanfq.firstgame;

public class Constant {
	
	public static final int SCREEN_WIDTH =320;//屏幕宽度
	public static final int SCREEN_HEIGHT = 480;//屏幕高度
	
	public static final int WELCOME_VIEW = 1;
	public static final int START_GAME = 2;
	
	
	public static int GAME_MODE=-1;//0:classis,1:loop,2:timed
	public static final int CLASSIC_MODE = 10;
	public static final int LOOP_MODE = 11;
	public static final int TIMED_MODE =12;
	public static final int SET_VIEW = 13;
	
	public static final int REPLAY_VIEW = 14;
	
	
	
	public static boolean MENU_THREAD_FLAG = true;
	public static boolean GAME_THREAD_FLAG=true;//控制ChoserThred线程
	public static boolean TIMED_THREAD_FLAG = true;
}
