package com.devdroid.quiz_application;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView totalQuestion, question;
    Button option_a, option_b, option_c, option_d;
    Button submitBtn;

    int score = 0;
    int totalQ = QuestionAnswer.question.length;
    int currentQIndex = 0;
    String selectAnswer = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        totalQuestion = findViewById(R.id.total_question);
        question = findViewById(R.id.question);
        option_a = findViewById(R.id.option_1);
        option_b = findViewById(R.id.option_2);
        option_c = findViewById(R.id.option_3);
        option_d = findViewById(R.id.option_4);
        submitBtn = findViewById(R.id.submitBtn);

        option_a.setOnClickListener(this);
        option_b.setOnClickListener(this);
        option_c.setOnClickListener(this);
        option_d.setOnClickListener(this);
        submitBtn.setOnClickListener(this);

        totalQuestion.setText("Total Question:- " + totalQ);

        loadNewQuestion();

    }

    private void loadNewQuestion() {
        if (currentQIndex == totalQ) {
            finishQuiz();
            return;
        }

        question.setText(QuestionAnswer.question[currentQIndex]);
        option_a.setText(QuestionAnswer.choice[currentQIndex][0]);
        option_b.setText(QuestionAnswer.choice[currentQIndex][1]);
        option_c.setText(QuestionAnswer.choice[currentQIndex][2]);
        option_d.setText(QuestionAnswer.choice[currentQIndex][3]);
    }

    public void finishQuiz() {
        String passStatus = "";
        if (score > totalQ + 0.60) {
            passStatus = "PASSED!";
        } else {
            passStatus = "FAILED!";
        }
        new AlertDialog.Builder(this)
                .setTitle(passStatus)
                .setMessage("Score is " + score + " out of " + totalQ)
                .setPositiveButton("Restart", (dialogInterface, i) -> restartQuiz())
                .setCancelable(false)
                .show();
    }

    private void restartQuiz() {
        score = 0;
        currentQIndex = 0;
        loadNewQuestion();
    }

    @Override
    public void onClick(View view) {
        option_a.setBackgroundColor(Color.WHITE);
        option_b.setBackgroundColor(Color.WHITE);
        option_c.setBackgroundColor(Color.WHITE);
        option_d.setBackgroundColor(Color.WHITE);

        Button clickedButton = (Button) view;
        if (clickedButton.getId() == R.id.submitBtn) {
            if (selectAnswer.equals(QuestionAnswer.correctAnswer[currentQIndex])) {
                score++;
            }
            currentQIndex++;
            loadNewQuestion();

        } else {
            // user clicked choice
            selectAnswer = clickedButton.getText().toString();
            clickedButton.setBackgroundColor(Color.MAGENTA);

        }
    }
}