/**
 * Author:	Peter Zorzonello
 * Date:	4/16/15
 * File:	EndScreenActivity.java
 * 
 * When this activity is called it is because the score for the player
 * or the score for the ai has reached the play-to score.
 */
package com.zorzonello.pong;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class EndScreenActivity extends ActionBarActivity {
	
	/**
	 * when the app is paused end this activity
	 */
	@Override
	protected void onPause() {
		super.onPause();
		finish();
	}

	TextView score;
	Button back;

	/**
	 * when the app is created set up the button and the score
	 * make the activity display the score. If the button is clicked,
	 * return to the main menu.
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_end_screen);
		
		score = (TextView) findViewById(R.id.textViewScore);
		back = (Button) findViewById(R.id.buttonGoBack);
		
		if(GameSurfaceView.aiScore > GameSurfaceView.playerScore){
			score.setText("You Lost \n" + GameSurfaceView.aiScore + " to " + GameSurfaceView.playerScore);
		} else{
			score.setText("You Win \n" + GameSurfaceView.playerScore + " to " + GameSurfaceView.aiScore);
		}
		
		
		back.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent openMainActivity = new Intent("com.zorzonello.pong.MAINACTIVITY");
				startActivity(openMainActivity);
				
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.end_screen, menu);
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
