package com.blink.treecommunicationproject.Libs;

import android.view.View;

import com.github.jlmd.animatedcircleloadingview.AnimatedCircleLoadingView;

/**
 * RIPPED OFF by Kr4zy on sshhh/a/secret.
 */
public class ProgressBar {

    public static AnimatedCircleLoadingView animatedCircleLoadingView;

    public static void hide(){
        animatedCircleLoadingView.setVisibility(View.GONE);
    }
    public static void show(){
        animatedCircleLoadingView.setVisibility(View.VISIBLE);
    }
    public static void update(int percent){
        if(animatedCircleLoadingView != null){
            animatedCircleLoadingView.setPercent(percent);
            if(percent == 100){
                stopOk();
                hide();
            }else{
                start();
                show();
            }
        }
    }
    public static void start(){
        animatedCircleLoadingView.startDeterminate();
    }
    public static void stop(){
        stopOk();
        hide();
    }
    public static void stopOk(){
        animatedCircleLoadingView.stopOk();
    }
    public static void stopFailure(){
        animatedCircleLoadingView.stopFailure();
    }

    public static void set(View circle_loading_view) {
        animatedCircleLoadingView = (AnimatedCircleLoadingView) circle_loading_view;
        hide();
        start();
    }
}
