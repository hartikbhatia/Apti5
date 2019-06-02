package com.example.hp.apti5;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;


public class MainActivity extends AppCompatActivity {
    private Button mchoose1, mchoose2, mchoose3, mchoose4;
    private TextView mQuestion, mScoreView;
    private int mScore = 0;
    private int mQuestionNumber = 0;
    private String mAnswer;

    private Firebase mQuestionRef, mchoice1Ref, mchoice2Ref, mchoice3Ref, mchoice4Ref, mAnswerRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //create notification channel code
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel =
                    new NotificationChannel("MyNotification", "MyNotification", NotificationManager.IMPORTANCE_DEFAULT);

            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
            //

        }
        FirebaseMessaging.getInstance().subscribeToTopic("general")
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        String msg = "successful";
                        if (!task.isSuccessful()) {
                            msg = "Failed";
                        }

                        Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
                    }
                });


        mScoreView = (TextView) findViewById(R.id.score);
        mQuestion = (TextView) findViewById(R.id.question);

        mchoose1 = (Button) findViewById(R.id.choice1);
        mchoose2 = (Button) findViewById(R.id.choice2);
        mchoose3 = (Button) findViewById(R.id.choice3);
        mchoose4 = (Button) findViewById(R.id.choice4);

        updateQuestion();

        //button1
        mchoose1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mchoose1.getText().equals(mAnswer)) {
                    mScore = mScore + 1;
                    updateScore(mScore);
                    updateQuestion();

                } else {
                    updateQuestion();
                }
            }
        });

        //button2
        mchoose2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mchoose2.getText().equals(mAnswer)) {
                    mScore = mScore + 1;
                    updateScore(mScore);
                    updateQuestion();

                } else {
                    updateQuestion();
                }
            }
        });
        //button3
        mchoose3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mchoose3.getText().equals(mAnswer)) {
                    mScore = mScore + 1;
                    updateScore(mScore);
                    updateQuestion();

                } else {
                    updateQuestion();
                }
            }
        });
        //button4
        mchoose4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mchoose4.getText().equals(mAnswer)) {
                    mScore = mScore + 1;
                    updateScore(mScore);
                    updateQuestion();

                } else {
                    updateQuestion();
                }
            }
        });


    }

    public void updateScore(int score) {
        mScoreView.setText("" + mScore);
    }

    public void updateQuestion() {
        mQuestionRef = new Firebase("https://aptitute5.firebaseio.com/" + mQuestionNumber + "/question");
        mQuestionRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String question = dataSnapshot.getValue(String.class);
                mQuestion.setText(question);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

        mchoice1Ref = new Firebase("https://aptitute5.firebaseio.com/" + mQuestionNumber + "/choose1");
        mchoice1Ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String choice = dataSnapshot.getValue(String.class);
                mchoose1.setText(choice);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
        mchoice2Ref = new Firebase("https://aptitute5.firebaseio.com/" + mQuestionNumber + "/choose2");
        mchoice2Ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String choice = dataSnapshot.getValue(String.class);
                mchoose2.setText(choice);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
        mchoice3Ref = new Firebase("https://aptitute5.firebaseio.com/" + mQuestionNumber + "/choose3");
        mchoice3Ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String choice = dataSnapshot.getValue(String.class);
                mchoose3.setText(choice);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
        mchoice4Ref = new Firebase("https://aptitute5.firebaseio.com/" + mQuestionNumber + "/choose4");
        mchoice4Ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String choice = dataSnapshot.getValue(String.class);
                mchoose4.setText(choice);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
        mAnswerRef = new Firebase("https://aptitute5.firebaseio.com/" + mQuestionNumber + "/answer");
        mAnswerRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                mAnswer = dataSnapshot.getValue(String.class);

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
        mQuestionNumber++;
    }

    public void quitapp(View view) {
        Intent i = new Intent(MainActivity.this, first.class);
        startActivity(i);
    }
}
