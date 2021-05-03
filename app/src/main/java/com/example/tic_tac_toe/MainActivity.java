package com.example.tic_tac_toe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {



    public void startGame(View view) {
        Button button = (Button) view;
        int buttonTag = Integer.parseInt(button.getTag().toString());
        Intent intent = new Intent(this,GameActivity.class);
        intent.putExtra("gameMode",buttonTag);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}