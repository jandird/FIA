package com.dalipjandir.fiaandroid;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.Image;
import android.net.Uri;

import java.io.InputStream;

import static android.support.v4.content.ContextCompat.startActivity;

public class ViewController {

    static Uri stream;
    public static void sendUncroppedImage(Uri input){
        stream = input;
    }

    public static Uri getStream(){
        return stream;
    }

    public static void giveImage(Bitmap give) {

    }
}
