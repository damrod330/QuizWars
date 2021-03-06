package com.a16mb.damrod.quizwars;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Damrod on 11.04.2017.
 */

public class QuestionModel implements Serializable{

    private String category;
    private String type;
    private String difficulty;
    private String question;
    private String correct_answer;
    private String selected_answer;
    private List<String> all_answers;

    public String getSelected_answer() {
        return selected_answer;
    }

    public void setSelected_answer(String selected_answer) {

        this.selected_answer = selected_answer;
    }

    public QuestionModel(String category, String type, String difficulty, String question, String correct_answer, List<String> all_answers) {
        this.category = category;
        this.type = type;
        this.difficulty = difficulty;
        this.question = question;
        this.correct_answer = correct_answer;
        this.all_answers = all_answers;
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

    public List<String> getAll_answers() {
        return all_answers;
    }
}
