package com.example.passport;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
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
        if (uname.getText().toString().isEmpty() || pswd.getText().toString().isEmpty()) {
            Toast.makeText(login.this, "Empty field exist", Toast.LENGTH_LONG).show();
            if (uname.getText().toString().isEmpty()) {
                uname.setError("Type your username");
            } else {
                pswd.setError("Type your password");
            }
        } else {
            Toast.makeText(login.this, "Login successfull", Toast.LENGTH_LONG).show();
            uname.setText("null");
            pswd.setText("null");
            StringRequest stringRequest = new StringRequest(Request.Method.POST,"https://gressorial-parts.000webhostapp.com/login.php",
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
//If we are getting success from server


                            /*try {
                                JSONArray jsonArray=new JSONArray(response);
                                for(int i=0;i<jsonArray.length();i++){
                                    JSONObject json_obj = jsonArray.getJSONObject(i);
                                    latitude = json_obj.getString("latitude");
                                    longitude=json_obj.getString("longitude");
                                    Toast.makeText(MapsActivity.this,latitude,Toast.LENGTH_SHORT).show();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }*/

                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
//You can handle error here if you want
                        }

                    }){
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String,String> params = new HashMap<>();
//Adding parameters to request
params.put("username",uname.getText().toString());
params.put("pswd",pswd.getText().toString());

//returning parameter
                    return params;
                }
            };

//Adding the string request to the queue
            RequestQueue requestQueue = Volley.newRequestQueue(this);
            requestQueue.add(stringRequest);


        }
    }

}







