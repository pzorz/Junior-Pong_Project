/**
 * Author:	Peter Zorzonello
 * Date:	4/16/15
 * File:	Splash.java
 * 
 * An activity that controls the splash screen. When the app is launched it 
 * is displayed for a limited time then destroyed.
 * The display metrics are determined here so they are ready for when 
 * the screen needs to be drawn. 
 */
package com.zorzonello.pong;


import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;

public class Splash extends ActionBarActivity {
	
	static int barHeight;

	/**
	 * When the splash is created get the height of the status bar
	 * This measurement is used to fix the display size. 
	 * 
	 * Wait for the alloted time and then move to the Main Menu
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);
		
		//get the display metrics so the screen can be drawn
		DisplayMetrics displayMetrics = new DisplayMetrics();
	    getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
	    DisplayAdvisor.setScreenDimensions(displayMetrics);
	    barHeight = getStatusBarHeight();
		
		Thread timer = new Thread() {
			public void run(){
				try {
					sleep(3000);
				} catch (InterruptedException e) {
				} finally {
					Intent openGameActivity = new Intent("com.zorzonello.pong.MAINACTIVITY");
					startActivity(openGameActivity);
				}
			}
		};
		timer.start();
	}
	
	/**
	 * A function that calculates the height of the top status bar
	 * This bar throws off the display size.
	 * 
	 * @return the height of the status bar
	 */
	public int getStatusBarHeight() { 
	      int result = 0;
	      int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
	      if (resourceId > 0) {
	          result = getResources().getDimensionPixelSize(resourceId);
	      } 
	      return result;
	} 
	
	@Override
	protected void onPause() {
		super.onPause();
		finish();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.splash, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
