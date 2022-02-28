package com.voting.app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import org.jetbrains.annotations.NotNull;

import java.util.regex.Pattern;

public class registerusr extends AppCompatActivity implements View.OnClickListener {
    private TextView welcome, register;
    private FirebaseAuth mAuth;
    private EditText edemail, edpassword,edname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_registerusr);

        mAuth = FirebaseAuth.getInstance();

        edemail = findViewById(R.id.email);
        edpassword = findViewById(R.id.password);
        edname = findViewById(R.id.name);

        welcome = findViewById(R.id.welcome);
        welcome.setOnClickListener(this);
        register = findViewById(R.id.register);
        register.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case(R.id.welcome):
                startActivity(new Intent(this, loginmain.class));
                break;
            case(R.id.register):
                registeruser();
        }
    }

    private void registeruser() {
        String email = edemail.getText().toString().trim();
        String name = edname.getText().toString().trim();
        String pass = edpassword.getText().toString().trim();

        if(name.equals("")){
            edname.setError("Name required");
            edname.requestFocus();
            return;
        }
        if(email.equals("")){
            edemail.setError("Email required");
            edemail.requestFocus();
            return;
        }
        if(pass.equals("")){
            edpassword.setError("Password required");
            edpassword.requestFocus();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            edemail.setError("Invalid email");
            edemail.requestFocus();
            return;
        }

        if(pass.length()<6){
            edpassword.setError("Password should be 6 chars minimum");
            edpassword.requestFocus();
            return;
        }

        mAuth.createUserWithEmailAndPassword(email,pass)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull @NotNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            userinfo user = new userinfo(name,email);

                            FirebaseDatabase.getInstance().getReference("Users")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull @NotNull Task<Void> task) {
                                    if(task.isSuccessful()){
                                        Toast.makeText(registerusr.this,"Registered Successfully!",Toast.LENGTH_LONG).show();
                                        // ------------NEW INTENT FOR REDIRECTION-------------------------
                                        startActivity(new Intent(registerusr.this, loginmain.class));
                                    }
                                    else
                                        Toast.makeText(registerusr.this,"Registration Somehow failed",Toast.LENGTH_LONG).show();
                                }
                            });
                        }
                        else {
                            Toast.makeText(registerusr.this,"Registration Failed",Toast.LENGTH_LONG).show();
                        }
                    }
                });

    }
}