package com.dalipjandir.fiaandroid;

import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import java.io.FileNotFoundException;
import java.io.InputStream;

public class UploadActivity extends AppCompatActivity {

    public static final int PICK_IMAGE = 1;

    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload);

        context = this;
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PICK_IMAGE) {
            try {
                //InputStream inputStream = context.getContentResolver().openInputStream(data.getData());
                ViewController.setUncroppedImage(data.getData());
                startActivity(new Intent(this, CropActivity.class));
            } catch (Exception e) {e.printStackTrace();}
        }
    }
}
