package com.example.hp.apti5;

import android.app.Application;

import com.firebase.client.Firebase;
import com.google.firebase.FirebaseApp;

public class quizclass extends Application {

    //this class is use for client basically to use (Firebase)

    @Override
    public void onCreate() {
        super.onCreate();


        Firebase.setAndroidContext(this);

    }
}
