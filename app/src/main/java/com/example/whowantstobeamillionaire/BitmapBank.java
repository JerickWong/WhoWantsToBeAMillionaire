package com.example.whowantstobeamillionaire;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class BitmapBank {

    Bitmap choiceBox1, choiceBox2, choiceBox3, choiceBox4;
    Bitmap questionBox;
    Bitmap background;

    int xPos1, yPos1;
    int xPos2, yPos2;
    int  xPos3, yPos3;
    int xPos4, yPos4;
    int xPosQ, yPosQ;

    public BitmapBank(Resources res){
        background = BitmapFactory.decodeResource(res, R.drawable.gamebg);
        background = scaleImage(background);
        choiceBox1 = BitmapFactory.decodeResource(res, R.drawable.choicebox);
        choiceBox2 = BitmapFactory.decodeResource(res, R.drawable.choicebox);
        choiceBox3 = BitmapFactory.decodeResource(res, R.drawable.choicebox);
        choiceBox4 = BitmapFactory.decodeResource(res, R.drawable.choicebox);
        questionBox = BitmapFactory.decodeResource(res, R.drawable.questionbox);

        xPos1 = xPos2 = xPos3 = xPos4 = xPosQ = 35;
        yPos1 = 320;
        yPos2 = 376;
        yPos3 = 432;
        yPos4 = 486;
        yPosQ = 147;
    }

    public Bitmap getChoiceBox1() {
        return choiceBox1;
    }

    public Bitmap getChoiceBox2() {
        return choiceBox2;
    }

    public Bitmap getChoiceBox3() {
        return choiceBox3;
    }

    public Bitmap getChoiceBox4() {
        return choiceBox4;
    }

    public Bitmap getQuestionBox() {
        return questionBox;
    }

    public Bitmap getBackground() {
        return background;
    }

    public int getChoiceBox1Height(){
        return choiceBox1.getHeight();
    }

    public int getChoiceBox2Height(){
        return choiceBox2.getHeight();
    }

    public int getChoiceBox3Height(){
        return choiceBox3.getHeight();
    }

    public int getChoiceBox4Height(){
        return choiceBox4.getHeight();
    }

    public int getQuestionBoxHeight(){
        return questionBox.getHeight();
    }

    public int getQuestionBoxWidth(){
        return questionBox.getWidth();
    }

    public int getChoiceBox1Width(){
        return choiceBox1.getWidth();
    }

    public int getChoiceBox2Width(){
        return choiceBox2.getWidth();
    }

    public int getChoiceBox3Width(){
        return choiceBox3.getWidth();
    }

    public int getChoiceBox4Width(){
        return choiceBox4.getWidth();
    }

    public int getBackgroundWidth(){
        return background.getWidth();
    }

    //Return background height
    public int getBackgroundHeight(){
        return background.getHeight();
    }

    public Bitmap scaleImage(Bitmap bitmap){
        float widthHeightRatio = getBackgroundWidth() / getBackgroundHeight();
        /*
        We'll multiply widthHeightRatio with screenHeight to get scaled width of the bitmap.
        Then call createScaledBitmap() to create a new bitmap, scaled from an existing bitmap, when possible.
         */
        int backgroundScaledWidth = (int) widthHeightRatio * AppConstants.SCREEN_HEIGHT;
        return Bitmap.createScaledBitmap(bitmap, backgroundScaledWidth, AppConstants.SCREEN_HEIGHT, false);
    }

    public int getxPos1() {
        return xPos1;
    }

    public int getyPos1() {
        return yPos1;
    }

    public int getxPos2() {
        return xPos2;
    }

    public int getyPos2() {
        return yPos2;
    }

    public int getxPos3() {
        return xPos3;
    }

    public int getyPos3() {
        return yPos3;
    }

    public int getxPos4() {
        return xPos4;
    }

    public int getyPos4() {
        return yPos4;
    }

    public int getxPosQ() {
        return xPosQ;
    }

    public int  getyPosQ() {
        return yPosQ;
    }
}
