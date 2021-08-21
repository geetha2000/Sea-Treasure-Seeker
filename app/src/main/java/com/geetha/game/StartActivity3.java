package com.geetha.game;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

public class StartActivity3 extends AppCompatActivity {

    int highestScore;
    ImageView medal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start3);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        TextView scoreLabel = (TextView) findViewById(R.id.scoreLabel);
        TextView highestScoreLabel = (TextView) findViewById(R.id.highestScoreLabel);
        TextView gamesPlayedLabel = (TextView) findViewById(R.id.gamesPlayedLabel);
        medal = (ImageView) findViewById(R.id.medal);

        int score = getIntent().getIntExtra("SCORE",0);
        scoreLabel.setText(""+score);

        if (score > 200){
            medal.setImageResource(R.drawable.gold);
        }
        else if(score > 20){
            medal.setImageResource(R.drawable.silver);
        }
        else {
            medal.setImageResource(R.drawable.bronze);
        }

        SharedPreferences preferencesScore = getSharedPreferences("HIGHESTSCORE", Context.MODE_PRIVATE);
        highestScore = preferencesScore.getInt("HIGHESTSCORE",0);

        if (score > highestScore){
            highestScoreLabel.setText("High Score:"+ score);

            SharedPreferences.Editor editor = preferencesScore.edit();
            editor.putInt("HIGHESTSCORE",score);
            editor.commit();
        }else{
            highestScoreLabel.setText("High Score:"+highestScore);
        }
        SharedPreferences preferencesGames = getSharedPreferences("GAMES",Context.MODE_PRIVATE);
        int games = preferencesGames.getInt("GAMES",0);

        gamesPlayedLabel.setText("Games Played: "+(games+1));

        SharedPreferences.Editor editor = preferencesGames.edit();
        editor.putInt("GAMES",(games+1));
        editor.commit();
    }

    public void tryAgain(View view){
        startActivity(new Intent(getApplicationContext(),StartActivity.class));
        finish();

    }
    //disable return button
    @Override
    public boolean dispatchKeyEvent(KeyEvent event){

        if(event.getAction() == KeyEvent.ACTION_DOWN){
            switch (event.getKeyCode()){
                case  KeyEvent.KEYCODE_BACK:
                     return true;
            }
        }
        return  super.dispatchKeyEvent(event);
    }



}