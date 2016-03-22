/**
 * Author:	Peter Zorzonello
 * File:	UserPaddle.java
 * Date:	4/20/15
 * 
 * This class represents the user's paddle.
 * It draws the paddle and updates it based on touch
 */
package com.zorzonello.pong;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;

public class UserPaddle {
	
	int curX, curY;
	
	//ideal values for the game 
	final int IDEAL_HEIGHT = 300;
	final int IDEAL_WIDTH = 30;
	final int IDEAL_SPEED = 13;
	
	//scaled values for the game
	int width = (int)(IDEAL_WIDTH*DisplayAdvisor.getScaleX());
	int height = (int)(IDEAL_HEIGHT*DisplayAdvisor.getScaleX());
	int speed = (int)(IDEAL_SPEED*DisplayAdvisor.getScaleX());
	
	Paint paint;
	
	/**
	 * Creates the User Paddle. Sets the paint color
	 * and paint style. 
	 * 
	 * @param x the x position of the upper left corner
	 * @param y the y position of the upper left corner
	 */
	public UserPaddle(int x, int y){
		curX = x;
		curY = y;
		
		paint = new Paint();
		
		if(MainActivity.color == "ec"){
			paint.setARGB(255, 0, 121, 72);
		}else{
			paint.setARGB(255, 255, 255, 255);
		}
		
		paint.setStyle(Paint.Style.FILL_AND_STROKE);
	}
	
	/**
	 * Updates the position of the paddle based off of a touch activity.
	 * 
	 * @param point the point on the screen where the user touched
	 */
	public void update(Point point){
		if(point.x > DisplayAdvisor.maxX/2){
		
			//if the touch is above the 1/2way point move up
			if(point.y > DisplayAdvisor.maxY/2 && curY+height < DisplayAdvisor.maxY-Splash.barHeight){
				curY += speed;
			}
			
			//else move down
			else if(point.y < DisplayAdvisor.maxY/2 && curY > 0){
				curY -= speed;
			}
			
			//if there is no touch don't move
			else{
				curY += 0;
			}
		}
		
	}

	/**
	 * Draws the paddle on the screen
	 * 
	 * @param canvas The screen canvas
	 */
	public void draw(Canvas canvas){
		canvas.drawRect(curX, curY, curX+width, curY+height, paint);
	}
}
