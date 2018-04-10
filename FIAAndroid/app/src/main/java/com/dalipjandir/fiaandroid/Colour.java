package com.dalipjandir.fiaandroid;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import org.opencv.android.OpenCVLoader;
import org.opencv.android.Utils;
import org.opencv.core.*;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import java.io.InputStream;
import java.lang.reflect.Array;
import java.util.*;

public class Colour {

    static {
        if (!OpenCVLoader.initDebug()) {
            // Handle initialization error
        }
    }

    static MatOfInt histSize = new MatOfInt(256);
    static final MatOfFloat histRange = new MatOfFloat(0f, 256f);
    static Size sz = new Size (550, 330);

    public static ArrayList<Flags> getImage (Context context, Bitmap bitmap){
        Mat image = new Mat(bitmap.getHeight(),bitmap.getWidth(), CvType.CV_8UC1);
        Utils.bitmapToMat(bitmap, image);


        Imgproc.resize(image, image, sz);

        Imgproc.cvtColor(image, image, Imgproc.COLOR_RGB2HSV);

        List<Mat> hsv_planes1 = new ArrayList<Mat>();
        Core.split(image, hsv_planes1);

        Mat bHist = new Mat();
        Mat gHist = new Mat();
        Mat rHist = new Mat();

        Imgproc.calcHist(Arrays.asList(hsv_planes1.get(0)), new MatOfInt(), new Mat(), bHist, histSize, histRange, true);
        Core.normalize(bHist, bHist, 0, 3, Core.NORM_MINMAX, -1, new Mat());

        Imgproc.calcHist(Arrays.asList(hsv_planes1.get(1)), new MatOfInt(), new Mat(), gHist, histSize, histRange, true);
        Core.normalize(gHist, gHist, 0, 3, Core.NORM_MINMAX, -1, new Mat());

        Imgproc.calcHist(Arrays.asList(hsv_planes1.get(2)), new MatOfInt(), new Mat(), rHist, histSize, histRange, true);
        Core.normalize(rHist, rHist, 0, 3, Core.NORM_MINMAX, -1, new Mat());

        return analyzeImage(context, bHist, gHist, rHist);
    }

    private static ArrayList<Flags> analyzeImage(Context cont, Mat bHist, Mat gHist, Mat rHist){

        ArrayList<Flags> possible = new ArrayList<>();

        ArrayList<Flags> temp = Flags_data.getFlags();
        temp.remove(0);

        for (Flags flag : temp){
            String flagFile = flag.getPngName();
            InputStream in = cont.getResources().openRawResource(cont.getResources().getIdentifier(flagFile,"raw", cont.getPackageName()));
            Bitmap bitmap = BitmapFactory.decodeStream(in);

            Mat image = new Mat(bitmap.getHeight(),bitmap.getWidth(), CvType.CV_8UC1);
            Utils.bitmapToMat(bitmap, image);

            Imgproc.resize(image, image, sz);
            Imgproc.cvtColor(image, image, Imgproc.COLOR_RGB2HSV);

            List<Mat> hsv_planes1 = new ArrayList<Mat>();
            Core.split(image, hsv_planes1);

            Mat bcHist = new Mat();
            Mat gcHist = new Mat();
            Mat rcHist = new Mat();

            Imgproc.calcHist(Arrays.asList(hsv_planes1.get(0)), new MatOfInt(), new Mat(), bcHist, histSize, histRange, true);
            Core.normalize(bcHist, bcHist, 0, 3, Core.NORM_MINMAX, -1, new Mat());

            Imgproc.calcHist(Arrays.asList(hsv_planes1.get(1)), new MatOfInt(), new Mat(), gcHist, histSize, histRange, true);
            Core.normalize(gcHist, gcHist, 0, 3, Core.NORM_MINMAX, -1, new Mat());

            Imgproc.calcHist(Arrays.asList(hsv_planes1.get(2)), new MatOfInt(), new Mat(), rcHist, histSize, histRange, true);
            Core.normalize(rcHist, rcHist, 0, 3, Core.NORM_MINMAX, -1, new Mat());

            double val = (Imgproc.compareHist(bHist, bcHist, Imgproc.CV_COMP_CORREL) +
                    Imgproc.compareHist(gHist, gcHist, Imgproc.CV_COMP_CORREL) +
                    Imgproc.compareHist(rHist, rcHist, Imgproc.CV_COMP_CORREL));

            if (val > 0.5){
                flag.setColourVal(val);
                possible.add(flag);
            }
        }
        return possible;
    }
}
