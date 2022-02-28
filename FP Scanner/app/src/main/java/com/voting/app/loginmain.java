package com.voting.app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import org.jetbrains.annotations.NotNull;

public class loginmain extends AppCompatActivity implements View.OnClickListener {
    private EditText emailid, password;
    private TextView signup;
    private Button login;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginmain);

        signup = findViewById(R.id.signup);
        signup.setOnClickListener(this);

        emailid = findViewById(R.id.emailid);
        password = findViewById(R.id.password);

        login = findViewById(R.id.login);
        login.setOnClickListener(this);

        mAuth = FirebaseAuth.getInstance();

    }

    @Override
    public void onClick(View v) {
    switch (v.getId()){
        case R.id.signup:
            startActivity(new Intent(this,registerusr.class));
            break;
        case(R.id.login):
            userlogin();
            break;
    }
    }

    private void userlogin() {
        Toast.makeText(loginmain.this," Please wait",Toast.LENGTH_SHORT).show();
        String emailID = emailid.getText().toString().trim();
        String passwd = password.getText().toString().trim();

        if(emailID.equals("")){
            emailid.setError("Invalid email");
            emailid.requestFocus();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(emailID).matches()){
            emailid.setError("Invalid email");
            emailid.requestFocus();
            return;
        }
        if(passwd.equals("")){
            password.setError("Invalid Password");
            password.requestFocus();
            return;
        }
        if(passwd.length()<6){
            password.setError("Invalid Password");
            password.requestFocus();
            return;

        }
        mAuth.signInWithEmailAndPassword(emailID,passwd)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull @NotNull Task<AuthResult> task) {
                        if(task.isSuccessful()){

                            //redirecting to home dashboard (homedash)
                            startActivity(new Intent(loginmain.this, homedash.class));


                        }
                        else{
                            Toast.makeText(loginmain.this,"Login Failed",Toast.LENGTH_LONG).show();
                        }
                    }
                });

    }

}