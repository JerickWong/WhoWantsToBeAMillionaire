package com.example.whowantstobeamillionaire;

public class Question {
    private String ID;
    private String question;
    private String answer;
    private String optionA;
    private String optionB;
    private String optionC;
    private String optionD;
    private String category;

    public Question (String id, String ques, String ans, String A, String B, String C, String D, String categ) {
        ID = id;
        question = ques;
        answer = ans;
        optionA = A;
        optionB = B;
        optionC = C;
        optionD = D;
        category = categ;
    }
    public Question (String ques, String ans, String A, String B, String C, String D, String categ) {
        question = ques;
        answer = ans;
        optionA = A;
        optionB = B;
        optionC = C;
        optionD = D;
        category = categ;
    }

    public Question() {}

    public Question(Question value) {
        question = value.question;
        answer = value.answer;
        optionA = value.optionA;
        optionB = value.optionB;
        optionC = value.optionC;
        optionD = value.optionD;
        category = value.category;
    }

    public String getID() {
        return ID;
    }

    public String getQuestion() {
        return question;
    }

    public String getAnswer() {
        return answer;
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
