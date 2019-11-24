package com.example.whowantstobeamillionaire;

public class Question {
    private String question;
    private int answer;
    private String choice1, choice2, choice3, choice4;
    private int scoreWorth;

    public Question(String q, int a, String c1, String c2, String c3, String c4, int s){
        question = q;
        answer = a;
        choice1 = c1;
        choice2 = c2;
        choice3 = c3;
        choice4 = c4;
        scoreWorth = s;
    }

    public String getChoice1() {
        return choice1;
    }

    public void setChoice1(String choice1) {
        this.choice1 = choice1;
    }

    public String getChoice2() {
        return choice2;
    }

    public void setChoice2(String choice2) {
        this.choice2 = choice2;
    }

    public String getChoice3() {
        return choice3;
    }

    public void setChoice3(String choice3) {
        this.choice3 = choice3;
    }

    public String getChoice4() {
        return choice4;
    }

    public void setChoice4(String choice4) {
        this.choice4 = choice4;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public int getAnswer() {
        return answer;
    }

    public void setAnswer(int answer) {
        this.answer = answer;
    }

    public int getScoreWorth() {
        return scoreWorth;
    }

    public void setScoreWorth(int scoreWorth) {
        this.scoreWorth = scoreWorth;
    }
}
