package com.dalipjandir.fiaandroid;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;

public class CropActivity extends AppCompatActivity {

    private static final int REQUEST_CROP_ICON = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crop);

        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(ViewController.getUncroppedImage(),"image/*");

        intent.putExtra("outputX", 550);
        intent.putExtra("outputY", 330);
        intent.putExtra("scaleUpIfNeeded", true);
        intent.putExtra("scale", "true");
        intent.putExtra("return-data", false);
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION | Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, ViewController.getUncroppedImage());

        startActivityForResult(intent, REQUEST_CROP_ICON);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CROP_ICON) {
            try {
                Bundle bundle = data.getExtras();
                Bitmap bitmap = bundle.getParcelable("data");

                ViewController.runFinalImage(getApplicationContext(), bitmap);
            } catch (Exception e) {e.printStackTrace();}
        }
    }
}
