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
import com.google.firebase.auth.FirebaseAuthRecentLoginRequiredException;
import com.google.firebase.auth.FirebaseUser;

import org.w3c.dom.Text;

public class Login extends AppCompatActivity {
    EditText pass;
    AutoCompleteTextView eml;
    TextView fgtp;
    TextView sup;
    Button bLogin;
    ProgressBar pb;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        auth=FirebaseAuth.getInstance();
        if(auth.getCurrentUser()!=null){
            startActivity(new Intent(Login.this,Download.class));
        }
        setContentView(R.layout.activity_login);
        pass=(EditText)findViewById(R.id.pass);
        eml=(AutoCompleteTextView)findViewById(R.id.eml);
        fgtp=(TextView)findViewById(R.id.fgtp);
        sup=(TextView)findViewById(R.id.sup);
        bLogin=(Button)findViewById(R.id.bLogin);
        pb=(ProgressBar)findViewById(R.id.pb);
        sup.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Login.this,SignUp.class);
                startActivity(i);
            }
        });
        bLogin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                String mail=eml.getText().toString().trim();
                final String pswrd=pass.getText().toString().trim();
                if(mail.isEmpty()){
                    Toast.makeText(Login.this, "Enter your Email address", Toast.LENGTH_SHORT).show();
                }
                if(pswrd.isEmpty()||pswrd.length()<6){
                    Toast.makeText(Login.this, "Enter a valid password", Toast.LENGTH_SHORT).show();
                    pass.setText("");
                }

                pb.setVisibility(View.VISIBLE);
                auth.signInWithEmailAndPassword(mail,pswrd)
                        .addOnCompleteListener(Login.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                pb.setVisibility(View.GONE);
                                if(!task.isSuccessful()){
                                    Toast.makeText(Login.this, "Authentication Failed", Toast.LENGTH_SHORT).show();
                                    }
                                 else{
                                    Toast.makeText(Login.this, "Authentication Success", Toast.LENGTH_SHORT).show();
                                    Intent inte=new Intent(Login.this,Download.class);
                                    startActivity(inte);
                                    finish();
                                }
                            }
                        });

            }


        });
        fgtp.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                startActivity(new Intent(Login.this,ForgotPassword.class));
            }
        });
    }
}
