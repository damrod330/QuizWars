package com.a16mb.damrod.quizwars;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Created by Damrod on 11.04.2017.
 */

public class JsonFormatter {

    public List<QuestionModel> GetQuestionList(String json){

        if(json !="" || json != null)
        {
            QuestionModel questionModel;
            List<QuestionModel> questionsList = new ArrayList<QuestionModel>();

            String category, type,difficulty, question, correct_answer;

            JSONObject jsonObject;
            JSONArray jsonArray, jsonArrayIncorrectAnswers;

            try {
                jsonObject = new JSONObject(json);
                jsonArray = jsonObject.getJSONArray("results");


                for(int i=0; i<jsonArray.length(); i++) {
                    JSONObject jsonQuestion = jsonArray.getJSONObject(i);
                    category = jsonQuestion.getString("category");
                    type = jsonQuestion.getString("type");
                    difficulty = jsonQuestion.getString("difficulty");
                    question = jsonQuestion.getString("question");
                    correct_answer = jsonQuestion.getString("correct_answer");

                    List<String> all_answers = new ArrayList<String>();


                    //incorrect_answers = jsonQuestion.getString("incorrect_answers");
                    jsonArrayIncorrectAnswers = jsonQuestion.getJSONArray("incorrect_answers");
                    for(int j=0; j<jsonArrayIncorrectAnswers.length(); j++) {
                        String s1 = jsonArrayIncorrectAnswers.getString(j);
                        s1 = s1.replace("&#039;", "\'");
                        s1 = s1.replace("&quot;","\"");
                        all_answers.add(s1);
                    }

                    //Remove strange signs from json -.-
                    category = category.replace("&#039;", "\'");
                    category = category.replace("&quot;","\"");

                    type = type.replace("&#039;", "\'");
                    type = type.replace("&quot;","\"");

                    difficulty = difficulty.replace("&#039;", "\'");
                    difficulty = difficulty.replace("&quot;","\"");

                    question = question.replace("&#039;", "\'");
                    question = question.replace("&quot;","\"");

                    correct_answer = correct_answer.replace("&#039;", "\'");
                    correct_answer = correct_answer.replace("&quot;","\"");

                    //randomize answers in list
                    all_answers.add(correct_answer);
                    Collections.shuffle(all_answers);

                    questionModel = new QuestionModel(category, type, difficulty, question, correct_answer, all_answers);
                    questionsList.add(questionModel);
                }
                return questionsList;
            } catch (JSONException e) {
                e.printStackTrace();
                return null;
            }
        }
        else
        {
            return null;
        }
    }
}
