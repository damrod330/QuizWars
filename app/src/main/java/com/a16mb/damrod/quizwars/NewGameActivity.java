package com.a16mb.damrod.quizwars;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class NewGameActivity extends AppCompatActivity {

    @InjectView(R.id.button_start_game)
    Button buttonStartGame;

    @InjectView(R.id.player_blue_et)
    EditText editTextPlayerBlue;

    @InjectView(R.id.player_red_et)
    EditText editTextPlayerRed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_game);
        ButterKnife.inject(this);

        buttonStartGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startNewGame(editTextPlayerRed.getText().toString(), editTextPlayerBlue.getText().toString());
            }
        });
    }
    void startNewGame(String redPlayer, String bluePlayer){

        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("redPlayer",redPlayer );
        intent.putExtra("bluePlayer", bluePlayer);
        startActivity(intent);

    }
}
