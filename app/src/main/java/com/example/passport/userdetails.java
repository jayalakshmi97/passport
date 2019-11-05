package com.example.passport;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class userdetails extends AppCompatActivity {
    ImageView imageView;
    TextView psno,nme,dobb,addr,aadhr;
   // Button btn_qr;
    String passno, name, dob, address, aadharno, casestatus, accountstatus, image;

    String p,n,d,ad,aa,im;
    Intent i,intent;

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
//        btn_qr=findViewById(R.id.user);


        intent=getIntent();

        passno = intent.getStringExtra("pass");
//        name = intent.getStringExtra("name");
//        dob = intent.getStringExtra("dob");
//        address = intent.getStringExtra("address");
//        aadharno = intent.getStringExtra("aadharno");
//        image = intent.getStringExtra("image");
//
//
//
//
//
//
//        i=getIntent();

        p= intent.getStringExtra("pass");
        n=intent.getStringExtra("name");
        d=intent.getStringExtra("dob");
        ad=intent.getStringExtra("address");
        aa=intent.getStringExtra("aadharno");
        im=intent.getStringExtra("image");


//set text to fields
        psno.setText(p);
        nme.setText(n);
        dobb.setText(d);
        addr.setText(ad);
        aadhr.setText(aa);

        Picasso.with(this).load(im).into(imageView);





//        btn_qr.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//
//            }
//        });
    }



    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.user, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.user:
                Intent i = new Intent(userdetails.this, qr.class);
                i.putExtra("pass",passno);
                startActivity(i);
                break;

            case R.id.logout:
                Intent intent = new Intent(userdetails.this, MainActivity.class);
                startActivity(intent);

                break;
            default:
                return super.onOptionsItemSelected(item);

//respond to menu item selection
        }

        return true;
    }



}
