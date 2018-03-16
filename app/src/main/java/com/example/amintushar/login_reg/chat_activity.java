package com.example.amintushar.login_reg;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class chat_activity extends AppCompatActivity {
    static  final String MY_PREF ="new pref";
//    TextView txt1,txt2;


    ListView chatlist;
    EditText msg;
    Button  sendButtn;
    String uEmail;
    DatabaseReference ref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_activity);

//        txt1 =findViewById(R.id.txtemail);
//        txt2 = findViewById(R.id.txtpass);
//
//
//
//        SharedPreferences pref2= getSharedPreferences(MY_PREF,0);
//        String Email =pref2.getString("name","Something Is Rong");
//        txt1.setText(Email);
//        SharedPreferences pref3= getSharedPreferences(MY_PREF,0);
//        String Pass =pref3.getString("pass","Something Is Rong");
//        txt2.setText(Pass);


        chatlist = findViewById(R.id.chatList);
        msg = findViewById( R.id.textChat);
        sendButtn=findViewById(R.id.sendbtn);

        Intent intent = getIntent();
        uEmail = intent.getStringExtra("email");

        ref = FirebaseDatabase.getInstance().getReference();

  sendButtn.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
          if (!msg.getText().toString().equals("")){

            MyText myTexts = new MyText(uEmail,msg.getText().toString());
             ref.child("ChatData").push().setValue(myTexts);
          }
      }
  });
  ChatAdapter adapter = new ChatAdapter(this,R.layout.single_row,ref);
chatlist.setAdapter(adapter);

    }
}
