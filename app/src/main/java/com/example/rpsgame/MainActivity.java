package com.example.rpsgame;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    //Field to hold the play result text
    TextView playResult;

    //Field to hold random number generator
    Random rand;

    //fields to hold the dice value rock paper scissors  to position
    int position1;
    int position2;

    //ArrayList to hold all rps values in position1, 2
    ArrayList<Integer> rps;

    //ArrayList to hold rps images to position
    ArrayList<ImageView> PositionImageViews;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playRPS(view);
            }
        });

        //Create Greeting
        Toast.makeText(getApplicationContext(), "Welcome to the Rock Paper Scissors Game!",
                Toast.LENGTH_SHORT).show();

        playResult = (TextView) findViewById(R.id.playResult);

        //initialize the random number generator
        rand = new Random();

        //Create arraylist to contain for the rps values
        rps = new ArrayList<Integer>();

        ImageView position1Image = (ImageView) findViewById(R.id.position1Image);
        ImageView position2Image = (ImageView) findViewById(R.id.position2Image);

        PositionImageViews = new ArrayList<ImageView>();
        PositionImageViews.add(position1Image);
        PositionImageViews.add(position2Image);

        //create greeting
        Toast.makeText(getApplicationContext(), "Welcome to the Rock Paper Scissors Game!", Toast.LENGTH_SHORT).show();
    }

    public void playRPS(View v){
        playResult.setText("Clicked!");

        //play game
        position1 = rand.nextInt(3) + 1;
        position2 = rand.nextInt(3) + 1;

        //Set rps values into an ArrayList use clear method to empty the arrayList
        rps.clear();
        rps.add(position1);
        rps.add(position2);

        for (int rpsOfset = 0; rpsOfset < 2; rpsOfset++) {
            String imageName = "rps_" + rps.get(rpsOfset) + ".jpg";

            try {
                InputStream stream = getAssets().open(imageName);
                Drawable d = Drawable.createFromStream(stream, null);
                PositionImageViews.get(rpsOfset).setImageDrawable(d);

            } catch(IOException e) {
                e.printStackTrace();
            }

            String msg;

            if ((position1 == 1 && position2 ==2)||(position1 == 2 &&
                    position2 ==3)||(position1 == 3 && position2 ==1)){
                msg = "Player 1 Won the game!!!";

                //Update the app to display the result msg
                playResult.setText(msg);
            }
            else if((position1 == 1 && position2 ==1)||(position1 == 2 &&
                    position2 ==2)||(position1 == 3 && position2 ==3)){
                msg = "You select same role, You are tie!!!";

                playResult.setText(msg);
            }
            else{
                msg = "Player 2 Won!!!!";

                playResult.setText(msg);
            }
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
