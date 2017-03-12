package com.ara.eventboard;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignUp extends AppCompatActivity{
    EditText password;
    AutoCompleteTextView email;
    Button signup;
    TextView login;
    ProgressBar pbar;
    private FirebaseAuth mauth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        password=(EditText)findViewById(R.id.password);
        email=(AutoCompleteTextView)findViewById(R.id.email);
        signup=(Button)findViewById(R.id.bSignUp);
        pbar=(ProgressBar)findViewById(R.id.pbar);
        login=(TextView)findViewById(R.id.tLogin);
        mauth=FirebaseAuth.getInstance();
        signup.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String em=email.getText().toString().trim();
                final String pw=password.getText().toString().trim();

                if(pw.isEmpty())
                {
                    Toast.makeText(SignUp.this,"Password cannot be left blank",Toast.LENGTH_SHORT).show();
                }
                if(pw.length()<6) {
                    Toast.makeText(SignUp.this, "The Password Too Short", Toast.LENGTH_SHORT).show();
                    password.setText("");
                }


                    pbar.setVisibility(View.VISIBLE);
                mauth.createUserWithEmailAndPassword(em,pw)
                        .addOnCompleteListener(SignUp.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {

                                pbar.setVisibility(View.GONE);
                                if(!task.isSuccessful())
                                {
                                    Toast.makeText(SignUp.this,"Registration Failed",Toast.LENGTH_SHORT).show();
                                }
                                else{
                                    Toast.makeText(SignUp.this,"Registration Successful",Toast.LENGTH_SHORT).show();
                                   startActivity(new Intent(SignUp.this,Login.class));
                                   finish();
                                }
                            }
                        });

            }
        });
        login.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignUp.this,Login.class));
            }
        });
    }
}
