package inc.primssware.mylibrary.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Handler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by Abdallah on 04/02/2017
 */

public class Utilities {

    public static long nowInMillis(){
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        return c.getTimeInMillis();
    }

    public static List<String> readFileFromAssetsAsListOfLines(String fileName, Context context) {
        List<String> lines = new ArrayList<>();
        try {
            final InputStream file = context.getAssets().open(fileName);
            BufferedReader reader = new BufferedReader(new InputStreamReader(file));
            String line = reader.readLine();
            lines.add(line);
            String previous = line;
            while(line != null){
                if (line.equals(previous)){
                    line = reader.readLine();
                    continue;
                }
                lines.add(line);
                line = reader.readLine();
                previous = line;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lines;
    }

    /**
     * Calculate distance between two points
     * @param point1Lat Latitude of point 1
     * @param point1Lng Longitude of point 1
     * @param point2Lat Latitude of point 2
     * @param point2Lng Longitude of point 2
     * @return Distance in meters
     */
    public static float calculateDistance(Double point1Lat, Double point1Lng, Double point2Lat, Double point2Lng){
        // Calculate distance
        float[] distance = new float[1];
        Location.distanceBetween(point1Lat, point1Lng, point2Lat, point2Lng, distance);
        return Math.round(distance[0]);
    }

    public static boolean isNullString(String entry){
        return entry == null;
    }

    public static boolean isNullOrEmptyString(String entry) {
        return entry == null || entry.length() == 0;
    }

    public static class AppInfo {

        private int versionCode;
        private String versionName;
        private String packageName;
        //private String hashKey;

        public AppInfo(Context context){
            PackageManager manager = context.getPackageManager();
            try {
                PackageInfo info = manager.getPackageInfo(context.getPackageName(), 0);
                packageName = info.packageName;
                versionCode = info.versionCode;
                versionName = info.versionName;
                /*info = manager.getPackageInfo(context.getPackageName(), PackageManager.GET_SIGNATURES);
                for (Signature signature : info.signatures) {
                    MessageDigest md = MessageDigest.getInstance("SHA");
                    md.update(signature.toByteArray());
                }*/
            } catch (PackageManager.NameNotFoundException e){// | NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
        }

        public int getVersionCode() {
            return versionCode;
        }

        public String getVersionName() {
            return versionName;
        }

        public String getPackageName() {
            return packageName;
        }

        /*public String getHashKey(){
            return hashKey;
        }*/

    }

    public static void autoSwitch(int durationInMillis, final boolean... value){
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                value[0] = !value[0];
            }
        }, durationInMillis);
    }

}
