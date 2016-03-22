/**
 * Author:	Peter Zorzonello
 * Date:	4/16/15
 * File:	GameSurfaceView.java
 * 
 * This class is responsible for the running of the game. 
 * While the game is running this class is constantly running.
 * It calls all of the functions repeatedly update the display to represent
 * the events in the game
 */
package com.zorzonello.pong;


import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class GameSurfaceView extends SurfaceView implements Runnable {
	boolean isRunning = false;
    private Thread thread = null;
    private GameActivity activity;
    Paint paint;
    
    //the ideal start speed 
    final static int IDEAL_START_SPEED = 10;
    //the ideal second speed for when the game progresses
    final static int IDEAL_SECOND_SPEED = 15;
    //the ideal third speed for when the game progresses
    final static int IDEAL_THIRD_SPEED = 26;
    
    //ideal sizes for the objects on the screen
    final int IDEAL_PADDLE_WIDTH = 30;
    final int IDEAL_PADDLE_SPACE = 40;
    final int IDEAL_FONT_SIZE = 100;
    
    //the time to wait before a game begins from the main menu
    final int IDEAL_START_TIMER = 800;
    
    //scaling sizes
    int fontSize = (int)(IDEAL_FONT_SIZE*DisplayAdvisor.getScaleX());
    int paddleWidth = (int) (IDEAL_PADDLE_WIDTH*DisplayAdvisor.getScaleX());
    int paddleSpace = (int) (IDEAL_PADDLE_SPACE*DisplayAdvisor.getScaleX());
    
    
    int startTimer = 800;
    long startTime;
    
    //the scores
    static int playerScore;
    static int aiScore;
    
    
    //the objects on the screen
    public UserPaddle user;
    public AIPaddle ai;
    public Ball ball;

    
    /**
     * When a game starts set the scores to 0
     * Create the ball and the paddles
     * 
     * @param context the GameActivity Activity
     */
    public GameSurfaceView(GameActivity context) {
    	
		super(context);	
		activity = context;
		
		ball = new Ball(DisplayAdvisor.maxX/2, DisplayAdvisor.maxY/2);
		
		playerScore = 0;
		aiScore = 0;
		
		startTime = System.currentTimeMillis();
		
		user = new UserPaddle(DisplayAdvisor.maxX-paddleWidth - paddleSpace, DisplayAdvisor.maxY/2 -paddleSpace);
		ai = new AIPaddle(paddleSpace, 0);
		
	}
    
    /**
     * When the app is running perform all of the necessary functions 
     * to make the circle move, check collisions, and accept touch. 
     */
	public void run() {
		
		//hold off on letting the game start so the game can load before the ball moves
		while (isRunning) {
			long curTime = System.currentTimeMillis()-startTime;
			if(curTime <startTimer){
				try{
					Thread.sleep(startTimer-curTime);
				}catch(Exception e){
					
				}
			}
			
			//if there is a touch event move the user paddle
			if(activity.isTouching){
				//boolean isPressed = activity.isTouching;
				user.update(activity.getTouch());
			}
			
			//update the ai paddle and ball automatically 
			ai.update(ball);
			ball.update(user, ai, activity);
			
			if(aiScore == MainActivity.playScore || playerScore == MainActivity.playScore){
				activity.startResultsScreen();
			}
		
			//draw all the necessary stuff
			SurfaceHolder surfaceHolder = getHolder();
			if (surfaceHolder.getSurface().isValid()) {
				Canvas canvas = surfaceHolder.lockCanvas();
				if(MainActivity.color == "ec"){
					canvas.drawARGB(255, 14,63,93);
				}else{
					canvas.drawARGB(255, 0, 0, 0);
				}
				drawEverything(canvas);
				surfaceHolder.unlockCanvasAndPost(canvas);
			}
		}
	}
	

	/**
	 * call the draw methods to draw 
	 * the paddles and the ball
	 * 
	 * @param canvas the canvas of the screen
	 */
	private void drawEverything(Canvas canvas) {
		paint = new Paint();
		
		if(MainActivity.color == "ec"){
			paint.setARGB(255, 0, 121, 72);
		}else{
			paint.setARGB(255, 255, 255, 255);
		}
		
		paint.setTextSize(fontSize);
		canvas.drawText(String.valueOf(playerScore), DisplayAdvisor.maxX -(DisplayAdvisor.maxX/4), fontSize, paint);
		canvas.drawText(String.valueOf(aiScore), DisplayAdvisor.maxX/4, fontSize, paint);
		
		ball.draw(canvas);
		user.draw(canvas);
		ai.draw(canvas);
	}

	/**
	 * when the app is paused make isRunning false and join the threads
	 * this suspends the game
	 */
	public void onPause() {
        isRunning = false;
        
        while (thread != null) {
            try {
                thread.join();
                thread = null;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        
    }
    
	/**
	 * when the the app resumes make isRunning true and start a new thread
	 */
    public void onResume() {
        isRunning = true;
        thread = new Thread(this);
        thread.start();
    }
}

