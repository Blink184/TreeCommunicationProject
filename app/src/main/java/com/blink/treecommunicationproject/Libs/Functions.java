//package com.blink.treecommunicationproject.Libs;
//
//import android.graphics.Bitmap;
//import android.graphics.BitmapFactory;
//import android.graphics.Point;
//import android.os.Handler;
//import android.os.SystemClock;
//import android.support.design.widget.Snackbar;
//import android.util.Base64;
//import android.view.View;
//import android.view.animation.Interpolator;
//import android.view.animation.LinearInterpolator;
//import android.widget.TextView;
//
//import com.google.android.gms.maps.GoogleMap;
//import com.google.android.gms.maps.Projection;
//import com.google.android.gms.maps.model.LatLng;
//import com.google.android.gms.maps.model.Marker;
//
//import java.io.ByteArrayOutputStream;
//
///**
// * Created by Ahmad on 10/8/2015.
// */
//public class Functions {
//    public static void showSnackbar(View view, String s, int color){
//        Snackbar snackbar = Snackbar.make(view, s, Snackbar.LENGTH_LONG);
//        ((TextView) snackbar.getView().findViewById(android.support.design.R.id.snackbar_text)).setTextColor(color);
//        snackbar.show();
//    }
//
//    public static void animateMarker(final Marker marker, final LatLng toPosition,
//                              final boolean hideMarker, GoogleMap googleMap) {
//        try {
//            final Handler handler = new Handler();
//            final long start = SystemClock.uptimeMillis();
//            Projection proj = googleMap.getProjection();
//            Point startPoint = proj.toScreenLocation(marker.getPosition());
//            final LatLng startLatLng = proj.fromScreenLocation(startPoint);
//            final long duration = 500;
//
//            final Interpolator interpolator = new LinearInterpolator();
//
//            handler.post(new Runnable() {
//                @Override
//                public void run() {
//                    long elapsed = SystemClock.uptimeMillis() - start;
//                    float t = interpolator.getInterpolation((float) elapsed
//                            / duration);
//                    double lng = t * toPosition.longitude + (1 - t)
//                            * startLatLng.longitude;
//                    double lat = t * toPosition.latitude + (1 - t)
//                            * startLatLng.latitude;
//                    marker.setPosition(new LatLng(lat, lng));
//
//                    if (t < 1.0) {
//                        // Post again 16ms later.
//                        handler.postDelayed(this, 16);
//                    } else {
//                        if (hideMarker) {
//                            marker.setVisible(false);
//                        } else {
//                            marker.setVisible(true);
//                        }
//                    }
//                }
//            });
//        }catch (Exception ignored){}
//    }
//
//    public static String getByteImageFromBitmap(Bitmap bitmap){
//        ByteArrayOutputStream bao = new ByteArrayOutputStream();
//        bitmap.compress(Bitmap.CompressFormat.JPEG, 90, bao);
//        byte[] ba = bao.toByteArray();
//        return Base64.encodeToString(ba, 0);
//    }
//    public static Bitmap getBitmapImageFromByte(String byte64){
//        if(byte64.equals("0"))
//            return null;
//        byte[] imageAsBytes = Base64.decode(byte64.getBytes(), Base64.DEFAULT);
//        return BitmapFactory.decodeByteArray(imageAsBytes, 0, imageAsBytes.length);
//    }
//}
