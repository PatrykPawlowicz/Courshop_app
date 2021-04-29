package com.example.courshop_app.activities;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.courshop_app.R;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.courshop_app.MainActivity;
import com.google.firebase.auth.FirebaseAuth;

public class HomeActivity extends AppCompatActivity {

    ProgressBar progressBar;
    FirebaseAuth auth;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        auth = FirebaseAuth.getInstance();

        //button = findViewById(R.id.button);
        progressBar = findViewById(R.id.progressBar_main);
        progressBar.setVisibility(View.GONE);
        if (auth.getCurrentUser() != null) {
            progressBar.setVisibility(View.VISIBLE);
            startActivity(new Intent(HomeActivity.this, MainActivity.class));
            Toast.makeText(this, "Please wait", Toast.LENGTH_SHORT).show();
            finish();
        }
    }


    public void login(View view) {
        startActivity(new Intent(HomeActivity.this,LoginActivity.class));
    }

    public void Register(View view) {
        startActivity(new Intent(HomeActivity.this, RegisterActivity.class));
    }

}