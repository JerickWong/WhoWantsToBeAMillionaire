package com.example.whowantstobeamillionaire;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class DatabaseQuestions {

    static DatabaseReference databaseQuestions;
    static ArrayList<Question> questions;

    public static void initialize() {
        databaseQuestions = FirebaseDatabase.getInstance().getReference("questions");
        questionsList();
        addingQuestions();
    }

    public static void questionsList() {
        String id = databaseQuestions.push().getKey();
        String question = "What is the largest continent of the world?";
        String answer = "Asia";
        String optionA = "Asia";
        String optionB = "North America";
        String optionC = "Australia";
        String optionD = "Europe";
        String category = "0";
        Question q = new Question(id, question, answer, optionA, optionB, optionC, optionD,category);
        questions.add(q);

        id = databaseQuestions.push().getKey();
        question = "Who is the wife of Barack Obama?";
        answer = "Michelle Obama";
        optionA = "Michelle Obama";
        optionB = "Barack Obama Jr.";
        optionC = "Maria Obama";
        optionD = "Moira Obama";
        category = "0";
        q = new Question(id, question, answer, optionA, optionB, optionC, optionD,category);
        questions.add(q);

        id = databaseQuestions.push().getKey();
        question = "What is the biggest island of the world?";
        answer = "Greenland";
        optionA = "Greenland";
        optionB = "Russia";
        optionC = "North America";
        optionD = "Antarctica";
        category = "1,000";
        q = new Question(id, question, answer, optionA, optionB, optionC, optionD,category);
        questions.add(q);

        id = databaseQuestions.push().getKey();
        question = "How many states are there in the United States of America?";
        answer = "50 States";
        optionA = "50 States";
        optionB = "52 States";
        optionC = "53 States";
        optionD = "51 States";
        category = "1,000";
        q = new Question(id, question, answer, optionA, optionB, optionC, optionD,category);
        questions.add(q);

        id = databaseQuestions.push().getKey();
        question = "Which state is known as the Empire State?";
        answer = "New York State";
        optionA = "New York State";
        optionB = "Washington D.C.";
        optionC = "Los Angeles California";
        optionD = "Chicago";
        category = "1,000";
        q = new Question(id, question, answer, optionA, optionB, optionC, optionD,category);
        questions.add(q);

        id = databaseQuestions.push().getKey();
        question = "Name the largest ocean of the world?";
        answer = "Pacific Ocean";
        optionA = "Pacific Ocean";
        optionB = "Atlantic Ocean";
        optionC = "Indian Ocean";
        optionD = "Mediterranian Ocean";
        category = "1,000";
        q = new Question(id, question, answer, optionA, optionB, optionC, optionD,category);
        questions.add(q);

        id = databaseQuestions.push().getKey();
        question = "What is the meaning of Leap Year?";
        answer = "Having a February of 29 days";
        optionA = "Having a February of 29 days";
        optionB = "Having a February of 28 days";
        optionC = "Having a February of 30 days";
        optionD = "Having a February of 31 days";
        category = "1,000";
        q = new Question(id, question, answer, optionA, optionB, optionC, optionD,category);
        questions.add(q);

        id = databaseQuestions.push().getKey();
        question = "Which is the tallest mammal of the world?";
        answer = "Giraffe";
        optionA = "Giraffe";
        optionB = "Elephant";
        optionC = "Ostrich";
        optionD = "Whale";
        category = "1,000";
        q = new Question(id, question, answer, optionA, optionB, optionC, optionD,category);
        questions.add(q);

        id = databaseQuestions.push().getKey();
        question = "How many bones are there in a human body?";
        answer = "206 bones";
        optionA = "206 bones";
        optionB = "232 bones";
        optionC = "219 bones";
        optionD = "224 bones";
        category = "15,000";
        q = new Question(id, question, answer, optionA, optionB, optionC, optionD,category);
        questions.add(q);

        id = databaseQuestions.push().getKey();
        question = "What is the diameter of the Earth?";
        answer = "12,742 kilometers";
        optionA = "12,742 kilometers";
        optionB = "13,912 kilometers";
        optionC = "11,225 kilometers";
        optionD = "10,837 kilometers";
        category = "15,000";
        q = new Question(id, question, answer, optionA, optionB, optionC, optionD,category);
        questions.add(q);

        id = databaseQuestions.push().getKey();
        question = "What is the official nickname of Texas?";
        answer = "The Loner Star State";
        optionA = "The Loner Star State";
        optionB = "The Star State";
        optionC = "State of the nation";
        optionD = "Nation star";
        category = "15,000";
        q = new Question(id, question, answer, optionA, optionB, optionC, optionD,category);
        questions.add(q);

        id = databaseQuestions.push().getKey();
        question = "";
        answer = "";
        optionA = "";
        optionB = "";
        optionC = "";
        optionD = "";
        category = "15,000";
        q = new Question(id, question, answer, optionA, optionB, optionC, optionD,category);
        questions.add(q);

        id = databaseQuestions.push().getKey();
        question = "";
        answer = "";
        optionA = "";
        optionB = "";
        optionC = "";
        optionD = "";
        category = "15,000";
        q = new Question(id, question, answer, optionA, optionB, optionC, optionD,category);
        questions.add(q);

        id = databaseQuestions.push().getKey();
        question = "";
        answer = "";
        optionA = "";
        optionB = "";
        optionC = "";
        optionD = "";
        category = "15,000";
        q = new Question(id, question, answer, optionA, optionB, optionC, optionD,category);
        questions.add(q);
    }

    public static void addQuestion(String question, String answer, String A, String B, String C, String D, String category) {
//        DatabaseReference databaseQuestions = FirebaseDatabase.getInstance().getReference("questions");
        String id = databaseQuestions.push().getKey();
        Question ques = new Question(id, question, answer, A, B, C, D, category);
        databaseQuestions.child(id).setValue(ques);
    }

    public static void addingQuestions() {
        for (Question question: questions) {
            String ID = question.getID();
            databaseQuestions.child(ID).setValue(question);
        }
    }
}
