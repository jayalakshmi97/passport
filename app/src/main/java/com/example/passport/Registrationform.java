package com.example.passport;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.Image;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
public class Registrationform extends AppCompatActivity {
    EditText name, dob, address, aadharno, username, pswd;
    Button btn_browse, btn_submit;
    String n, d, ad, aa, u, p;
    ImageView imageView;
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
        imageView=findViewById(R.id.imageView);

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


                }
                else if(!(imageView.getDrawable() == null))
                {
                    uploadImage();
                }
                else
                {
                    Toast.makeText(Registrationform.this,"Select image",Toast.LENGTH_SHORT).show();
                }


                    // Toast.makeText(Registrationform.this, "Registration Successful", Toast.LENGTH_SHORT).show();

                    //Move to login page

               // }
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
               imageView.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    private void uploadImage(){
        class UploadImage extends AsyncTask<Bitmap,Void,String>{

            ProgressDialog loading;
            RequestHandler rh = new RequestHandler();

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(Registrationform.this, "Uploading...", null,true,true);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();

                Toast.makeText(getApplicationContext(),s,Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(Bitmap... params) {
                Bitmap bitmap = params[0];
                String uploadImage = getStringImage(bitmap);

                HashMap<String,String> data = new HashMap<>();

                data.put("image", uploadImage);
                data.put("name",n);
                data.put("dob",d);
                data.put("address",ad);
                data.put("aadharno",aa);
                data.put("username",u);
                data.put("pswd",p);

                String result = rh.sendPostRequest("https://gressorial-parts.000webhostapp.com/registration.php",data);

                return result;
            }
        }

        UploadImage ui = new UploadImage();
        ui.execute(bitmap);
    }

    public String getStringImage(Bitmap bmp) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] imageBytes = baos.toByteArray();
        String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        return encodedImage;
    }

    }

