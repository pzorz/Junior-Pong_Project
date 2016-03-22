package com.zorzonello.pong;
/**
 * Author:	Peter Zorzonello
 * File:	MainActivity.java
 * 
 * This activity represents the Main Menu. From
 * Here users can press play to begin the game.
 */

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends ActionBarActivity {
	

	Button playButton;
	Button playToTen;
	Button playToTwenty;
	Button playToThirty;
	Button ecColor;
	Button bwColor;
	
	public static String color;
	public static int playScore;

	/**
	 * Set up the buttons on the screen
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		playButton = (Button) findViewById(R.id.buttonPlay);
		playToTen = (Button) findViewById(R.id.button10);
		playToTwenty = (Button) findViewById(R.id.button20);
		playToThirty = (Button) findViewById(R.id.button30);
		ecColor = (Button)findViewById(R.id.ecButton);
		bwColor = (Button)findViewById(R.id.bwButton);
		
		playButton.setVisibility(View.INVISIBLE);
		bwColor.setVisibility(View.INVISIBLE);

		ecColor.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				color = "ec";
				ecColor.setVisibility(View.INVISIBLE);
				bwColor.setVisibility(View.VISIBLE);
				
			}
		});
		
		bwColor.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				color = "bw";
				bwColor.setVisibility(View.INVISIBLE);
				ecColor.setVisibility(View.VISIBLE);
				
			}
		});
		
		/**
		 * When this button is pressed the game begins.
		 */
		playButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent openGameActivity = new Intent("com.zorzonello.pong.GAMEACTIVITY");

				startActivity(openGameActivity);
				
			}
		});
		
		/**
		 * When the user clicks this button it sets the play-to score
		 * to ten. The game won't end until someone hits 10 points.
		 */
		playToTen.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				playScore = 10;
				
				playButton.setVisibility(View.VISIBLE);
				
				playToTen.setVisibility(View.INVISIBLE);
				playToTwenty.setVisibility(View.VISIBLE);
				playToThirty.setVisibility(View.VISIBLE);
			}
			
		});
		
		/**
		 * When the user clicks this button the game plays until 20
		 */
		playToTwenty.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				playScore = 20;
				
				playButton.setVisibility(View.VISIBLE);
				
				playToTwenty.setVisibility(View.INVISIBLE);
				playToThirty.setVisibility(View.VISIBLE);
				playToTen.setVisibility(View.VISIBLE);
			}
			
		});
		
		/**
		 * When the user clicks this button the game plays until 30
		 */
		playToThirty.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				playScore = 30;
				
				playToThirty.setVisibility(View.INVISIBLE);
				playToTwenty.setVisibility(View.VISIBLE);
				playToTen.setVisibility(View.VISIBLE);
				
				playButton.setVisibility(View.VISIBLE);
			}
			
		});
		
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
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
	
//	@Override
//	protected void onPause() {
//		super.onPause();
//		finish();
//	}
}
