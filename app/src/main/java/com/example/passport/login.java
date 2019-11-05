package com.example.passport;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class login<stringRequest> extends AppCompatActivity {
    TextView username, password;
    EditText uname, pswd;
    Button login;
    String no,status,namee,dobb,addresss,aadharnoo,casestatuss,accountstatuss,img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        username = findViewById(R.id.textview_name);
        password = findViewById(R.id.textview_psw);
        uname = findViewById(R.id.edittext_username);
        pswd = findViewById(R.id.edittext_psw);
        login = findViewById(R.id.btn_login);
        //to check whether password or username field is empty

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //if (uname.getText().toString().isEmpty() || pswd.getText().toString().isEmpty()) {
//                    Toast.makeText(login.this, "Empty field exist", Toast.LENGTH_LONG).show();
//                    if (uname.getText().toString().isEmpty()) {
//                        uname.setError("Type your username");
//                    } else {
//                        pswd.setError("Type your password");
//                    }
//                } else {
            //    Toast.makeText(login.this,"hi",Toast.LENGTH_SHORT).show();
                    StringRequest stringRequest = new StringRequest(Request.Method.POST, "https://gressorial-parts.000webhostapp.com/login.php",
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
//If we are getting success from server


                                //    Toast.makeText(login.this,response,Toast.LENGTH_LONG).show();


       try {


                                JSONArray jsonArray=new JSONArray(response);
                                for(int i=0;i<jsonArray.length();i++){
                                    JSONObject json_obj = jsonArray.getJSONObject(i);
                                    no = json_obj.getString("passportnumber");
                                    status=json_obj.getString("login_status");
                                    namee=json_obj.getString("name");
                                    dobb=json_obj.getString("dob");
                                    addresss=json_obj.getString("address");
                                    aadharnoo=json_obj.getString("aadharno");
                                    casestatuss=json_obj.getString("casestatus");
                                    accountstatuss=json_obj.getString("accountstatus");
                                    img=json_obj.getString("image");



                                // longitude=json_obj.getString("longitude");
                            //        Toast.makeText(login.this,no,Toast.LENGTH_SHORT).show();

                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                                    if (status.equals("1")) {
                                        Intent intent = new Intent(login.this, userdetails.class);
                                         intent.putExtra("pass",no);

                                        intent.putExtra("login",status);

                                        intent.putExtra("name",namee);

                                        intent.putExtra("dob",dobb);

                                        intent.putExtra("address",addresss);

                                        intent.putExtra("aadharno",aadharnoo);

                                        intent.putExtra("image",img);

                                        startActivity(intent);
                                    }
                                    else
                                    {
                                        Toast.makeText(login.this,"Account Blocked",Toast.LENGTH_LONG).show();
                                    }

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
                            params.put("username", uname.getText().toString());
                            params.put("pswd", pswd.getText().toString());

//returning parameter
                            return params;
                        }
                    };

//Adding the string request to the queue
                    RequestQueue requestQueue = Volley.newRequestQueue(login.this);
                    requestQueue.add(stringRequest);

                }

        });

        }
    }








