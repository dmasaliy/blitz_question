package com.game.blitz_question;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class GameActivity extends AppCompatActivity {

    TextView questionText;

    Button optionOne, optionTwo, optionThree, optionFour;

    ArrayList<ArrayList<String>> blitz_question = new ArrayList<>();

    String data[][] = {
            {"How many seconds per hour?", "2800", "3600", "2600", "4800"},
            {"How many days in a year?", "400", "365", "411", "327"},
            {"What is Darth Vader's real name?", "Luke Skywalker", "Anakin Skywalker", "Ben Skywalker", "Cade Skywalker"},
            {"How many liters of water does a camel drink?", "110", "114", "60", "0"},
            {"What is the height of the highest mountain", "8611", "8848", "8586", "8516"},
    };

    private String rightAnswer;

    private int rightQuizCount = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        optionOne = findViewById(R.id.optionOneButton);
        optionTwo = findViewById(R.id.optionTwoButton);
        optionThree = findViewById(R.id.optionThreeButton);
        optionFour = findViewById(R.id.optionFourButton);

        questionText = findViewById(R.id.textViewMovie);

        for (int i = 0; i < data.length; i++) {

            ArrayList<String> array = new ArrayList<>();

            array.add(data[i][0]);
            array.add(data[i][1]);
            array.add(data[i][2]);
            array.add(data[i][3]);
            array.add(data[i][4]);

            blitz_question.add(array);
        }

        nextQuestion();
    }

    void nextQuestion() {
        Random random = new Random();
        int randomNum = random.nextInt(blitz_question.size());

        ArrayList<String> quiz = blitz_question.get(randomNum);

        questionText.setText(quiz.get(0));
        rightAnswer = quiz.get(2);

        quiz.remove(0);
        Collections.shuffle(quiz);

        optionOne.setText(quiz.get(0));
        optionTwo.setText(quiz.get(1));
        optionThree.setText(quiz.get(2));
        optionFour.setText(quiz.get(3));

        blitz_question.remove(randomNum);
    }


    public void checkAnswer(View view) {
        Button answerButton = findViewById(view.getId());
        String buttonText = answerButton.getText().toString();

        if (rightQuizCount == 5) {
            youWin();
        } else if (buttonText.equals(rightAnswer)) {
            rightQuizCount++;
            nextQuestion();
        } else {
            youLost();
        }
    }

    public void youLost() {
        Intent intent = new Intent(GameActivity.this, GameOverActivity.class);
        startActivity(intent);
    }

    public void youWin() {
        Intent intent = new Intent(GameActivity.this, WinActivity.class);
        startActivity(intent);
    }
}