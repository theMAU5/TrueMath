package com.example.android.truemath;

import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.concurrent.ThreadLocalRandom;

public class MainActivity extends AppCompatActivity {
    /** @param score for score
     * @param a as the first number to multiply
     * @param b as the second number to multiply
     */
    int score = 0;
    int a = ThreadLocalRandom.current().nextInt(1, 100 + 1);
    int b = ThreadLocalRandom.current().nextInt(1, 100 + 1);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //score, a and b get saved in case the screen rotates
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("a", a);
        outState.putInt("b", b);
        outState.putInt("score", score);
    }
    //score, a and b get loaded in case the screen rotates
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        a = savedInstanceState.getInt("a");
        b = savedInstanceState.getInt("b");
        score = savedInstanceState.getInt("score");
    }
    //this method gets called when the start button gets clicked
    public void startButton(View v) {
        LinearLayout root = (LinearLayout) this.findViewById(R.id.root);
        LinearLayout rules = (LinearLayout) this.findViewById(R.id.rules);
        //the start screen gets set invisible gone
        rules.setVisibility(View.GONE);
        //the new layout gets created
        final TextView Score = new TextView(this);
        TextView EQ = new TextView(this);
        final TextView Task = new TextView(this);
        final TextView Result = new TextView(this);
        final EditText Answer = new EditText(this);
        Button Assign = new Button(this);
        Assign.setText("Check");
        EQ.setText("=");
        EQ.setTextColor(getResources().getColor(R.color.colorAccent));
        Answer.setHint("type here");
        Score.setTextSize(25);
        Task.setTextSize(50);
        EQ.setTextSize(50);
        Result.setTextSize(100);
        LinearLayout.LayoutParams TaskLP = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        LinearLayout.LayoutParams AnswerLP = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        LinearLayout.LayoutParams AssignLP = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        LinearLayout.LayoutParams EQLP = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        LinearLayout.LayoutParams ResultLP = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        TaskLP.setMargins(0, 100, 0, 0);
        Score.setGravity(Gravity.CENTER_HORIZONTAL);
        TaskLP.gravity = Gravity.CENTER_HORIZONTAL;
        AnswerLP.gravity = Gravity.CENTER_HORIZONTAL;
        AssignLP.gravity = Gravity.CENTER_HORIZONTAL;
        EQLP.gravity = Gravity.CENTER_HORIZONTAL;
        ResultLP.gravity = Gravity.CENTER_HORIZONTAL;
        root.addView(Score);
        root.addView(Task, TaskLP);
        root.addView(EQ, EQLP);
        root.addView(Answer, AnswerLP);
        root.addView(Assign, AssignLP);
        root.addView(Result, ResultLP);
        Answer.setInputType(InputType.TYPE_CLASS_NUMBER);
        //the first arithmetical problem gets composed
        Task.setText("" + a + " * " + b);
        //the intial score gets set
        Score.setText("Score: " + score);

        //the onClickListener for the check solution button
        Assign.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Log.i("MainActivity", "a:" + a + " b:" + b);
                //check if anything is entered
                if (!Answer.getText().toString().trim().isEmpty()) {
                    //case: the users answer is right
                    if (Integer.parseInt(Answer.getText().toString()) == a * b) {
                        Result.setTextColor(getResources().getColor(R.color.colorAccent));
                        Result.setText("TRUE");
                        score = score + 1;
                        Score.setText("Score: " + score);
                        Toast.makeText(getApplicationContext(), "Correct! Your score increased by 1.", Toast.LENGTH_SHORT).show();
                    }
                    // case the users answer is wrong
                    else {
                        Result.setText("FALSE");
                        Result.setTextColor(getResources().getColor(android.R.color.primary_text_dark_nodisable));
                        score = 0;
                        Score.setText("Score: " + score);
                        Toast.makeText(getApplicationContext(), "Wrong! Your score was set to 0. Try again!", Toast.LENGTH_SHORT).show();
                    }
                    //new numbers to multiply get created and displayed
                    a = ThreadLocalRandom.current().nextInt(1, 100 + 1);
                    b = ThreadLocalRandom.current().nextInt(1, 100 + 1);
                    Task.setText("" + a + " * " + b);
                    Answer.setText("");
                }
            }
        });
    }
}