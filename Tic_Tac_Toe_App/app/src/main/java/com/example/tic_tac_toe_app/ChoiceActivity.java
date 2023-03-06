package com.example.tic_tac_toe_app;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class ChoiceActivity extends AppCompatActivity {

    private AlertDialog alertDialog = null;

    private Button btnSingle;
    private Button btnAI;
    private Button btnMulti;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choice);
        btnSingle = findViewById(R.id.btnSingle);
        btnAI = findViewById(R.id.btnAI);
        btnMulti = findViewById(R.id.btnMulti);

        btnSingle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ChoiceActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        btnAI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ChoiceActivity.this, MainActivity2.class);
                startActivity(intent);
            }
        });
        btnMulti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ChoiceActivity.this, MainActivity3.class);
                startActivity(intent);
            }
        });
    }
    public void onBackPressed(){

        if(alertDialog == null){
            alertDialog = new AlertDialog.Builder(this)
                    .setTitle("Note!")
                    .setMessage("Want to exit?")
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            finish();
                        }
                    })
                    .setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Toast.makeText(ChoiceActivity.this, "Exit!", Toast.LENGTH_SHORT).show();
                        }
                    })
                    .create();
        }
        alertDialog.show();
    }
}

