package android.com.connect3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    //Yellow = 0 | Red = 1
    int activePlayer = 0;
    int[] gameState = {2,2,2,2,2,2,2,2,2};
    //Initially 2 -- unplayed state
    boolean gameActive = true;
    int[][] winningPositions = {
                                {0,1,2},
                                {3,4,5},
                                {6,7,8},
                                {0,3,6},
                                {1,4,7},
                                {2,5,8},
                                {0,4,8},
                                {2,4,6},
                                };
    //2D Array 7*3 matrix

    public void dropin (View view) {
        //user taps on center R1C1
        ImageView counter = (ImageView) view;
        //When the user click on empty image view (Taping)
        //No need of findViewById bcz that's the view on which we taped on and which calls the dropin func
        System.out.println(counter.getTag().toString());
        //Print in console which position is tapped.
        int tappedCounter = Integer.parseInt(counter.getTag().toString());
        //tappedCounter var stores value of tag of tapped position
        if (gameState[tappedCounter] == 2 && gameActive) {
            //No need of else
            //a[i] == get value at that index
            gameState[tappedCounter] = activePlayer;
            //Change the unplayed state (particular indexed value change) to played by setting it to 0 or 1 by assigning activePlayer var

            counter.setTranslationY(-1000f);
            //Move img to the top of the screen -1000px

            //Condition to check the activePlayers turn
            if (activePlayer == 0) {
                counter.setImageResource(R.drawable.yellow);
                //set Image to image view
                activePlayer = 1;
            } else {
                counter.setImageResource(R.drawable.red);
                activePlayer = 0;
            }


            counter.animate().translationYBy(1000f).rotation(360).setDuration(300);
            //get that image that is set on to the screen at clicked position
            for (int[] winningPosition : winningPositions) {
                if (gameState[winningPosition[0]] == gameState[winningPosition[1]] &&
                        gameState[winningPosition[1]] == gameState[winningPosition[2]] &&
                        gameState[winningPosition[0]] != 2) {
                    //winningPosition[0] == index[0] elements in W=winningPositions Array i.e 0,3,6,0,1,2,0
                    // Like the same index[1] are 1 index numbers in that array.
                    //gameState[winningPosition[0]];gameState[winningPosition[1];gameState[winningPosition[2] -- 2,2,2,2,2,2,2,2,2
                    // if if-loop is true it means someone has Won
                    String winner = "Captain";
                    {
                        System.out.println(gameState[winningPosition[0]]);
                        //Someone has Won
                        gameActive = false;
                        if(gameState[winningPosition[0]] == 0)
                        {
                            winner = "Spider Guy";
                        }
                        TextView winnermsg = (TextView) findViewById(R.id.winnerMessage);
                        winnermsg.setText(" "+winner+" " + " has Won! ");
                        LinearLayout linear = (LinearLayout) findViewById(R.id.PlayAgainLayout);
                        linear.setVisibility(View.VISIBLE);

                    }
                }
                else
                {
                    //If no one Won
                    boolean GameIsOver = true;
                    for(int CounterState : gameState)
                    {
                        if(CounterState==2)
                            GameIsOver = false;
                        //Check the gameState i.e Un-played  if any one of it is 2 that means moves are left so not draw
                        // the GameIsOver - false

;                    }
                    if(GameIsOver)
                    {
                        //Display Draw Message if Game Over -- Draw
                        TextView winnermsg = (TextView) findViewById(R.id.winnerMessage);
                        winnermsg.setText(" It's a Draw! ");
                        LinearLayout linear = (LinearLayout) findViewById(R.id.PlayAgainLayout);
                        linear.setVisibility(View.VISIBLE);
                    }
                }
            }
        }
    }
    public void playAgain(View view)
    {
        gameActive = true;
        LinearLayout linear = (LinearLayout) findViewById(R.id.PlayAgainLayout);
        linear.setVisibility(View.INVISIBLE);
        //Invisible the Winner Layout

        activePlayer = 0;
        for(int i = 0;i<gameState.length;i++)
        {
            gameState[i] =2;
            //loop that resets the array to 2 i.e un-played state
        }

        GridLayout grid = (GridLayout) findViewById(R.id.gridLayout);
        for(int i =0;i<grid.getChildCount();i++)
        {
            ((ImageView) grid.getChildAt(i)).setImageResource(0);
            //loop that fetch each child ImageView and set it to 0 back to initial state
        }

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
