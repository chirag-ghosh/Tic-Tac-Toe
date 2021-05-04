package com.example.tic_tac_toe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class GameActivity extends AppCompatActivity {

    int gameMode;

    int score[] = {0,0};

    int gamesPlayed = 0;

    // Player Details
    // 0 - O
    // 1 - X
    int playerState = 0;

    int winner = 2;

    boolean gameOver = false;

    int tapCount = 0;


    // Grid Details
    // 0 - O
    // 1 - X
    // 2 - Null

    int grid[] = {2, 2, 2, 2, 2, 2, 2, 2, 2};

    int winPositions[][] = {{0,1,2},{3,4,5},{6,7,8},
                            {0,3,6},{1,4,7},{2,5,8},
                            {0,4,8},{2,4,6}};

    public void playGame(View view) {
        ImageView img = (ImageView) view;
        int tappedCell = Integer.parseInt(img.getTag().toString());
        TextView heading = (TextView) findViewById(R.id.textView);
        TextView reset = (TextView) findViewById(R.id.textView3);
        if(grid[tappedCell]==2 && !(gameOver)) {
            tapCount++;
            img.setTranslationY(-1000f);
            if(playerState == 0) {
                playerState = 1;
                grid[tappedCell]=0;
                img.setImageResource(R.drawable.x);
                heading.setText(R.string.p2_turn);
            }
            else {
                playerState = 0;
                grid[tappedCell]=1;
                img.setImageResource(R.drawable.o);
                heading.setText(R.string.p1_turn);
            }
            img.animate().translationYBy(1000f).setDuration(300);
        }

        for(int i = 0 ; i < 8 ; i++) {
            int a0 = grid[winPositions[i][0]];
            int a1 = grid[winPositions[i][1]];
            int a2 = grid[winPositions[i][2]];
            if(a0==a1 && a1==a2 && a1 != 2) {
                winner = a0;
                break;
            }
        }

        if(winner != 2 && !(gameOver)) {
            if(gameMode != 2) {
                Toast.makeText(this, "Player "+(winner+1)+" Wins!", Toast.LENGTH_SHORT).show();
                gameOver = true;
                reset.setVisibility(View.VISIBLE);
            }
            else {
                score[winner]++;
                gamesPlayed++;
                refreshScore();
            }
        }

        if(tapCount==9 && !(gameOver)) {
            if(gameMode != 2) {
                Toast.makeText(this, "Match Draw!", Toast.LENGTH_SHORT).show();
                gameOver = true;
                reset.setVisibility(View.VISIBLE);
            }
            else {
                gamesPlayed++;
                refreshScore();
            }
        }

    }

    public void resetFreePlay(View view) {
        resetGame();
    }

    public void resetGame() {
        TextView reset = (TextView) findViewById(R.id.textView3);
        reset.setVisibility(View.INVISIBLE);
        TextView heading = (TextView) findViewById(R.id.textView);
        heading.setText(R.string.p1_turn);
        for(int i = 0 ; i < 9 ; i++) {
            grid[i] = 2;
        }
        playerState = 0;
        winner = 2;
        gameOver = false;
        tapCount = 0;
        ImageView img = (ImageView) findViewById(R.id.imageView0);
        img.setImageResource(0);
        img = (ImageView) findViewById(R.id.imageView1);
        img.setImageResource(0);
        img = (ImageView) findViewById(R.id.imageView2);
        img.setImageResource(0);
        img = (ImageView) findViewById(R.id.imageView3);
        img.setImageResource(0);
        img = (ImageView) findViewById(R.id.imageView4);
        img.setImageResource(0);
        img = (ImageView) findViewById(R.id.imageView5);
        img.setImageResource(0);
        img = (ImageView) findViewById(R.id.imageView6);
        img.setImageResource(0);
        img = (ImageView) findViewById(R.id.imageView7);
        img.setImageResource(0);
        img = (ImageView) findViewById(R.id.imageView8);
        img.setImageResource(0);

        if(gamesPlayed == 5) {
            TextView p1Score = (TextView) findViewById(R.id.textView5);
            TextView p2Score = (TextView) findViewById(R.id.textView7);
            p1Score.setText(String.format("%d",0));
            p2Score.setText(String.format("%d",0));
            gamesPlayed = 0;
            score[0] = 0 ;
            score[1] = 0;
        }

    }

    public void playTournament() {
        LinearLayout scoreboard = (LinearLayout) findViewById(R.id.linearLayout2);
        scoreboard.setVisibility(View.VISIBLE);
    }

    public void refreshScore() {
        TextView p1Score = (TextView) findViewById(R.id.textView5);
        TextView p2Score = (TextView) findViewById(R.id.textView7);
        p1Score.setText(String.format("%d",score[0]));
        p2Score.setText(String.format("%d",score[1]));
        if(gamesPlayed == 5) {
            gameOver = true;
            if(score[0] > score[1]) {
                Toast.makeText(this, "Player 1 Wins!", Toast.LENGTH_SHORT).show();
            }
            else if(score[0] < score[1]) {
                Toast.makeText(this, "Player 2 Wins!", Toast.LENGTH_SHORT).show();
            }
            else {
                Toast.makeText(this, "It's a Draw!", Toast.LENGTH_SHORT).show();
            }
            TextView reset = (TextView) findViewById(R.id.textView3);
            reset.setVisibility(View.VISIBLE);
        }
        else {
            resetGame();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        gameMode = intent.getIntExtra("gameMode",0);
        setContentView(R.layout.activity_game);
        if(gameMode==2) {
            playTournament();
        }
    }
}