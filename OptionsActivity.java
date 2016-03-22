package com.zorzonello.pong;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class OptionsActivity extends ActionBarActivity {

	Button backButton;
	
	Button ECColor;
	Button BWColor;
	
	Button setScore30;
	Button setScore20;
	Button setScore10;
	
	public static String color;
	public static String playScore;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_options);
		
		//set up all the buttons
		backButton = (Button) findViewById(R.id.buttonGoBack);
		
		setScore10 = (Button) findViewById(R.id.buttonSetScore10);
		setScore20 = (Button) findViewById(R.id.buttonSetScore20);
		setScore30 = (Button) findViewById(R.id.buttonSetScore30);
		
		BWColor = (Button) findViewById(R.id.buttonColorBW);
		ECColor = (Button) findViewById(R.id.buttonColorEC);
		
		setScore10.setVisibility(View.INVISIBLE);
		BWColor.setVisibility(View.INVISIBLE);
		
		/**
		 * When the ECColor button is clicked set the string color to "EC"
		 */
		ECColor.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				ECColor.setVisibility(View.INVISIBLE);
				BWColor.setVisibility(View.VISIBLE);
				
				OptionsActivity.color = "EC";
			}
		});
		
		/**
		 * When the BWColor button is clicked set the color to "BW"
		 */
		BWColor.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				BWColor.setVisibility(View.INVISIBLE);
				ECColor.setVisibility(View.VISIBLE);
				
				OptionsActivity.color = "BW";
				
			}
		});
		
		/**
		 * When the setScore20 button is clicked set the string playScore to be "20"
		 */
		setScore20.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				setScore20.setVisibility(View.INVISIBLE);
				setScore10.setVisibility(View.VISIBLE);
				setScore30.setVisibility(View.VISIBLE);
				
				OptionsActivity.playScore = "20";
				
			}
		});
		
		/**
		 * When the setScore10 button is clicked set the string playScore to be "10"
		 */
		setScore10.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				setScore10.setVisibility(View.INVISIBLE);
				setScore20.setVisibility(View.VISIBLE);
				setScore30.setVisibility(View.VISIBLE);
				
				OptionsActivity.playScore = "10";
				
			}
		});
		
		/**
		 * When the setScore30 button is clicked set the string playScore to be "30"
		 */
		setScore30.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				setScore30.setVisibility(View.INVISIBLE);
				setScore20.setVisibility(View.VISIBLE);
				setScore10.setVisibility(View.VISIBLE);
				
				OptionsActivity.playScore = "30";
				
			}
		});
		
		/**
		 * When the back button is clicked, save the options to a .txt file 
		 * and then start the next activity passing the color and playScore to the 
		 * next activity
		 */
		backButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				String root = Environment.getExternalStorageDirectory()
						.getAbsolutePath() + File.separator;
				
			    File myDir = new File(root + "options");    
			    myDir.mkdirs();
			   
			    String fname = "options.txt";
			    File file = new File (myDir, fname);
			    if (file.exists ()) file.delete (); 
			    try {
			    		FileWriter fw = new FileWriter(file.getAbsoluteFile());
			    		fw.write("Elephant");
			    		fw.write("\n");
			    		fw.write(OptionsActivity.color);
			    		fw.write(OptionsActivity.playScore);
			    		fw.flush();
			    		fw.close();

			    } catch (Exception e) {
			           e.printStackTrace();
			    }
				
			    readInOptions();
				
				Intent openMainActivity = new Intent("com.zorzonello.pong.MAINACTIVITY");
				startActivity(openMainActivity);
				
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.options, menu);
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
	
	/**
	 * when the activity is paused kill the activity 
	 */
	@Override
	protected void onPause() {
		super.onPause();
		finish();
	}
	
	public void readInOptions() {
		String fileName = "options.txt";
		String root = Environment.getExternalStorageDirectory()
                .getAbsolutePath() + File.separator;
		 File myDir = new File(root + "options");
		 File file = new File (myDir, fileName);
		 
		 if(file.exists()){
			 try {
				InputStream in = new FileInputStream(root+"options"+File.separator + fileName);
				BufferedReader br = new BufferedReader(new InputStreamReader(in));
				color = br.readLine();
				playScore = br.readLine();
				br.close();
				
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		 }
	}
}
