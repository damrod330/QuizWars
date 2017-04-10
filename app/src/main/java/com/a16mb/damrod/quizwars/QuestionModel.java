package com.a16mb.damrod.quizwars;

/**
 * Created by Damrod on 11.04.2017.
 */

public class QuestionModel {

    String category, type, difficulty, question, correct_answer, incorrect_answers;

    public QuestionModel(String category, String type, String difficulty, String question, String correct_answer, String incorrect_answers) {
        this.category = category;
        this.type = type;
        this.difficulty = difficulty;
        this.question = question;
        this.correct_answer = correct_answer;
        this.incorrect_answers = incorrect_answers;
    }

    public String getCategory() {
        return category;
    }

    public String getType() {
        return type;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public String getQuestion() {
        return question;
    }

    public String getCorrect_answer() {
        return correct_answer;
    }

    public String getIncorrect_answers() {
        return incorrect_answers;
    }
}
