package com.example.lukecaughell.dankbox;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignupActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private static final String TAG = "EmailPassword";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
    }


    public void onCreateButtonClick (View view) {
        EditText pass_text = (EditText) findViewById(R.id.pass_text);
        EditText email_address_text = (EditText) findViewById(R.id.email_address_text);

        String password = pass_text.getText().toString();
        String emailAddress = email_address_text.getText().toString();

        createUser(emailAddress,password);
    }

    public void onBackButtonClick (View view) {
        moveToLoginPage();
    }

    public void createUser(String email, String password) {
        mAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Log.d(TAG,"createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            Toast.makeText(SignupActivity.this,user.getEmail(),Toast.LENGTH_LONG).show();
                        }
                        else {
                            Log.w(TAG, "createUserWithEmail:failure",task.getException());
                            Toast.makeText(SignupActivity.this,"Failed",Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }

    public void moveToLoginPage() {
        Intent intent = new Intent(this,LoginActivity.class);
        startActivity(intent);
    }
}
