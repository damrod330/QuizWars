package com.a16mb.damrod.quizwars;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class MainActivity extends AppCompatActivity {

    @InjectView(R.id.info_text_view)
    TextView textViewInfo;

    String redPlayer, bluePlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.inject(this);

        Intent intent = getIntent();
        redPlayer = intent.getStringExtra("redPlayer");
        bluePlayer = intent.getStringExtra("bluePlayer");

        textViewInfo.setText(redPlayer+"\n"+bluePlayer);


    }
}
