package com.a16mb.damrod.quizwars;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    //@InjectView(R.id.info_text_view)
    //TextView textViewInfo;

    @InjectView(R.id.questions_list_view)
    ListView questionListView;

    @InjectView(R.id.button_begin_quiz)
    Button beginQuizButton;

    @InjectView(R.id.button_check_quiz)
    Button checkQuizButton;

    @InjectView(R.id.player_text_view)
    TextView playerTextView;

    String redPlayer, bluePlayer, currentPlayer;
    int redPlayerPoints =0;
    int bluePlayerPoints =0;


    List<QuestionModel> blueQuestionList, redQuestionList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.inject(this);

        Intent intent = getIntent();
        redPlayer = intent.getStringExtra("redPlayer");
        bluePlayer = intent.getStringExtra("bluePlayer");

        OkHttpHandler okHttpHandler = new OkHttpHandler();
        okHttpHandler.execute();

        currentPlayer = redPlayer;

        playerTextView.setText("Questions for: \n"+currentPlayer);

        beginQuizButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                populateListView();
                beginQuizButton.setEnabled(false);
                checkQuizButton.setEnabled(true);
            }
        });

        checkQuizButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(currentPlayer != bluePlayer)
                {
                    beginQuizButton.setEnabled(true);
                    checkQuizButton.setEnabled(false);
                    redPlayerPoints = checkAnswers(redQuestionList);
                    currentPlayer = bluePlayer;
                    playerTextView.setText("Questions for: \n"+currentPlayer);
                }
                else
                {
                    bluePlayerPoints = checkAnswers(blueQuestionList);
                    displaySummary();
                }
            }
        });

    }

    private void displaySummary() {
        Intent intent = new Intent(this, SummaryActivity.class);
        intent.putExtra("redPlayer",redPlayer );
        intent.putExtra("bluePlayer", bluePlayer);
        intent.putExtra("redPlayerPoints", redPlayerPoints);
        intent.putExtra("bluePlayerPoints", bluePlayerPoints);
        intent.putExtra("redQuestionList", (Serializable) redQuestionList);
        intent.putExtra("blueQuestionList", (Serializable) blueQuestionList);
        startActivity(intent);
        finish();
        }

    private int checkAnswers(List<QuestionModel> answersToCheck) {

        int points = 0;
        for(int i=0; i<answersToCheck.size(); i++)
        {
            if(answersToCheck.get(i).getSelected_answer() == answersToCheck.get(i).getCorrect_answer())
            {
                points++;
            }
        }
        questionListView.setAdapter(null);
        return points;
    }

    public void populateListView()
    {
        if(currentPlayer == bluePlayer)
        {
            QuestionAdapter questionAdapter = new QuestionAdapter(this, blueQuestionList);
            questionListView.setAdapter(questionAdapter);
        }
        if(currentPlayer == redPlayer)
        {
            QuestionAdapter questionAdapter = new QuestionAdapter(this, redQuestionList);
            questionListView.setAdapter(questionAdapter);
        }

    }

    private class OkHttpHandler extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {
            String url = "https://opentdb.com/api.php?amount=10&type=multiple";
            OkHttpClient client = new OkHttpClient();

            Request request = new Request.Builder().url(url).build();
            try {
                Response response = client.newCall(request).execute();
                return response.body().string();
            }
             catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(final String s) {
            super.onPostExecute(s);
            JsonFormatter jsonFormatter = new JsonFormatter();
            blueQuestionList = jsonFormatter.GetQuestionList(s);
            redQuestionList = jsonFormatter.GetQuestionList(s);
            beginQuizButton.setEnabled(true);
            Toast.makeText(MainActivity.this, "Questions downloaded successfully", Toast.LENGTH_SHORT).show();
        }
    }


}
