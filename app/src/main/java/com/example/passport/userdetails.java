package com.example.passport;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class userdetails extends AppCompatActivity {
    ImageView imageView;
    TextView psno,nme,dob,addr,aadhr;
    Button btn_qr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userdetails);
        imageView=findViewById(R.id.imageView);
        psno=findViewById(R.id.passno);
        nme=findViewById(R.id.name);
        dob=findViewById(R.id.dob);
        addr=findViewById(R.id.address);
        aadhr=findViewById(R.id.aadharno);
        btn_qr=findViewById(R.id.qr);

        btn_qr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                
            }
        });
    }
}
