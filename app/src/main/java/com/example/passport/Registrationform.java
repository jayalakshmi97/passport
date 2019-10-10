package com.example.passport;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Registrationform extends AppCompatActivity {
    EditText name, dob, address, aadharno, username, pswd;
    Button btn_browse, btn_submit;
    String n, d, ad, aa, u, p;
    private Uri filePath;
Bitmap bitmap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrationform);
        name = findViewById(R.id.name);
        dob = findViewById(R.id.dob);
        address = findViewById(R.id.address);
        aadharno = findViewById(R.id.aadharno);
        username = findViewById(R.id.username);
        pswd = findViewById(R.id.pswd);

        btn_browse = findViewById(R.id.btn_browse);
        btn_submit = findViewById(R.id.btn_submit);

        btn_browse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), 1);

            }
        });
        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                n = name.getText().toString();
                d = dob.getText().toString();
                ad = address.getText().toString();
                aa = aadharno.getText().toString();
                u = username.getText().toString();
                p = pswd.getText().toString();
                //check empty field

                if (n.isEmpty() || d.isEmpty() || ad.isEmpty() || aa.isEmpty() || u.isEmpty() || p.isEmpty()) {
                    Toast.makeText(Registrationform.this, "Empty Field exist", Toast.LENGTH_SHORT).show();


                } else {

                    StringRequest stringRequest = new StringRequest(Request.Method.POST, "https://gressorial-parts.000webhostapp.com/registration.php",
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
//If we are getting success from server


                                    Toast.makeText(Registrationform.this, response, Toast.LENGTH_LONG).show();
if(response.contains("success"))
{
    Intent intent = new Intent(Registrationform.this, login.class);

    startActivity(intent);
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
                            params.put("name", n);
                            params.put("dob", d);
                            params.put("address", ad);
                            params.put("aadharno", aa);
                            params.put("username", u);
                            params.put("pswd", p);
                            String uploadImage = getStringImage(bitmap);
                           params.put("image",uploadImage);

//returning parameter
                            return params;
                        }

                    };

//Adding the string request to the queue
                    RequestQueue requestQueue = Volley.newRequestQueue(Registrationform.this);
                    requestQueue.add(stringRequest);
                    // Toast.makeText(Registrationform.this, "Registration Successful", Toast.LENGTH_SHORT).show();

                    //Move to login page

                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == RESULT_OK && data != null && data.getData() != null) {

            filePath = data.getData();
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
               // imageView.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public String getStringImage(Bitmap bmp) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] imageBytes = baos.toByteArray();
        String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        return encodedImage;
    }
   /* private void uploadImage() {
        class UploadImage extends AsyncTask<Bitmap, Void, String> {

            ProgressDialog loading;
            RequestHandler rh = new RequestHandler();

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(Registrationform.this, "Uploading...", null, true, true);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(getApplicationContext(), s, Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(Bitmap... params) {
                Bitmap bitmap = params[0];
                String uploadImage = getStringImage(bitmap);

                HashMap<String, String> data = new HashMap<>();

                data.put("image", uploadImage);
                String result = rh.sendPostRequest("", data);

                return result;
            }
        }

        UploadImage ui = new UploadImage();
        ui.execute(bitmap);
    }*/
    }

