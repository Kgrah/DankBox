package com.example.lukecaughell.dankbox;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.lukecaughell.dankbox.Classes.Router;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener, OnClickListener{

    private SignInButton googleButton;
    private Button normalSignInButton;
    private GoogleSignInOptions signInOptions;
    private GoogleApiClient googleApiClient;
    //Arbitrary number
    private static int RC_SIGN_IN = 100;
    private FirebaseAuth mAuth;
    private static final String TAG = "EmailPassword";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        googleButton = (SignInButton) findViewById(R.id.sign_in_button);
        googleButton.setOnClickListener(this);

        mAuth = FirebaseAuth.getInstance();

        normalSignInButton = (Button) findViewById(R.id.normal_sign_in_button);
        normalSignInButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                fireBaseUserSignin();
            }
        });

        // Configure sign-in to request the user's ID, email address, and basic
        // profile. ID and basic profile are included in DEFAULT_SIGN_IN.

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();


        // Build a GoogleApiClient with access to the Google Sign-In API and the
        // options specified by gso.

        googleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this , this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();

        //TODO:: Attempt login if an account is already connected

        //signOut();
        //signIn();


        Button signupButton = (Button) findViewById(R.id.sign_up_button);
        signupButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                moveToSignupActivity();
            }
        });
    }
    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.sign_in_button:
                signIn();
                break;
            case R.id.buttonDev:
                finishLogin();
                break;
            default:

                break;
        }
    }
    private void signIn(){
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(googleApiClient);
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }
    private void signOut() {
        if (googleApiClient.isConnected()){
            Auth.GoogleSignInApi.signOut(googleApiClient).setResultCallback(new ResultCallback<Status>() {
                @Override
                public void onResult(Status status) {
                    Toast.makeText(LoginActivity.this, "Signed Out", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleSignInResult(result);
        }
    }
    private void handleSignInResult(GoogleSignInResult result) {
        Log.d("Login","handleSignInResult:" + result.isSuccess());

        if (result.isSuccess()) {
            // Signed in successfully, show authenticated UI.
            GoogleSignInAccount acct = result.getSignInAccount();
            Toast.makeText(this, acct.getEmail() + " signed in.", Toast.LENGTH_SHORT).show();

            finishLogin();
            //mStatusTextView.setText(getString(R.string.signed_in_fmt, acct.getDisplayName()));
            //updateUI(true);
        } else {
            // Signed out, show unauthenticated UI.
            //updateUI(false);
        }
    }

    private  void finishLogin(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    public void moveToSignupActivity() {
        Intent intent = new Intent(this,SignupActivity.class);
        startActivity(intent);
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Toast.makeText(this, "Login Failed", Toast.LENGTH_LONG).show();
    }

    public void fireBaseUserSignin() {
        EditText email_text = (EditText) findViewById(R.id.login_email_address_text);
        EditText pass_edit_text = (EditText) findViewById(R.id.login_password_text);

        String emailAddress = email_text.getText().toString();
        String password = pass_edit_text.getText().toString();
        mAuth.signInWithEmailAndPassword(emailAddress,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()) {
                    Log.d(TAG,"signInWithEmail:success");
                    FirebaseUser user = mAuth.getCurrentUser();
                    Toast.makeText(LoginActivity.this,user.getEmail() +" signed in.",Toast.LENGTH_LONG).show();
                    finishLogin();
                }
                else {
                    Log.w(TAG,"signInwithEmail:failure",task.getException());
                    Toast.makeText(LoginActivity.this, "Login failed", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
