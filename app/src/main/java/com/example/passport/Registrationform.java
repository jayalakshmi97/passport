package com.example.passport;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Registrationform extends AppCompatActivity {
    EditText name,dob,address,aadharno,username,pswd;
    Button btn_browse,btn_submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrationform);
        name=findViewById(R.id.name);
        dob=findViewById(R.id.dob);
        address=findViewById(R.id.address);
        aadharno=findViewById(R.id.aadharno);
        username=findViewById(R.id.username);
        pswd=findViewById(R.id.pswd);

        btn_browse=findViewById(R.id.btn_browse);
        btn_submit=findViewById(R.id.btn_submit);

        btn_browse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                


            }
        });

        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,login.class);
                startActivity(intent);
            }
        });
    }
}
