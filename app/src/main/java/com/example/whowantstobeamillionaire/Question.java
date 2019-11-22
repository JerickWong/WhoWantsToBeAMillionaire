package com.example.whowantstobeamillionaire;

public class Question {
    private String question;
    private String answer;
    private int scoreWorth;

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public int getScoreWorth() {
        return scoreWorth;
    }

    public void setScoreWorth(int scoreWorth) {
        this.scoreWorth = scoreWorth;
    }
}
