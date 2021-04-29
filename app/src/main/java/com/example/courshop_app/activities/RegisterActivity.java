package com.example.courshop_app.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.courshop_app.R;
import com.example.courshop_app.models.UserModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity {

    Button signUp;
    EditText name,email,password;
    TextView signIn;
    ProgressBar progressBar;

    FirebaseAuth auth;
    FirebaseDatabase database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();

        progressBar = findViewById(R.id.progressBar_reg);
        progressBar.setVisibility(View.GONE);

        signUp = findViewById(R.id.sign_up_btn);
        name = findViewById(R.id.editText1);
        email = findViewById(R.id.editText2);
        password = findViewById(R.id.editText3);
        signIn = findViewById(R.id.Sign_in_ref);

        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
            }
        });
        signUp.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                createUser();
                progressBar.setVisibility(View.VISIBLE);
            }
        });
    }

    private void createUser() {
        String userName = name.getText().toString();
        String userEmail = email.getText().toString();
        String userPassword = password.getText().toString();

        if(TextUtils.isEmpty(userName)) {
            Toast.makeText(this, "Enter the name to continue", Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(userEmail)) {
            Toast.makeText(this, "Enter the email to continue", Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(userPassword)) {
            Toast.makeText(this, "Enter the password to continue", Toast.LENGTH_SHORT).show();
            return;
        }
        if(userPassword.length()<6) {
            Toast.makeText(this, "Password have min. 6 characters", Toast.LENGTH_SHORT).show();
            return;
        }
        //User

        auth.createUserWithEmailAndPassword(userEmail,userPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){

                    UserModel userModel = new UserModel(userName, userEmail, userPassword);
                    String id = task.getResult().getUser().getUid();
                    database.getReference().child("Users").child(id).setValue(userModel);
                    progressBar.setVisibility(View.GONE);

                    Toast.makeText(RegisterActivity.this, "Hi! " + userName + " Registration Successful", Toast.LENGTH_SHORT ).show();
                }
                else{
                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(RegisterActivity.this, "Error: "+task.getException(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}