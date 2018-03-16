package com.example.amintushar.login_reg;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login_activity extends AppCompatActivity {
    Button log_in;
    TextView sign_up;
EditText email3 , pass3;
FirebaseAuth loginAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_activity);
        log_in=findViewById(R.id.login1);
        sign_up=findViewById(R.id.sigup1);
        email3=findViewById(R.id.email);
        pass3=findViewById(R.id.pass);
        loginAuth= FirebaseAuth.getInstance();

        log_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });


        sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(Login_activity.this,Sign_up.class);
                startActivity(intent1);

            }
        });
        log_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String emailText = email3.getText().toString();
                String passwordd = pass3.getText().toString();
                
                if (emailText.isEmpty()||passwordd.isEmpty()) {
                    
                    email3.setError("Fill Id");
                    pass3.setError("Fill valid Pass");
                    Toast.makeText(Login_activity.this, "Please enter all of informatin", Toast.LENGTH_SHORT).show();
                }  else {
                    loginUser(emailText,passwordd);
                                    }
            }
        });



    }

    private void loginUser(final String emailText, final String passwordd) {
        loginAuth.signInWithEmailAndPassword(emailText,passwordd).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
           if(task.isSuccessful()){
               Toast.makeText(Login_activity.this, "Log In SuccesFully", Toast.LENGTH_SHORT).show();

               Intent intent = new Intent(Login_activity.this, chat_activity.class);
               intent.putExtra("email",email3.getText().toString());
               startActivity(intent);
           }    else {
               Toast.makeText(Login_activity.this, "Not Success ", Toast.LENGTH_SHORT).show();
           }
            }
        });
        
        
    }
}
