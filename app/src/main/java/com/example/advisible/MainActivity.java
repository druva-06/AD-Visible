package com.example.advisible;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();

        getWindow().setStatusBarColor(getResources().getColor(R.color.light_blue,null));

        /* Main Activity -> Login Activity */
        findViewById(R.id.login_btn).setOnClickListener(v ->
                startActivity(new Intent(MainActivity.this,LoginActivity.class)));

        /* Main Activity -> Register Activity */
        findViewById(R.id.sign_up_txt).setOnClickListener(v ->
                startActivity(new Intent(MainActivity.this,RegisterActivity.class)));
    }

    @Override
    protected void onStart() {
        super.onStart();
        if(mAuth.getCurrentUser()!=null){
            startActivity(new Intent(MainActivity.this,HomeActivity.class));
            finish();
        }
    }
}