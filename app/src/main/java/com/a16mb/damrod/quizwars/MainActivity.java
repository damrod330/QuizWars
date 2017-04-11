package com.a16mb.damrod.quizwars;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    @InjectView(R.id.info_text_view)
    TextView textViewInfo;

    @InjectView(R.id.questions_list_view)
    ListView questionListView;

    String redPlayer, bluePlayer;

    List<QuestionModel> questionList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.inject(this);

        Intent intent = getIntent();
        redPlayer = intent.getStringExtra("redPlayer");
        bluePlayer = intent.getStringExtra("bluePlayer");

        textViewInfo.setText(redPlayer+"\n"+bluePlayer);
        OkHttpHandler okHttpHandler = new OkHttpHandler();
        okHttpHandler.execute();

    }

    private class OkHttpHandler extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {
            String url = "https://opentdb.com/api.php?amount=14";
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
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Toast.makeText(MainActivity.this, "done", Toast.LENGTH_SHORT).show();
            JsonFormatter jsonFormatter = new JsonFormatter();



            questionList = jsonFormatter.GetQuestionList(s);
            String res ="";
            for(int i=0; i<questionList.size(); i++)
            {

                res += questionList.get(i).getQuestion() + "\n"
                        +questionList.get(i).getCorrect_answer()+"\n"
                +questionList.get(i).getType() + "\n"+"\n";
            }
            //textViewInfo.setText(res);

            //questionListView.setAdapter(new ArrayAdapter<String>());

        }
    }
}
