package com.dalipjandir.fiaandroid;

import android.graphics.Bitmap;
import org.opencv.core.*;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;

import java.lang.reflect.Array;
import java.util.*;

public class Colour {

    static MatOfInt histSize = new MatOfInt(256);
    static final MatOfFloat histRange = new MatOfFloat(0f, 256f);
    static Size sz = new Size (550, 330);

    public static ArrayList<Flags> getImage (String path){
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
        Mat image = Imgcodecs.imread(path);

        Imgproc.resize(image, image, sz);

        Imgproc.cvtColor(image, image, Imgproc.COLOR_BGR2HSV);

        List<Mat> hsv_planes1 = new ArrayList<Mat>();
        Core.split(image, hsv_planes1);

        Mat bHist = new Mat();
        Mat gHist = new Mat();
        Mat rHist = new Mat();

        Imgproc.calcHist(Arrays.asList(hsv_planes1.get(0)), new MatOfInt(), new Mat(), bHist, histSize, histRange, true);
        Core.normalize(bHist, bHist, 0, 1, Core.NORM_MINMAX, -1, new Mat());

        Imgproc.calcHist(Arrays.asList(hsv_planes1.get(1)), new MatOfInt(), new Mat(), gHist, histSize, histRange, true);
        Core.normalize(gHist, gHist, 0, 1, Core.NORM_MINMAX, -1, new Mat());

        Imgproc.calcHist(Arrays.asList(hsv_planes1.get(2)), new MatOfInt(), new Mat(), rHist, histSize, histRange, true);
        Core.normalize(rHist, rHist, 0, 1, Core.NORM_MINMAX, -1, new Mat());

        return new ArrayList<>();
        //return analyzeImage(bHist, gHist, rHist);
    }

    private static ArrayList<Flags> analyzeImage(Mat bHist, Mat gHist, Mat rHist){

        ArrayList<Flags> possible = new ArrayList<>();

        ArrayList<Flags> temp = Flags_data.getFlags();

        for (Flags flag : temp){
            Mat image = Imgcodecs.imread("res/" + flag.getPngName());

            Imgproc.resize(image, image, sz);

            Imgproc.cvtColor(image, image, Imgproc.COLOR_BGR2HSV);

            List<Mat> hsv_planes1 = new ArrayList<Mat>();
            Core.split(image, hsv_planes1);

            Mat bcHist = new Mat();
            Mat gcHist = new Mat();
            Mat rcHist = new Mat();

            Imgproc.calcHist(Arrays.asList(hsv_planes1.get(0)), new MatOfInt(), new Mat(), bcHist, histSize, histRange, true);
            Core.normalize(bcHist, bcHist, 0, 1, Core.NORM_MINMAX, -1, new Mat());

            Imgproc.calcHist(Arrays.asList(hsv_planes1.get(1)), new MatOfInt(), new Mat(), gcHist, histSize, histRange, true);
            Core.normalize(gcHist, gcHist, 0, 1, Core.NORM_MINMAX, -1, new Mat());

            Imgproc.calcHist(Arrays.asList(hsv_planes1.get(2)), new MatOfInt(), new Mat(), rcHist, histSize, histRange, true);
            Core.normalize(rcHist, rcHist, 0, 1, Core.NORM_MINMAX, -1, new Mat());

            double val = (Imgproc.compareHist(bHist, bcHist, Imgproc.CV_COMP_CORREL) +
                    Imgproc.compareHist(gHist, gcHist, Imgproc.CV_COMP_CORREL) +
                    Imgproc.compareHist(rHist, rcHist, Imgproc.CV_COMP_CORREL));

            if (val > 1){
                flag.setColourVal(val);
                possible.add(flag);
            }
        }
        Collections.sort(possible, sort);
        return possible;
    }

    static Comparator<Flags> sort = new Comparator<Flags>() {
        @Override
        public int compare(Flags o1, Flags o2) {
            if (o1.getShapeVal() > o2.getShapeVal()){
                return 1;
            }
            else if (o1.getShapeVal() == o2.getShapeVal()){
                return 0;
            }
            else {
                return 1;
            }
        }
    };
}
