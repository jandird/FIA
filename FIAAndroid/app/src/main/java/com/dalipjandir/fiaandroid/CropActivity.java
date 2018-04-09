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

        Intent CropIntent = new Intent("com.android.camera.action.CROP");
        CropIntent.setDataAndType(ViewController.getUncroppedImage(),"image/*");

        CropIntent.putExtra("crop","true");
        CropIntent.putExtra("outputX",180);
        CropIntent.putExtra("outputY",180);
        CropIntent.putExtra("scaleUpIfNeeded",true);
        CropIntent.putExtra("return-data",true);
        CropIntent.putExtra(MediaStore.EXTRA_OUTPUT, ViewController.getUncroppedImage());

        startActivityForResult(CropIntent, REQUEST_CROP_ICON);
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
