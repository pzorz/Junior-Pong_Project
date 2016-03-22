/**
 * Author:	Peter Zorzonello
 * File:	Ball.java
 * Date:	4/21/15
 * 
 * This class represents the ball.
 * It draws the ball and updates its position. This class
 * also decides if there was a collision event. 
 */
package com.zorzonello.pong;

import java.util.Random;

import android.graphics.Canvas;
import android.graphics.Paint;

public class Ball {
	
	int curX, curY;
	
	//the ideal values for the game
	final int IDEAL_RADIUS = 40;
	final int IDEAL_SPEED = 10;
	final int IDEAL_PADDLE_HEIGHT = 300;
	final int IDEAL_PADDLE_WIDTH = 30;
	
	//scaled value for the game
	
	int radius = (int) (IDEAL_RADIUS*DisplayAdvisor.getScaleX());
	int width = (int)(IDEAL_PADDLE_WIDTH*DisplayAdvisor.getScaleX());
	int paddleHeight = (int)(IDEAL_PADDLE_HEIGHT*DisplayAdvisor.getScaleX());
	
	int speed;
	Paint paint;
	int xSpeed, ySpeed;
	
	/**
	 * Create the ball.
	 * Also set the speed and the pain color
	 * 
	 * @param x	the ball's x position
	 * @param y	the ball's y position
	 */
	public Ball(int x, int y){
		curX = x;
		curY = y;
		
		paint = new Paint();
		
		if(MainActivity.color == "ec"){
			paint.setARGB(255,0,121,72);
		}else{
			paint.setARGB(255, 255, 255, 255);
		}
		
		
		speed = (int)(GameSurfaceView.IDEAL_START_SPEED*DisplayAdvisor.getScaleX());
		xSpeed = speed;
		ySpeed = -speed;
	}
	
	/**
	 * Place the ball back in the center of the screen
	 * Update the speed based off of the score.
	 * Picks a random number which is used to determine the direction 
	 * the ball will begin to move in.
	 */
	public void replaceBall(){
		Random rnd = new Random();
		int random = rnd.nextInt((3 - 0) + 1) + 0;
		
		curX = DisplayAdvisor.maxX/2;
		curY = DisplayAdvisor.maxY/2;
		
		if(GameSurfaceView.aiScore >= 10 || GameSurfaceView.playerScore >= 10){
			speed = (int)(GameSurfaceView.IDEAL_THIRD_SPEED*DisplayAdvisor.getScaleX());
			if(random == 0){
				xSpeed = speed;
				ySpeed = -speed;
			}else if(random == 1){
				xSpeed = speed;
				ySpeed = speed;
			}
			else if(random == 2){
				xSpeed = -speed;
				ySpeed = -speed;
			}
			else{
				xSpeed = -speed;
				ySpeed = speed;
			}
		}else if(GameSurfaceView.aiScore >= 5 || GameSurfaceView.playerScore >= 5){
			speed = (int)(GameSurfaceView.IDEAL_SECOND_SPEED*DisplayAdvisor.getScaleX());
			if(random == 0){
				xSpeed = speed;
				ySpeed = -speed;
			}else if(random == 1){
				xSpeed = speed;
				ySpeed = speed;
			}
			else if(random == 2){
				xSpeed = -speed;
				ySpeed = -speed;
			}
			else{
				xSpeed = -speed;
				ySpeed = speed;
			}
		}else{
			speed = (int)(GameSurfaceView.IDEAL_START_SPEED*DisplayAdvisor.getScaleX());
			if(random == 0){
				xSpeed = speed;
				ySpeed = -speed;
			}else if(random == 1){
				xSpeed = speed;
				ySpeed = speed;
			}
			else if(random == 2){
				xSpeed = -speed;
				ySpeed = -speed;
			}
			else{
				xSpeed = -speed;
				ySpeed = speed;
			}	
		}
		
	}
	
	/**
	 * Update the position of the ball
	 * Check if the ball collided based off of the position of the ball 
	 * and the position of the paddles
	 * 
	 * @param user		the user paddle
	 * @param ai		the AI paddle
	 * @param activity	the GameActivity
	 */
	public void update(UserPaddle user, AIPaddle ai, GameActivity activity){
		
		if(curY  >= DisplayAdvisor.maxY-Splash.barHeight){
			ySpeed = -speed;
		}
		else if(curY <= radius){
			ySpeed = speed;
		}
		
		if (curX + radius >= user.curX  && user.curY < curY && curY < user.curY+paddleHeight ){
			xSpeed = -speed;
			activity.ping.start();
			
		
		}else if (curX - radius <= ai.curX+width && ai.curY < curY && curY < ai.curY+paddleHeight ){
			xSpeed = speed;
			ai.speed = (int)(9*DisplayAdvisor.getScaleX());
			activity.ping.start();		
		}
		
		//the ball hits the left side past the ai paddle
		//the player gets a point;
		else if(curX  <= 0){
			GameSurfaceView.playerScore++;
			replaceBall();
			activity.beep.start();	
		}
		
		//ball hits right side, past the player paddle
		//the ai gets a point
		else if(curX >= DisplayAdvisor.maxX-radius){
			GameSurfaceView.aiScore++;
			replaceBall();
			activity.beep.start();			
		}
		
		//move the ball
		curX += xSpeed;
		curY += ySpeed;
	}

	
	/**
	 * Draws the ball on the screen
	 * 
	 * @param canvas the canvas of the screen
	 */
	public void draw(Canvas canvas) {
		canvas.drawCircle(curX, curY, radius, paint);
	}
}
