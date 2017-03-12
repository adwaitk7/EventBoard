package com.ara.eventboard;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ForgotPassword extends AppCompatActivity {
    AutoCompleteTextView email;
    TextView back;
    Button rese;
    ProgressBar pbr;
    FirebaseAuth authi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        email=(AutoCompleteTextView)findViewById(R.id.reset);
        rese=(Button)findViewById(R.id.bReset);
        pbr=(ProgressBar)findViewById(R.id.pbr);
        back=(TextView)findViewById(R.id.back);
        authi=FirebaseAuth.getInstance();
        rese.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String remail=email.getText().toString().trim();
                if(TextUtils.isEmpty(remail)){
                    Toast.makeText(ForgotPassword.this,"Enter a registered Email ID",Toast.LENGTH_SHORT).show();
                    return;
                }
                pbr.setVisibility(View.VISIBLE);
                authi.sendPasswordResetEmail(remail)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if(task.isSuccessful()){
                                    Toast.makeText(ForgotPassword.this,"We've sent you an email with instructions to reset your password",Toast.LENGTH_LONG).show();
                                }
                                else{
                                    Toast.makeText(ForgotPassword.this,"Failed to send an email",Toast.LENGTH_SHORT).show();
                                }
                                pbr.setVisibility(View.GONE);
                            }
                        });
            }
        });
        back.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ForgotPassword.this,Login.class));
            }
        });
    }
}
