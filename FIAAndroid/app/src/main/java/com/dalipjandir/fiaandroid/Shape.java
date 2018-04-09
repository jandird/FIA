package com.dalipjandir.fiaandroid;

import org.opencv.core.*;
import org.opencv.features2d.DescriptorExtractor;
import org.opencv.features2d.DescriptorMatcher;
import org.opencv.features2d.FeatureDetector;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import java.util.ArrayList;
import java.util.List;

public class Shape {

    static FeatureDetector fastFeatureDetector = FeatureDetector.create(FeatureDetector.FAST);
    static DescriptorExtractor surfDescriptorExtractor = DescriptorExtractor.create(DescriptorExtractor.ORB);
    static DescriptorMatcher flannDescriptorMatcher = DescriptorMatcher.create(DescriptorMatcher.BRUTEFORCE);

    static Size sz = new Size(550, 330);

    public static ArrayList<Flags> getImage(String path){
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        Mat image1 = Imgcodecs.imread(path, Imgcodecs.CV_LOAD_IMAGE_GRAYSCALE);

        Imgproc.resize(image1, image1, sz);
        ArrayList<MatOfKeyPoint> keypoints = new ArrayList<>();
        keypoints.add(new MatOfKeyPoint());

        fastFeatureDetector.detect(image1, keypoints.get(0));
        Mat descriptor = new Mat();
        surfDescriptorExtractor.compute(image1, keypoints.get(0), descriptor);

        return analyzeImage(descriptor);
    }

    public static ArrayList<Flags> analyzeImage(Mat original){

        ArrayList<Flags> possible = new ArrayList<>();

        Flags [] temp = new Flags [10];
        for (Flags flag : temp){
            Mat image1 = Imgcodecs.imread("res/" + flag.getPngName(), Imgcodecs.CV_LOAD_IMAGE_GRAYSCALE);

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
        }
        return new ArrayList<Flags>();
    }
}
