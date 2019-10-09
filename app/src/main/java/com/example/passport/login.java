package com.example.passport;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.Map;

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
        //to check whether password or username field is empty
        if (uname.getText().toString().isEmpty()||pswd.getText().toString().isEmpty())
        {
            Toast.makeText(login.this,"Empty field exist",Toast.LENGTH_LONG).show();
            if (uname.getText().toString().isEmpty())
            {
                uname.setError("Type your username");
            }
            else
            {
                pswd.setError("Type your password");
            }
        }
        else
        {
            Toast.makeText(login.this,"Login successfull",Toast.LENGTH_LONG).show();
            uname.setText("null");
            pswd.setText("null");
            // Instantiate the RequestQueue.
            RequestQueue queue = Volley.newRequestQueue(this);
            String url ="http://www.google.com";

// Request a string response from the provided URL.
            StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            // Display the first 500 characters of the response string.
                          //  textView.setText("Response is: "+ response.substring(0,500));
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    //textView.setText("That didn't work!");
                }
            });
                @Override
                        protected Map<String>
// Add the request to the RequestQueue.
            queue.add(stringRequest);
        }

    }
}
