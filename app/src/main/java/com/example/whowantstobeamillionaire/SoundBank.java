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

        oneMillion = MediaPlayer.create(context, R.raw.onemillion);
        hundred = MediaPlayer.create(context, R.raw.hundred);
        twoThousand = MediaPlayer.create(context, R.raw.twothousand);
        fiveHundredThousand = MediaPlayer.create(context, R.raw.fivehundredthousand);
        sixtyFourThousand = MediaPlayer.create(context, R.raw.sixtyfourthousand);
        commercialBreak = MediaPlayer.create(context,  R.raw.commerical_break);
        correctAnswer = MediaPlayer.create(context, R.raw.correct_answer);
        finalAnswer = MediaPlayer.create(context, R.raw.final_answer);
        letsPlay = MediaPlayer.create(context, R.raw.lets_play);
        mainTheme = MediaPlayer.create(context, R.raw.main_theme);
        wrongAnswer = MediaPlayer.create(context, R.raw.wrong_answer);
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