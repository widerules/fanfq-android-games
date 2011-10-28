package com.fanfq.glsurfaceview;

import android.app.Activity;
import android.os.Bundle;
import android.widget.LinearLayout;

public class MainActivity extends Activity {
	
	private MySurfaceView mSurfaceView;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        mSurfaceView = new MySurfaceView(this);
        mSurfaceView.requestFocus();
        mSurfaceView.setFocusable(true);
        LinearLayout ll = (LinearLayout)this.findViewById(R.id.main_liner);
        ll.addView(mSurfaceView);
    }

	@Override
	protected void onPause() {
		super.onPause();
		mSurfaceView.onPause();
	}

	@Override
	protected void onResume() {
		super.onResume();
		mSurfaceView.onResume();
	}
    
    
}