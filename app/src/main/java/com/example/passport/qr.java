package com.example.passport;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.util.Base64;
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

import java.io.ByteArrayOutputStream;
import java.util.HashMap;

import androidmads.library.qrgenearator.QRGContents;
import androidmads.library.qrgenearator.QRGEncoder;
import androidmads.library.qrgenearator.QRGSaver;


public class qr extends AppCompatActivity {
    String TAG = "GenerateQRCode";
    ImageView qrImage;
    String savePath = Environment.getExternalStorageDirectory().getPath() + "/PassportQr/";
    Bitmap bitmap;
    QRGEncoder qrgEncoder;
    String passno, name, dob, address, aadharno, casestatus, accountstatus, image;
    Intent intent;
    TextView passnumber, namee, dobb, addresss, aadharnoo, casestatuss, accountstatuss, img;
    String p, n, d, ad, aa, im;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr);
        qrImage = findViewById(R.id.QRImage);
        passnumber = findViewById(R.id.textview_pno);
        intent = getIntent();
        passno = intent.getStringExtra("pass");
        name = intent.getStringExtra("name");
        dob = intent.getStringExtra("dob");
        address = intent.getStringExtra("address");
        aadharno = intent.getStringExtra("aadharno");
        image = intent.getStringExtra("image");
        passnumber.setText(passno);


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
            uploadImage();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }


    private void uploadImage() {
        class UploadImage extends AsyncTask<Bitmap, Void, String> {

            ProgressDialog loading;
            RequestHandler rh = new RequestHandler();

            @Override

            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(qr.this, "Uploading...", null, true, true);
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

                data.put("qr", uploadImage);
                data.put("no", aadharno);


                String result = rh.sendPostRequest("https://gressorial-parts.000webhostapp.com/qrupload.php", data);

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


    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.user, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.user:
                Intent i = new Intent(qr.this, userdetails.class);
                i.putExtra("pass", passno);
                i.putExtra("name", name);
                i.putExtra("dob", dob);
                i.putExtra("address", address);
                i.putExtra("aadharno", aadharno);
                i.putExtra("image", image);
                startActivity(i);
                break;

            case R.id.logout:
                Intent intent = new Intent(qr.this, MainActivity.class);
                startActivity(intent);

break;
            default:
                return super.onOptionsItemSelected(item);

//respond to menu item selection
        }

        return true;
    }
}