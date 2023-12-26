package com.n22.infisecure;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.n22.infisecure.constants.AppConstants;

public class UserSignUpActivity extends AppCompatActivity {

    Button fbButton;

    private FirebaseAuth mAuth;

    private CallbackManager mCallbackManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_sign_up);
        mCallbackManager = CallbackManager.Factory.create();
        LoginButton fbButton = findViewById(R.id.fb_login_button);
         mAuth = FirebaseAuth.getInstance();
        handleFacebookSignUpProcess(fbButton);

    }

    private void handleFacebookSignUpProcess(LoginButton fbButton) {
        fbButton.setPermissions(AppConstants.EMAIL, AppConstants.PUBLIC_PROFILE);
        // Callback registration
        fbButton.registerCallback(mCallbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                Log.d(AppConstants.FACEBOOK, "facebook:onSuccess:" + loginResult);
                handleFacebookAccessToken(loginResult.getAccessToken());
            }

            @Override
            public void onCancel() {
                Log.d(AppConstants.FACEBOOK, "facebook:onCancel");
            }

            @Override
            public void onError(@NonNull FacebookException error) {
                Log.d(AppConstants.FACEBOOK, "facebook:onError", error);
            }


        });
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null)
            updateUI(currentUser);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Pass the activity result back to the Facebook SDK
        mCallbackManager.onActivityResult(requestCode, resultCode, data);

    }

    private void handleFacebookAccessToken(AccessToken token) {
        Log.d(AppConstants.FACEBOOK, "handleFacebookAccessToken:" + token);

        AuthCredential credential = FacebookAuthProvider.getCredential(token.getToken());
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(AppConstants.FACEBOOK, "signInWithCredential:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            Toast.makeText(UserSignUpActivity.this, "Authentication success.",
                                    Toast.LENGTH_SHORT).show();
                            updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(AppConstants.FACEBOOK, "signInWithCredential:failure", task.getException());
                            Toast.makeText(UserSignUpActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            updateUI(null);
                        }
                    }
                });
    }
    private void updateUI(FirebaseUser user) {
            String name = user.getDisplayName();
            String email = user.getEmail();
            Uri photoUri =user.getPhotoUrl();
            String phoneNumber = user.getPhoneNumber();
        Toast.makeText(UserSignUpActivity.this, name + "\n"+ email+ "\n"+ phoneNumber+ "\n"+ photoUri,
                Toast.LENGTH_LONG).show();
    }
}