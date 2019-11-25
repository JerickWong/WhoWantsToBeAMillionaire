package com.example.whowantstobeamillionaire;

public class Question {
    private String question;
    private String answer;
    private String optionA;
    private String optionB;
    private String optionC;
    private String optionD;
    private int scoreWorth;
    private String category;

    public Question (String ques, String ans, String A, String B, String C, String D, int worth, String categ) {
        question = ques;
        answer = ans;
        optionA = A;
        optionB = B;
        optionC = C;
        optionD = D;
        scoreWorth = worth;
        category = categ;
    }

    public String getQuestion() {
        return question;
    }

    public String getAnswer() {
        return answer;
    }

    public int getScoreWorth() {
        return scoreWorth;
    }

    public String getOptionA() {
        return optionA;
    }

    public String getOptionB() {
        return optionB;
    }

    public String getOptionC() {
        return optionC;
    }

    public String getOptionD() {
        return optionD;
    }

    public String getCategory() {
        return category;
    }
}
