package com.example.whowantstobeamillionaire;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;

public class SoundBank {

    Context context;
//    MediaPlayer swoosh, point, hit, wing;

    MediaPlayer oneMillion, hundred, twoThousand, fiveHundredThousand, sixtyFourThousand, commercialBreak, correctAnswer,
            finalAnswer, letsPlay, mainTheme, wrongAnswer;

    public SoundBank(Context context){
        this.context = context;
//        swoosh = MediaPlayer.create(context, R.raw.swoosh);
//        point = MediaPlayer.create(context, R.raw.point);
//        hit = MediaPlayer.create(context, R.raw.hit);
//        wing = MediaPlayer.create(context, R.raw.wing);

        oneMillion = MediaPlayer.create(context, Uri.parse("android.resource://app/res/raw/oneMillion"));
        hundred = MediaPlayer.create(context, Uri.parse("android.resource://app/res/raw/1001000-music"));
        twoThousand = MediaPlayer.create(context, Uri.parse("android.resource://app/res/raw/200032000"));
        fiveHundredThousand = MediaPlayer.create(context, Uri.parse("android.resource://app/res/raw/5000000-music"));
        sixtyFourThousand = MediaPlayer.create(context, Uri.parse("android.resource://app/res/raw/64000-music"));
        commercialBreak = MediaPlayer.create(context,  Uri.parse("android.resource://app/res/raw/commercial-break"));
        correctAnswer = MediaPlayer.create(context, Uri.parse("android.resource://app/res/raw/correct-answer"));
        finalAnswer = MediaPlayer.create(context, Uri.parse("android.resource://app/res/raw/final-answer"));
        letsPlay = MediaPlayer.create(context, Uri.parse("android.resource://app/res/raw/lets-play"));
        mainTheme = MediaPlayer.create(context, Uri.parse("android.resource://app/res/raw/main-theme"));
        wrongAnswer = MediaPlayer.create(context, Uri.parse("android.resource://app/res/raw/wrong-answer"));
    }

    public void playOneMillion(){
        if(oneMillion != null){
            oneMillion.start();
        }
    }

    public void playFiveHundredThousand(){
        if(fiveHundredThousand != null){
            fiveHundredThousand.start();
        }
    }

    public void playSixtyFourThousand(){
        if(sixtyFourThousand != null){
            sixtyFourThousand.start();
        }
    }

    public void playTwoThousand(){
        if(twoThousand != null){
            twoThousand.start();
        }
    }

    public void playHundred(){
        if(hundred != null){
            hundred.start();
        }
    }

    public void playCorrectAnswer(){
        if(correctAnswer != null){
            correctAnswer.start();
        }
    }

    public void playFinalAnswer(){
        if(finalAnswer != null){
            finalAnswer.start();
        }
    }

    public void playWrongAnswer(){
        if(wrongAnswer != null){
            wrongAnswer.start();
        }
    }

    public void letsPlay(){
        if(letsPlay != null){
            letsPlay.start();
        }
    }

    public void playCommercialBreak(){
        if(commercialBreak != null){
            commercialBreak.start();
        }
    }

    public void playMainTheme(){
        if(mainTheme != null){
            mainTheme.start();
        }
    }

}