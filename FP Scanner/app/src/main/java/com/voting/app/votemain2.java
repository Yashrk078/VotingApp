package com.voting.app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.biometric.BiometricPrompt;
import androidx.core.content.ContextCompat;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.concurrent.Executor;

public class votemain2 extends AppCompatActivity implements View.OnClickListener {
    private RadioGroup RG;
    private RadioButton RB;
    public Button idol;
    public TextView declare;

    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_votemain2);

        RG = findViewById(R.id.idolgrp);
         idol = findViewById(R.id.idolvote);
        idol.setOnClickListener(this);

        declare = findViewById(R.id.declare);
    }

    @Override
    public void onClick(View v) {
        int radioID = RG.getCheckedRadioButtonId();
        RB = findViewById(radioID);
        //Toast.makeText(votemain2.this,"Voted for :"+RB.getText(),Toast.LENGTH_LONG).show();
        BiometricPrompt.PromptInfo promptInfo = new BiometricPrompt.PromptInfo.Builder()
                .setTitle("Verification")
                .setDescription("Place your Finger on the Fingerprint scanner")
                .setNegativeButtonText("Cancel")
                .build();

        getprompt().authenticate(promptInfo);
    }
    private void notifyerr(String message){
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
    }
    private BiometricPrompt getprompt(){
        Executor executor = ContextCompat.getMainExecutor(this);
        BiometricPrompt.AuthenticationCallback callback = new BiometricPrompt.AuthenticationCallback() {
            @Override
            public void onAuthenticationError(int errorCode, @NonNull @NotNull CharSequence errString) {
                super.onAuthenticationError(errorCode, errString);
                notifyerr(errString.toString());
            }

            @Override
            public void onAuthenticationSucceeded(@NonNull @NotNull BiometricPrompt.AuthenticationResult result) {
                super.onAuthenticationSucceeded(result);

                HashMap<String, String> vote = new HashMap<>();
                vote.put("Voter",user.getUid());
                vote.put("Candidate",RB.getText().toString());
                vote.put("Category","Head of Marketing");
                vote.put("email",user.getEmail());
                idol.setEnabled(false);
                db.collection(user.getEmail()).add(vote);

                Toast.makeText(votemain2.this,"Voted for :"+RB.getText(),Toast.LENGTH_LONG).show();
                declare.setText("You can now Exit this Voting Menu \uD83D\uDE0A");
            }

            @Override
            public void onAuthenticationFailed() {
                super.onAuthenticationFailed();
                notifyerr("Failed to Authenticate");
            }
        };
        BiometricPrompt biometricPrompt = new BiometricPrompt(this,executor,callback);
        return biometricPrompt;
    }
}