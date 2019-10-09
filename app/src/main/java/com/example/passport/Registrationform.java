package com.example.passport;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

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
                    }else {
                        pswd.setError("Enter password");
                    }
                }
                else {

                    StringRequest stringRequest = new StringRequest(Request.Method.POST, "https://gressorial-parts.000webhostapp.com/login.php",
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
//If we are getting success from server


                                    Toast.makeText(Registrationform.this, response, Toast.LENGTH_LONG).show();

                                }
                            },
                            new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {
//You can handle error here if you want
                                }

                            }) {
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {
                            Map<String, String> params = new HashMap<>();
//Adding parameters to request
                            params.put("name", n);
                            params.put("dob", d);
                            params.put("address", ad);
                            params.put("aadharno", aa);
                            params.put("username", u);
                            params.put("pswd", p);


//returning parameter
                            return params;
                        }

                    };


                   // Toast.makeText(Registrationform.this, "Registration Successful", Toast.LENGTH_SHORT).show();

                    //Move to login page
                    Intent intent = new Intent(Registrationform.this, login.class);

                    startActivity(intent);
                }



            }
        });
    }
}
