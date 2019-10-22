package com.example.passport;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class userdetails extends AppCompatActivity {
    ImageView imageView;
    TextView psno,nme,dobb,addr,aadhr;
    Button btn_qr;

    String passno,name,dob,address,aadharno,casestatus,accountstatus,image;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userdetails);
        imageView=findViewById(R.id.imageView);
        psno=findViewById(R.id.passno);
        nme=findViewById(R.id.name);
        dobb=findViewById(R.id.dob);
        addr=findViewById(R.id.address);
        aadhr=findViewById(R.id.aadharno);
        btn_qr=findViewById(R.id.qr);
        qr q=new qr();
        q.intent=getIntent();
        passno=q.intent.getStringExtra("");
        psno.setText(passno);
        nme.setText(name);
        dobb.setText(dob);
        addr.setText(address);
        aadhr.setText(aadharno);





        btn_qr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
}
