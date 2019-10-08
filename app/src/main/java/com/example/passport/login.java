package com.example.passport;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class login extends AppCompatActivity {
    TextView username,password;
    EditText uname,pswd;
    Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        username=findViewById(R.id.edittext_username);
        password=findViewById(R.id.textview_psw);
        uname=findViewById(R.id.edittext_username);
        pswd=findViewById(R.id.edittext_psw);
        login=findViewById(R.id.btn_login);
        
    }
}
