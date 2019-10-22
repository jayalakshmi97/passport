package com.example.passport;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Display;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.WriterException;

import androidmads.library.qrgenearator.QRGContents;
import androidmads.library.qrgenearator.QRGEncoder;
import androidmads.library.qrgenearator.QRGSaver;


public class qr extends AppCompatActivity {
    String TAG = "GenerateQRCode";
    EditText edtValue;
    ImageView qrImage;
    Button start, save;
    String inputValue;
    String savePath = Environment.getExternalStorageDirectory().getPath() + "/PassportQr/";
    Bitmap bitmap;
    QRGEncoder qrgEncoder;
    String passno,name,dob,address,aadharno,casestatus,accountstatus,image;
     Intent intent;
     TextView passnumber,namee,dobb,addresss,aadharnoo,casestatuss,accountstatuss,img;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr);
        qrImage = (ImageView) findViewById(R.id.QRImage);
        start = (Button) findViewById(R.id.start);
        passnumber = findViewById(R.id.textview_pno);


        intent = getIntent();

        passno = intent.getStringExtra("pass");
        name=intent.getStringExtra("name");
        dob=intent.getStringExtra("dob");
        address=intent.getStringExtra("address");
        aadharno=intent.getStringExtra("aadharno");
        image=intent.getStringExtra("image");


        passnumber.setText(passno);
        namee.setText(name);
        dobb.setText(dob);
        addresss.setText(address);
        aadharnoo.setText(aadharno);
        casestatuss.setText(casestatus);
        accountstatuss.setText(accountstatus);



        WindowManager manager = (WindowManager) getSystemService(WINDOW_SERVICE);
        Display display = manager.getDefaultDisplay();
        Point point = new Point();
        display.getSize(point);
        int width = point.x;
        int height = point.y;
        int smallerDimension = width < height ? width : height;
        smallerDimension = smallerDimension * 3 / 4;

        qrgEncoder = new QRGEncoder(
                passno, null,
                QRGContents.Type.TEXT,
                smallerDimension);
        try {
            bitmap = qrgEncoder.encodeAsBitmap();
            qrImage.setImageBitmap(bitmap);
        } catch (WriterException e) {
            Log.v(TAG, e.toString());
        }
        boolean save;
        String result;
        try {
            save = QRGSaver.save(savePath, passno, bitmap, QRGContents.ImageType.IMAGE_JPEG);
            result = save ? "Passport Downloaded" : "Passport Not Saved";
            Toast.makeText(getApplicationContext(), result, Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
        public boolean onCreateOptionsMenu(Menu menu) {
            MenuInflater inflater = getMenuInflater();
            inflater.inflate(R.menu.user, menu);
            return true;
        }
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.user:
                Intent i=new Intent(qr.this,userdetails.class);
//                i.putExtra("pass");
//                i.putExtra("name");
//                i.putExtra("dob");
//                i.putExtra("address");
//                i.putExtra("aadharno");
//                i.putExtra("image")
//                startActivity(i);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
//respond to menu item selection
    }


    }
