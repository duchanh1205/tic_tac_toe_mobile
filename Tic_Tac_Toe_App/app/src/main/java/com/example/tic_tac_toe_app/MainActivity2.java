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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity2 extends AppCompatActivity implements View.OnClickListener{

    private AlertDialog alertDialog = null;

    private TextView playerOneScore, playerTwoScore, playerStatus;
    private Button [] buttons = new Button[9];
    private Button resetGame;
    private int playerOneScoreCount, playerTwoScoreCount, rountCount;
    boolean activePlayer;
    boolean gameActive = true;
    ArrayList<String> emptySquares = new ArrayList<>();

    // p1 => 0
    // p2 => 1
    // empty => 2

    int [] gameState = {2,2,2,2,2,2,2,2,2};

    int [][] winningPositions = {
            {0,1,2}, {3,4,5}, {6,7,8}, // rows
            {0,3,6}, {1,4,7}, {2,5,8}, // columns
            {0,4,8}, {2,4,6}           // cross
    };

    public void computer(){

        if (gameActive) {
            int select = emptySquares.size();
            int selected = new Random().nextInt(select);
            String selectedSquare = emptySquares.get(selected);
            switch (selectedSquare) {
                case "0":
                    Button btn_0 = findViewById(R.id.btn_0);
                    btn_0.setText("X");
                    btn_0.setTextColor(Color.parseColor("#FFC34A"));
                    gameState[Integer.parseInt(selectedSquare)] = 0;
                    activePlayer = false;
                    emptySquares.remove(selectedSquare);
                    break;

                case "1":
                    Button btn_1 = findViewById(R.id.btn_1);

                    btn_1.setText("X");
                    btn_1.setTextColor(Color.parseColor("#FFC34A"));
                    gameState[Integer.parseInt(selectedSquare)] = 0;
                    activePlayer = false;
                    emptySquares.remove(selectedSquare);
                    break;

                case "2":
                    Button btn_2 = findViewById(R.id.btn_2);

                    btn_2.setText("X");
                    btn_2.setTextColor(Color.parseColor("#FFC34A"));
                    gameState[Integer.parseInt(selectedSquare)] = 0;
                    activePlayer = false;
                    emptySquares.remove(selectedSquare);
                    break;

                case "3":
                    Button btn_3 = findViewById(R.id.btn_3);

                    btn_3.setText("X");
                    btn_3.setTextColor(Color.parseColor("#FFC34A"));
                    gameState[Integer.parseInt(selectedSquare)] = 0;
                    activePlayer = false;
                    emptySquares.remove(selectedSquare);
                    break;

                case "4":
                    Button btn_4 = findViewById(R.id.btn_4);
                    btn_4.setText("X");
                    btn_4.setTextColor(Color.parseColor("#FFC34A"));
                    gameState[Integer.parseInt(selectedSquare)] = 0;
                    activePlayer = false;
                    emptySquares.remove(selectedSquare);
                    break;

                case "5":
                    Button btn_5 = findViewById(R.id.btn_5);
                    btn_5.setText("X");
                    btn_5.setTextColor(Color.parseColor("#FFC34A"));
                    gameState[Integer.parseInt(selectedSquare)] = 0;
                    activePlayer = false;
                    emptySquares.remove(selectedSquare);
                    break;

                case "6":
                    Button btn_6 = findViewById(R.id.btn_6);
                    btn_6.setText("X");
                    btn_6.setTextColor(Color.parseColor("#FFC34A"));
                    gameState[Integer.parseInt(selectedSquare)] = 0;
                    activePlayer = false;
                    emptySquares.remove(selectedSquare);
                    break;

                case "7":
                    Button btn_7 = findViewById(R.id.btn_7);
                    btn_7.setText("X");
                    btn_7.setTextColor(Color.parseColor("#FFC34A"));
                    gameState[Integer.parseInt(selectedSquare)] = 0;
                    activePlayer = false;
                    emptySquares.remove(selectedSquare);
                    break;

                case "8":
                    Button btn_8 = findViewById(R.id.btn_8);
                    btn_8.setText("X");
                    btn_8.setTextColor(Color.parseColor("#FFC34A"));
                    gameState[Integer.parseInt(selectedSquare)] = 0;
                    activePlayer = false;
                    emptySquares.remove(selectedSquare);
                    break;
            }

            checkWinner();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        emptySquares.add("0");
        emptySquares.add("1");
        emptySquares.add("2");
        emptySquares.add("3");
        emptySquares.add("4");
        emptySquares.add("5");
        emptySquares.add("6");
        emptySquares.add("7");
        emptySquares.add("8");

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
        getMenuInflater().inflate(R.menu.option_menu_main2, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.option_menu_item1:
                startActivity(new Intent(MainActivity2.this, MainActivity.class));
                Toast.makeText(this, "Offline Option", Toast.LENGTH_SHORT).show();
                break;
            case R.id.option_menu_item2:
                startActivity(new Intent(MainActivity2.this, MainActivity3.class));
                Toast.makeText(this, "Online Option", Toast.LENGTH_SHORT).show();
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

        if(activePlayer == true){
            ((Button) view).setText("O");
            ((Button) view).setTextColor(Color.parseColor("#70FFEA"));
            gameState[gameStatePointer] = 0;

//            emptySquares.remove(gameStatePointer);

            gameState[gameStatePointer] = 1;

            checkWinner();

            computer();

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
        }
        else if(rountCount == 9){
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
                gameActive = false;

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
        gameActive = true;
        emptySquares.clear();
        emptySquares.add("0");
        emptySquares.add("1");
        emptySquares.add("2");
        emptySquares.add("3");
        emptySquares.add("4");
        emptySquares.add("5");
        emptySquares.add("6");
        emptySquares.add("7");
        emptySquares.add("8");
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
                            Toast.makeText(MainActivity2.this, "Exit!", Toast.LENGTH_SHORT).show();
                        }
                    })
                    .create();
        }
        alertDialog.show();
    }
}