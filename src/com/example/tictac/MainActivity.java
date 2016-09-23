package com.example.tictac;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {

    /**
     * Called when the activity is first created.
     */

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setBoard();
    }

    int check[][];
    int i,j;
    Button b[][];
    int player=0;
    TextView textView;
    Button newGame;

    // Set up the game board.
    private void setBoard()
    {

        b = new Button[4][4];
        check = new int[4][4];


        textView = (TextView) findViewById(R.id.textView1);

        newGame = (Button) findViewById(R.id.newgame);
        newGame.setOnClickListener (new View.OnClickListener(){

            public void onClick(View v)
            {
                if(newGame.isEnabled())
                {
                    textView.setText("Click button to start!");
                    player=0;
                    setBoard();
                }
            }
        });

        b[1][3] = (Button) findViewById(R.id.button1);
        b[1][2] = (Button) findViewById(R.id.button2);
        b[1][1] = (Button) findViewById(R.id.button3);


        b[2][3] = (Button) findViewById(R.id.button4);
        b[2][2] = (Button) findViewById(R.id.button5);
        b[2][1] = (Button) findViewById(R.id.button6);


        b[3][3] = (Button) findViewById(R.id.button7);
        b[3][2] = (Button) findViewById(R.id.button8);
        b[3][1] = (Button) findViewById(R.id.button9);

        for (i = 1; i <= 3; i++) {
            for (j = 1; j <= 3; j++)
                check[i][j] = 2;
        }

        // add the click listeners for each button

        for (i = 1; i <= 3; i++) {
            for (j = 1; j <= 3; j++) {
                b[i][j].setOnClickListener(new MyClickListener(i, j));
                if (!b[i][j].isEnabled()) {
                    b[i][j].setText("");
                    b[i][j].setEnabled(true);
                }
            }
        }
    }

    class MyClickListener implements View.OnClickListener
    {
        int x;
        int y;


        public MyClickListener(int x, int y)
        {
            this.x = x;
            this.y = y;
        }



        public void onClick(View view)
        {
            if (b[x][y].isEnabled())
            {
                b[x][y].setEnabled(false);
                if (player == 0)
                {
                    b[x][y].setText("X");
                    check[x][y] = 0;
                    player = 1;
                    checkBoard();
                } else
                {
                    b[x][y].setText("O");
                    check[x][y] = 1;
                    player = 0;
                    checkBoard();
                }
            }
        }


  // check the board to see if someone has won


  private boolean checkBoard() {
            boolean gameOver = false;
            if (( check[1][1] == 0 && check[2][2] == 0 &&  check[3][3] == 0)
                    || ( check[1][3] == 0 && check[2][2] == 0 &&  check[3][1] == 0)
                    || ( check[1][2] == 0 &&  check[2][2] == 0 &&  check[3][2] == 0)
                    || ( check[1][3] == 0 &&  check[2][3] == 0 &&  check[3][3] == 0)
                    || ( check[1][1] == 0 &&  check[1][2] == 0 &&  check[1][3] == 0)
                    || ( check[2][1] == 0 &&  check[2][2] == 0 &&  check[2][3] == 0)
                    || ( check[3][1] == 0 &&  check[3][2] == 0 &&  check[3][3] == 0)
                    || ( check[1][1] == 0 &&  check[2][1] == 0 &&  check[3][1] == 0)) {
                textView.setText("Player 1: You win!");
                gameOver = true;
            } else if (( check[1][1] == 1 && check[2][2] == 1 && check[3][3] == 1)
                    || ( check[1][3] == 1 && check[2][2] == 1 && check[3][1] == 1)
                    || ( check[1][2] == 1 && check[2][2] == 1 && check[3][2] == 1)
                    || ( check[1][3] == 1 && check[2][3] == 1 && check[3][3] == 1)
                    || ( check[1][1] == 1 && check[1][2] == 1 && check[1][3] == 1)
                    || ( check[2][1] == 1 && check[2][2] == 1 && check[2][3] == 1)
                    || ( check[3][1] == 1 && check[3][2] == 1 && check[3][3] == 1)
                    || ( check[1][1] == 1 &&  check[2][1] == 1 && check[3][1] == 1)) {
                textView.setText("Player 2: You Win!");
                gameOver = true;
            } else {
                boolean empty = false;
                for (i = 1; i <= 3; i++) {
                    for (j = 1; j <= 3; j++) {
                        if (check[i][j] == 2) {
                            empty = true;
                            break;
                        }
                    }
                }
                if (!empty) {
                    gameOver = true;
                    textView.setText("Game over. It's a draw!");

                }
            }if(gameOver)

                for(i=1;i<=3;i++)
                {
                    for(j=1;j<=3;j++)
                    {
                        b[i][j].setEnabled(false);
                    }
                }

            return gameOver;
        }
    }

}