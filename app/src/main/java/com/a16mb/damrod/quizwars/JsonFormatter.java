package com.a16mb.damrod.quizwars;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Damrod on 11.04.2017.
 */

public class JsonFormatter {

    public List<QuestionModel> GetQuestionList(String json){

        if(json !="" || json != null)
        {
            QuestionModel questionModel;
            List<QuestionModel> questionsList = new ArrayList<QuestionModel>();
            String category, type,difficulty, question, correct_answer, incorrect_answers;
            JSONObject jsonObject;
            JSONArray jsonArray;

            try {
                jsonObject = new JSONObject(json);
                jsonArray = jsonObject.getJSONArray("results");

                for(int i=0; i<jsonArray.length(); i++) {
                    JSONObject jsonUser = jsonArray.getJSONObject(i);
                    category = jsonUser.getString("category");
                    type = jsonUser.getString("type");
                    difficulty = jsonUser.getString("difficulty");
                    question = jsonUser.getString("question");
                    correct_answer = jsonUser.getString("correct_answer");
                    incorrect_answers = jsonUser.getString("incorrect_answers");

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

                    incorrect_answers = incorrect_answers.replace("&#039;", "\'");
                    incorrect_answers = incorrect_answers.replace("&quot;","\"");
                    //Remove strange signs from json -.-

                    questionModel = new QuestionModel(category, type, difficulty, question, correct_answer, incorrect_answers);
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
