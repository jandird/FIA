package com.dalipjandir.fiaandroid;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import org.opencv.android.OpenCVLoader;
import org.opencv.android.Utils;
import org.opencv.core.*;
import org.opencv.features2d.DescriptorExtractor;
import org.opencv.features2d.DescriptorMatcher;
import org.opencv.features2d.FeatureDetector;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Shape {

    static {
        if (!OpenCVLoader.initDebug()) {
            // Handle initialization error
        }
    }

    static DescriptorExtractor surfDescriptorExtractor = DescriptorExtractor.create(DescriptorExtractor.ORB);
    static FeatureDetector fastFeatureDetector = FeatureDetector.create(FeatureDetector.FAST);
    static DescriptorMatcher flannDescriptorMatcher = DescriptorMatcher.create(DescriptorMatcher.BRUTEFORCE);

    static Size sz = new Size(550, 330);

    public static ArrayList<Flags> getImage(Context context, Bitmap bitmap){

        Mat image1 = new Mat(bitmap.getHeight(),bitmap.getWidth(), CvType.CV_8UC1);
        Utils.bitmapToMat(bitmap, image1);

        Imgproc.resize(image1, image1, sz);
        Imgproc.cvtColor(image1, image1, Imgproc.COLOR_RGB2GRAY);

        ArrayList<MatOfKeyPoint> keypoints = new ArrayList<>();
        keypoints.add(new MatOfKeyPoint());

        fastFeatureDetector.detect(image1, keypoints.get(0));
        Mat descriptor = new Mat();
        surfDescriptorExtractor.compute(image1, keypoints.get(0), descriptor);

        return analyzeImage(context, descriptor);
    }

    public static ArrayList<Flags> analyzeImage(Context cont, Mat original){

        ArrayList<Flags> possible = new ArrayList<>();

        ArrayList<Flags> temp = Flags_data.getFlags();
        temp.remove(0);

        for (Flags flag : temp){
            String flagFile = flag.getPngName();
            InputStream in = cont.getResources().openRawResource(cont.getResources().getIdentifier(flagFile,"raw", cont.getPackageName()));
            Bitmap bitmap = BitmapFactory.decodeStream(in);

            Mat image1 = new Mat(bitmap.getHeight(),bitmap.getWidth(), CvType.CV_8UC1);
            Utils.bitmapToMat(bitmap, image1);

            Imgproc.resize(image1, image1, sz);
            Imgproc.cvtColor(image1, image1, Imgproc.COLOR_RGB2GRAY);

            ArrayList<MatOfKeyPoint> keypoints = new ArrayList<>();
            keypoints.add(new MatOfKeyPoint());

            fastFeatureDetector.detect(image1, keypoints.get(0));
            Mat descriptor = new Mat();
            surfDescriptorExtractor.compute(image1, keypoints.get(0), descriptor);

            ArrayList<MatOfDMatch> matches = new ArrayList<>();
            matches.add(new MatOfDMatch());

            flannDescriptorMatcher.match(original, descriptor, matches.get(0));

            List<DMatch> match = matches.get(0).toList();
            ArrayList<DMatch> filter = new ArrayList<>();

            if (match.size() > 0){
                for (int i = 0; i < match.size(); i++){
                    if (match.get(i).distance < 80){
                        filter.add(match.get(i));
                    }
                }
            }

            if (filter.size() > 0){
                flag.setShapeVal(filter.size());
                possible.add(flag);
            }
        }
        Collections.sort(possible, sort);
        return possible;
    }

    static Comparator<Flags> sort = new Comparator<Flags>() {
        @Override
        public int compare(Flags o1, Flags o2) {
            if (o1.getColourVal() > o2.getColourVal()){
                return 1;
            }
            else if (o1.getColourVal() == o2.getColourVal()){
                return 0;
            }
            else {
                return 1;
            }
        }
    };
}
