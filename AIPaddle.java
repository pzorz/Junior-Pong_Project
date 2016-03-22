/**
 * Author:	Peter Zorzonello
 * File:	AIPaddle.java
 * Date:	4/20/15
 * 
 * This class represents the ai's paddle. 
 * It handles moving the paddle based off of the ball's location
 * and it also draws the paddle
 */
package com.zorzonello.pong;

import java.util.Random;

import android.graphics.Canvas;
import android.graphics.Paint;

public class AIPaddle {
	int curX, curY;
	
	//ideal values for the game
	final int IDEAL_HEIGHT = 300;
	final int IDEAL_WIDTH = 30;
	final int IDEAL_SPEED = 9;
	
	//scaled value for the game 
	int width = (int)(IDEAL_WIDTH*DisplayAdvisor.getScaleX());
	int height = (int)(IDEAL_HEIGHT*DisplayAdvisor.getScaleX());
	int speed = (int)(IDEAL_SPEED*DisplayAdvisor.getScaleX());
	
	Paint paint;
	
	/**
	 * Creates the paddle
	 * 
	 * @param x the x position in the upper left corner
	 * @param y the y position of the upper left corner
	 */
	public AIPaddle(int x, int y){
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
	 * Moves the paddle so the middle of the paddle is lined up with 
	 * the y position of the center of the ball
	 * 
	 * @param ball the game ball
	 */
	public void update(Ball ball){
		//makes the game harder more often then not the paddle will speed up so 
		//the ai dosn't lose
		if(ball.curY >= DisplayAdvisor.maxY-Splash.barHeight || ball.curY <= ball.radius){
			Random rnd = new Random();
			int random = rnd.nextInt((4 - 0) + 1) + 0;
			if(random == 0 || random == 1 || random == 2){
				speed = ball.speed + 1;
			}
			else{
				speed = (int)(IDEAL_SPEED*DisplayAdvisor.getScaleX());
			}
			
		}
		
		//moves the paddle to line up with the ball
		if(ball.curY > (curY+height/2)){
			curY += speed;
		}
		else if(ball.curY < (curY+height/2)){
			curY -= speed;
		}
		
	}

	/**
	 * Draws the paddle on the screen
	 * 
	 * @param canvas the game's canvas
	 */
	public void draw(Canvas canvas){
		canvas.drawRect(curX, curY, curX+width, curY+height, paint);
	}

}
