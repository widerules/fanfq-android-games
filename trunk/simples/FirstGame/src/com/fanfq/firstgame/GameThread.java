package com.fanfq.firstgame;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

public class GameThread extends Thread {

	GameView gameView;
	SurfaceHolder holder;

	public GameThread(GameView gameView) {
		this.gameView = gameView;
		this.holder = gameView.getHolder();
	}

	public void run() {
		Canvas canvas;
		while (Constant.threadFlag) {
			canvas = null;
			if (true) {
				try {
					canvas = this.holder.lockCanvas();
					synchronized (this.holder) {
						gameView.onDraw(canvas);
					}
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					if (canvas != null) {
						this.holder.unlockCanvasAndPost(canvas);
					}
				}
			}
		}
	}
}
