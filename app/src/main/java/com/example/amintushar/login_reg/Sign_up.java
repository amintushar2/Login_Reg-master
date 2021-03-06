package com.example.amintushar.login_reg;

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


public class Sign_up extends AppCompatActivity {


    Button signup;
    TextView logpage;
    EditText personame, email, password, confirmpassword;
    static  final String MY_PREF ="new pref";

    FirebaseAuth myAppAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        signup = findViewById(R.id.signup2);
        logpage = findViewById(R.id.login);
        personame = findViewById(R.id.editText);
        email = findViewById(R.id.regemail);
        password = findViewById(R.id.pass);
        confirmpassword = findViewById(R.id.compass);
        myAppAuth= FirebaseAuth.getInstance();

        logpage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Sign_up.this, Login_activity.class);
                startActivity(intent);

            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                boolean bol= checkValidation();

                if(bol == false){
                    String emailid = email.getText().toString();
                    String password1 = password.getText().toString();

                   registerUser(emailid,password1);

//                    Toast.makeText(Sign_up.this, "Success Fully Register", Toast.LENGTH_SHORT).show();
//                Intent intent = new Intent(Sign_up.this, Login_activity.class);
//                startActivity(intent);
                    SharedPreferences pref = getSharedPreferences(MY_PREF,0);
                    SharedPreferences.Editor editor= pref.edit();
                    String Email =email.getText().toString();
                    editor.putString("name",Email);
                    String Pass =password.getText().toString();
                    editor.putString("pass",Pass);
                    editor.commit();


                }else if (bol == true) {
                    Toast.makeText(Sign_up.this, "d", Toast.LENGTH_SHORT).show();
                }

            }


        });
    }

    private void registerUser(String emailid, String password1) {

        myAppAuth.createUserWithEmailAndPassword(emailid,password1).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
           if(task.isSuccessful()){
               Toast.makeText(getApplicationContext(), "RegistrationSuccesFull", Toast.LENGTH_SHORT).show();
               Intent intent = new Intent(Sign_up.this, Login_activity.class);
               startActivity(intent);
           }     else{
               Toast.makeText(Sign_up.this, "RegistrationFaild", Toast.LENGTH_SHORT).show();
           }
            }
        });
    }


    Boolean checkValidation() {
        String personname = personame.getText().toString();
        String emailid = email.getText().toString();
        String password1 = password.getText().toString();
        String comPass = confirmpassword.getText().toString();

        Boolean flag = false;
        if (personname.isEmpty() || emailid.isEmpty() || password1.isEmpty() || comPass.isEmpty()) {
            Toast.makeText(this, "Filup Info.", Toast.LENGTH_SHORT).show();
            flag = true;
            personame.setError("Enter Name");
            email.setError("Enter Email");

        } if (!emailid.contains("@gmail.com")) {
            email.setError("Enter valid Id");
            flag = true;
        }
         if (password1.length() < 8) {
            password.setError("At Least 8 Charecter");
            flag = true;

        }  if (!password1.equals(comPass)) {
           flag = true;
           confirmpassword.setError("Please Enter Same Pass");
        }return flag;
    }
}