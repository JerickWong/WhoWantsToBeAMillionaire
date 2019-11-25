package com.example.whowantstobeamillionaire;

import android.graphics.Canvas;
import android.os.SystemClock;
import android.util.Log;
import android.view.SurfaceHolder;

public class GameThread extends Thread {
    private SurfaceHolder surfaceHolder; //Surfaceholder object reference
    private boolean isRunning; // Flag to detect whether the thread is running or not
    private long startTime, loopTime; // Loop start time and loop duration
    private long DELAY = 33; // Delay in milliseconds between screen refreshes

    public GameThread(SurfaceHolder surfaceHolder) {
        this.surfaceHolder = surfaceHolder;
        isRunning = true;
    }

    @Override
    public void run() {
        // Looping until the boolean is false
        while(isRunning){
            startTime = SystemClock.uptimeMillis();

            // This is where we draw things into the canvas
            Canvas canvas = surfaceHolder.lockCanvas(null);
            if(canvas != null) {
                synchronized (canvas) {
                    AppConstants.getGameEngine().drawBackground(canvas);
                    AppConstants.getGameEngine().drawQuestionBox(canvas);
                    AppConstants.getGameEngine().drawFirstChoice(canvas);
                    AppConstants.getGameEngine().drawSecondChoice(canvas);
                    AppConstants.getGameEngine().drawThirdChoice(canvas);
                    AppConstants.getGameEngine().drawFourthChoice(canvas);

                    surfaceHolder.unlockCanvasAndPost(canvas);
                }
            }
            //loop time
            loopTime = SystemClock.uptimeMillis() - startTime;
            // Pausing here to make sure we update the right amount per second
            if(loopTime < DELAY){
                runTimer();
            }
        }
    }

    public void runTimer() {
        int timer = 60;

        while(timer > 0) {
            GameActivity.countDown.setText(timer);
            try{
                timer--;
                Thread.sleep(60000L);
            } catch (InterruptedException e) {
                Log.e("Interrupted","Interrupted while sleeping");
            }
        }
    }

    // Return whether the thread is running
    public boolean isRunning(){
        return isRunning;
    }

    // Sets the thread state, false = stopped, true = running
    public void setIsRunning(boolean state){
        isRunning = state;
    }
}
