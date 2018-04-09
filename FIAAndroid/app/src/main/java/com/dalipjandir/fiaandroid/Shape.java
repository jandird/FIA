package com.dalipjandir.fiaandroid;

import android.content.Context;
import android.graphics.Bitmap;
import org.opencv.android.OpenCVLoader;
import org.opencv.android.Utils;
import org.opencv.core.*;
import org.opencv.features2d.DescriptorExtractor;
import org.opencv.features2d.DescriptorMatcher;
import org.opencv.features2d.FeatureDetector;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

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
    static FeatureDetector fastFeatureDetector = FeatureDetector.create(FeatureDetector.ORB);
    static DescriptorMatcher flannDescriptorMatcher = DescriptorMatcher.create(DescriptorMatcher.BRUTEFORCE);

    static Size sz = new Size(550, 330);

    static Context cont;

    public static ArrayList<Flags> getImage(Context context, Bitmap bitmap){
        cont = context;

        Mat image1 = new Mat(bitmap.getHeight(),bitmap.getWidth(), CvType.CV_8UC1);
        Utils.bitmapToMat(bitmap, image1);

        Imgproc.resize(image1, image1, sz);
        Imgproc.cvtColor(image1, image1, Imgproc.COLOR_BGR2GRAY);

        ArrayList<MatOfKeyPoint> keypoints = new ArrayList<>();
        keypoints.add(new MatOfKeyPoint());

        fastFeatureDetector.detect(image1, keypoints.get(0));
        Mat descriptor = new Mat();
        surfDescriptorExtractor.compute(image1, keypoints.get(0), descriptor);

        return analyzeImage(descriptor);
    }

    public static ArrayList<Flags> analyzeImage(Mat original){

        ArrayList<Flags> possible = new ArrayList<>();

        ArrayList<Flags> temp = Flags_data.getFlags();

        for (Flags flag : temp){
            Mat image1 = Utils.loadResource(cont, R.raw.ad);
            Imgproc.cvtColor(image1, image1, Imgproc.COLOR_BGR2GRAY);

            Imgproc.resize(image1, image1, sz);
            ArrayList<MatOfKeyPoint> keypoints = new ArrayList<>();
            keypoints.add(new MatOfKeyPoint());

            fastFeatureDetector.detect(image1, keypoints.get(0));
            Mat descriptor = new Mat();
            surfDescriptorExtractor.compute(image1, keypoints.get(0), descriptor);

            ArrayList<MatOfDMatch> matches = new ArrayList<>();
            matches.add(new MatOfDMatch());

            flannDescriptorMatcher.match(original,
                    descriptor, matches.get(0));

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
