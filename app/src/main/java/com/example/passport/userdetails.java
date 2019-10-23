package com.example.passport;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class userdetails extends AppCompatActivity {
    ImageView imageView;
    TextView psno,nme,dobb,addr,aadhr;
    Button btn_qr;

    String p,n,d,ad,aa,im;
    Intent i;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userdetails);
        imageView=findViewById(R.id.imageView);
        psno=findViewById(R.id.passno);
        nme=findViewById(R.id.name);
        dobb=findViewById(R.id.dobb);
        addr=findViewById(R.id.address);
        aadhr=findViewById(R.id.aadharno);
        btn_qr=findViewById(R.id.qr);

        i=getIntent();

        p= i.getStringExtra("pass");
        n=i.getStringExtra("name");
        d=i.getStringExtra("dob");
        ad=i.getStringExtra("address");
        aa=i.getStringExtra("aadharno");
        im=i.getStringExtra("image");



        psno.setText(p);
        nme.setText(n);
        dobb.setText(d);
        addr.setText(ad);
        aadhr.setText(aa);

        Picasso.with(this).load(im).into(imageView);





        btn_qr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
}
