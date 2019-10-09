package com.example.passport;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Registrationform extends AppCompatActivity {
    EditText name,dob,address,aadharno,username,pswd;
    Button btn_browse,btn_submit;
    String n,d,ad,aa,u,p;

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
                n=name.getText().toString();
                d=dob.getText().toString();
                ad=address.getText().toString();
                aa=aadharno.getText().toString();
                u=username.getText().toString();
                p=pswd.getText().toString();

                //check empty field

                if(n.isEmpty() || d.isEmpty() || ad.isEmpty() || aa.isEmpty() || u.isEmpty() || p.isEmpty())
                {
                    Toast.makeText(Registrationform.this,"Empty Field exist",Toast.LENGTH_SHORT).show();
                    if(n.isEmpty()){
                        name.setError("Enter name");
                    }else if (d.isEmpty()){
                        dob.setError("Enter dob");
                    }else if (ad.isEmpty()){
                        address.setError("Enter Address");
                    }else if (aa.isEmpty()){
                        aadharno.setError("Enter Aadhar no");
                    }else if (u.isEmpty()){
                        username.setError("Enter username");
                    }else if (p.isEmpty()){
                        pswd.setError("Enter password");
                    }
                }
                else{










                    Toast.makeText(Registrationform.this,"Registration Successful",Toast.LENGTH_SHORT).show();
                    
                    //Move to login page
                    Intent intent=new Intent(Registrationform.this,login.class);
                    startActivity(intent);

                }


            }
        });
    }
}
