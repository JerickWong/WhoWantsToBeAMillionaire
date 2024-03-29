package com.example.whowantstobeamillionaire;

import android.content.Context;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class GameView extends SurfaceView implements SurfaceHolder.Callback{

    GameThread gameThread;
    SurfaceHolder surfaceHolder;

    public GameView(Context context) {
        super(context);
        initView();
    }

    private void initView() {
        surfaceHolder = getHolder();
        setFocusable(true);
        gameThread = new GameThread(surfaceHolder);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        if(!gameThread.isRunning()){
            gameThread = new GameThread(holder);
            gameThread.start();
        }else {
            gameThread.start();
        }
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        if(gameThread.isRunning()){
            gameThread.setIsRunning(false);
            boolean retry = true;
            while(retry){
                try{
                    gameThread.join();
                    retry = false;
                }catch(InterruptedException e){}
            }
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        // Tap is detected
        if(action == MotionEvent.ACTION_DOWN){
            if(AppConstants.getGameEngine().gameState == 0){
                AppConstants.getGameEngine().gameState = 1;
            } else {
            }
//            AppConstants.getGameEngine().bird.setVelocity(AppConstants.VELOCITY_WHEN_JUMPED);
        }
        return true;
    }
}
