/**
 * Author:	Peter Zorzonello
 * Date:	4/16/15
 * File:	DisplayAdvisor.java
 * 
 * This class is responsible for the screen size.
 * It gets the max screen size and then creates a 
 * scaling factor based off of a desired size screen.
 */
package com.zorzonello.pong;

import android.util.DisplayMetrics;

public class DisplayAdvisor 
{
	static int maxX;
	static int maxY;
	
	static float scaleY;
	static float scaleX;
	
	final static int IDEAL_HEIGHT = 2560;
	final static int IDEAL_WIDTH = 1600;
	
	
	
	/**
	 * get the max screen dimensions. Using the max screen
	 * dimensions and the ideal dimensions calculate a scale factor
	 * that will be used through out the app.
	 * 
	 * @param displaymetrics the screen size
	 */
	static public void setScreenDimensions(DisplayMetrics displaymetrics){
		maxX = displaymetrics.widthPixels;
		maxY = displaymetrics.heightPixels;
		
		scaleX = (float) maxX/IDEAL_WIDTH;
		scaleY = (float) maxY/IDEAL_HEIGHT;
		scaleY = Math.min(scaleX, scaleY);
		scaleX = scaleY;
	}
	
	/**
	 * Returns the scaling factor
	 * @return the scale
	 */
	public static float getScaleX(){
		return scaleX;	
	}
	
}
