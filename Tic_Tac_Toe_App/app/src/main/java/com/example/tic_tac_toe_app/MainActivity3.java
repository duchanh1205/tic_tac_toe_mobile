package com.example.tic_tac_toe_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity3 extends AppCompatActivity implements View.OnClickListener{

    private AlertDialog alertDialog = null;
    private TextView playerOneScore, playerTwoScore, playerStatus;
    private Button[] buttons = new Button[9];
    private Button resetGame;

    private int playerOneScoreCount, playerTwoScoreCount, rountCount;
    boolean activePlayer;

    // p1 => 0
    // p2 => 1
    // empty => 2

    int [] gameState = {2,2,2,2,2,2,2,2,2};

    int [][] winningPositions = {
            {0,1,2}, {3,4,5}, {6,7,8}, // rows
            {0,3,6}, {1,4,7}, {2,5,8}, // columns
            {0,4,8}, {2,4,6}           // cross
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        playerOneScore = (TextView) findViewById(R.id.playerOneScore);
        playerTwoScore = (TextView) findViewById(R.id.playerTwoScore);
        playerStatus = (TextView) findViewById(R.id.playerStatus);

        resetGame = (Button) findViewById(R.id.resetGame);

        for(int i = 0; i < buttons.length; i++){
            String buttonID = "btn_" + i;
            int resourceID = getResources().getIdentifier(buttonID, "id", getPackageName());
            buttons[i] = (Button) findViewById(resourceID);
            buttons[i].setOnClickListener(this);
        }

        rountCount = 0;
        playerOneScoreCount = 0;
        playerTwoScoreCount = 0;
        activePlayer = true;

    }
    @Override
    public boolean onCreateOptionsMenu(@NonNull Menu menu) {
        getMenuInflater().inflate(R.menu.option_menu_main3, menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.option_menu_item1:
                startActivity(new Intent(MainActivity3.this, MainActivity.class));
                Toast.makeText(this, "Offline Option", Toast.LENGTH_SHORT).show();
                break;
            case R.id.option_menu_item2:
                startActivity(new Intent(MainActivity3.this, MainActivity2.class));
                Toast.makeText(this, "Computer Option", Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View view) {
        if(!((Button)view).getText().toString().equals("")){
            return;
        }
        String buttonID = view.getResources().getResourceEntryName(view.getId()); // btn_2
        int gameStatePointer = Integer.parseInt(buttonID.substring(buttonID.length()-1, buttonID.length())); //2

        if(activePlayer){
            ((Button) view).setText("X");
            ((Button) view).setTextColor(Color.parseColor("#FFC34A"));
            gameState[gameStatePointer] = 0;

        }else{
            ((Button) view).setText("O");
            ((Button) view).setTextColor(Color.parseColor("#70FFEA"));
            gameState[gameStatePointer] = 1;
        }
        rountCount++;

        if(checkWinner()){
            if(activePlayer){
                playerOneScoreCount++;
                updatePlayerScore();
                Toast.makeText(this, "Player One Won!", Toast.LENGTH_SHORT).show();
                playAgain();
            }else{
                playerTwoScoreCount++;
                updatePlayerScore();
                Toast.makeText(this, "Player Two Won!", Toast.LENGTH_SHORT).show();
                playAgain();
            }
        }else if(rountCount == 9){
            playAgain();
            Toast.makeText(this, "No One Won!", Toast.LENGTH_SHORT).show();
        }else {
            activePlayer = !activePlayer;
        }

        if(playerOneScoreCount > playerTwoScoreCount){
            playerStatus.setText("Player One is Winning!");
        }else if(playerTwoScoreCount > playerOneScoreCount){
            playerStatus.setText("Player Two is Winning!");
        }else{
            playerStatus.setText("");
        }
        resetGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playAgain();
                playerOneScoreCount = 0;
                playerTwoScoreCount = 0;
                playerStatus.setText("");
                updatePlayerScore();
            }
        });

    }
    public boolean checkWinner(){
        boolean winnerResult = false;

        for(int [] winningPosion : winningPositions){
            if(gameState[winningPosion[0]] == gameState[winningPosion[1]] &&
                    gameState[winningPosion[1]] == gameState[winningPosion[2]] && gameState[winningPosion[0]] != 2){
                winnerResult = true;
            }
        }
        return winnerResult;
    }
    public void updatePlayerScore(){
        playerOneScore.setText(Integer.toString(playerOneScoreCount));
        playerTwoScore.setText(Integer.toString(playerTwoScoreCount));
    }
    public void playAgain(){
        rountCount = 0;
        activePlayer = true;

        for(int i = 0; i < buttons.length; i++){
            gameState[i] = 2;
            buttons[i].setText("");
        }
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
                            Toast.makeText(MainActivity3.this, "Exit!", Toast.LENGTH_SHORT).show();
                        }
                    })
                    .create();
        }
        alertDialog.show();
    }
}