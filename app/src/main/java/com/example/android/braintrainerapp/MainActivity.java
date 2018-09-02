package com.example.android.braintrainerapp;

import android.os.CountDownTimer;
import android.os.strictmode.WebViewMethodCalledOnWrongThreadViolation;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button startButton;
    int locationOfCorrectAnswer;
    int score = 0;
    TextView resultTextView;
    int numberOfQuestions = 0;
    TextView scoreTextView;
    TextView sumTextView;
    Button button0;
    Button button1;
    Button button2;
    Button button3;
    TextView timerTextView;
    Button playAgain;
    ConstraintLayout gameLayout;

    ArrayList <Integer> answers = new ArrayList<Integer>();

    public void playAgain(View view) {

        score = 0;
        numberOfQuestions = 0;
        timerTextView.setText("30s");
        scoreTextView.setText(Integer.toString(score) + " / " + Integer.toString(numberOfQuestions));
        newQuestion();
        playAgain.setVisibility(View.INVISIBLE);
        resultTextView.setText("");
        button0.setEnabled(true);
        button1.setEnabled(true);
        button2 .setEnabled(true);
        button3.setEnabled(true);

        new CountDownTimer(30100, 1000 ) {


            @Override
            public void onTick(long l) {

                timerTextView.setText(String.valueOf( l / 1000) + "s");

            }

            @Override
            public void onFinish() {

                resultTextView.setText("Times up");
                playAgain.setVisibility(View.VISIBLE);
                button0.setEnabled(false);
                button1.setEnabled(false);
                button2 .setEnabled(false);
                button3.setEnabled(false);



            }
        }.start();


    }

    public void chooseAnswer (View view) {

        view.getTag().toString();

        if (Integer.toString(locationOfCorrectAnswer).equals(view.getTag().toString())) {

            resultTextView.setText("Correct");
            score++;

        }else   {
            resultTextView.setText("Incorrect");

        }

        numberOfQuestions++;
        scoreTextView.setText(Integer.toString(score) + " / " + Integer.toString(numberOfQuestions));
        newQuestion();

    }

    public void start (View view) {

        startButton.setVisibility(view.INVISIBLE);
        playAgain(findViewById(R.id.replayButton));
        gameLayout.setVisibility(View.VISIBLE);

    }

    public void  newQuestion () {
        Random rand = new Random();

        int a = rand.nextInt(21);
        int b = rand.nextInt(21);

        sumTextView.setText(Integer.toString(a) + " + " + Integer.toString(b));

        locationOfCorrectAnswer = rand.nextInt(4);

        answers.clear();

        for (int i = 0; i<4; i++) {
            if (i == locationOfCorrectAnswer) {
                answers.add(a + b);
            } else {
                int wrongAnswer = rand.nextInt(41);

                while (wrongAnswer == a + b) {

                    wrongAnswer = rand.nextInt(41);
                }

                answers.add(wrongAnswer);
            }
        }

        button0.setText(Integer.toString(answers.get(0)));
        button1.setText(Integer.toString(answers.get(1)));
        button2.setText(Integer.toString(answers.get(2)));
        button3.setText(Integer.toString(answers.get(3)));


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sumTextView = findViewById(R.id.sumTextView);
        button0 = findViewById(R.id.button0);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        resultTextView = findViewById(R.id.resultTextView);
        scoreTextView = findViewById(R.id.scoreTextView);
        timerTextView = findViewById(R.id.timerTextView);
        playAgain = findViewById(R.id.replayButton);
        startButton = findViewById(R.id.startButton);
        gameLayout = findViewById(R.id.gameLayout);



        startButton.setVisibility(View.VISIBLE);
        gameLayout.setVisibility(View.INVISIBLE);




    }
}
