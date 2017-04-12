package com.a16mb.damrod.quizwars;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class SummaryActivity extends AppCompatActivity {

    @InjectView(R.id.correct_answers_text_view)
    TextView correctAnswersTextView;

    @InjectView(R.id.restart_button)
    Button restartButton;

    @InjectView(R.id.summary_text_view)
    TextView summaryTextView;

    String redPlayer, bluePlayer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);

        ButterKnife.inject(this);

        int redPlayerPoints = getIntent().getIntExtra("redPlayerPoints", 0);
        int bluePlayerPoints = getIntent().getIntExtra("bluePlayerPoints", 0);

        redPlayer = getIntent().getStringExtra("redPlayer");
        bluePlayer = getIntent().getStringExtra("bluePlayer");

        List<QuestionModel> redQuestionList = (List<QuestionModel>) getIntent().getSerializableExtra("redQuestionList");
        List<QuestionModel> blueQuestionList = (List<QuestionModel>) getIntent().getSerializableExtra("blueQuestionList");

        String winner, correctAnswers ="Correct answers: \n \n";

        summaryTextView.setText(redPlayer+": "+redPlayerPoints+" points \n"
                +bluePlayer+": "+bluePlayerPoints+" points \n");

        for(int i=0; i<redQuestionList.size(); i++)
        {
            correctAnswers += (i+1)+". "+redQuestionList.get(i).getCorrect_answer()+"\n";
        }
        correctAnswersTextView.setText(correctAnswers);

        restartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                restartGame();
                finish();
            }
        });
    }

    void restartGame(){
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("redPlayer",redPlayer );
        intent.putExtra("bluePlayer", bluePlayer);
        startActivity(intent);
    }
}
