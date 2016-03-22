/**
 * Author:	Peter Zorzonello
 * Date:	4/16/15
 * File:	GameActivity.java
 * 
 * The android Activity that controls the game. 
 * This activity controls the GameSurfaceView class which runs
 * the game. It also calls the activity that displays the score when 
 * the game ends.
 */
package com.zorzonello.pong;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.MotionEvent;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Point;

public class GameActivity extends Activity {
    
	public static String Gamecolor;
	public static String Playscore;
	GameSurfaceView surfaceView;
    Point touched;
	boolean wasTouched;
	boolean isTouching;
	MediaPlayer ping;
	MediaPlayer beep;
	
	/**
	 * Create the surface view and the Point object
	 */
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	
		touched = new Point();
		surfaceView = new GameSurfaceView(this);
		setContentView(surfaceView);
		
		
	    //the sounds the app will use
		ping = MediaPlayer.create(GameActivity.this, R.raw.ping_pong_8bit_beeep);
		beep = MediaPlayer.create(GameActivity.this, R.raw.ping_pong_8bit_plop);
	}

	/**
	 * pause the surfaceView
	 */
	protected void onPause() {
		super.onPause();
		surfaceView.onPause();
		ping.release();
	}

	/**
	 * resume the surfaceView
	 */
	protected void onResume() {
		super.onResume();
		surfaceView.onResume();
		
		
	}
	
	/**
	 * if the screen was touched, determine what kind of touch was is.
	 * if it was a down press then get the x and y coordinates
	 */
	public boolean onTouchEvent(MotionEvent e) {
        int action = e.getAction();
        switch (action & MotionEvent.ACTION_MASK) {
        case MotionEvent.ACTION_DOWN:
            wasTouched = true;
            isTouching = true;
            touched.x = (int) e.getX();
            touched.y = (int) e.getY();
            break;
        case MotionEvent.ACTION_MOVE:
            break;
        case MotionEvent.ACTION_UP:
        	isTouching = false;
            break;
        }

        return true;
    }

    public boolean isTouching(){
    	return isTouching();
    }
    
    /**
     * Get the touched point and set the boolean back to being not touched
     * @return the touched point
     */
    public Point getTouch() {
    	wasTouched = false;
    	return touched;
    }

    /**
     * start the EndScreenActivity and finish this activity
     */
	public void startResultsScreen() {
		Intent openResultsActivity = new Intent("android.zorzonello.pong.ENDSCREENACTIVITY");
		startActivity(openResultsActivity);
		finish();
		
	}

}
