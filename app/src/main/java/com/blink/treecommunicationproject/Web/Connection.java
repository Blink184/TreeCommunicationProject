//package com.blink.treecommunicationproject.Web;
//
//import android.os.AsyncTask;
//
//import com.ahmadhammoud.traffic.Libs.ProgressBar;
//
//import org.json.JSONException;
//
//import java.io.BufferedReader;
//import java.io.BufferedWriter;
//import java.io.InputStreamReader;
//import java.io.OutputStream;
//import java.io.OutputStreamWriter;
//import java.io.UnsupportedEncodingException;
//import java.net.HttpURLConnection;
//import java.net.URL;
//import java.net.URLEncoder;
//import java.util.HashMap;
//import java.util.Map;
//
//import javax.net.ssl.HttpsURLConnection;
//
///**
// * Created by Ahmad on 10/7/2015.
// */
//public class Connection {
//    public ConnectionTask performPostCall(String requestURL, HashMap<String, String> postDataParams, OnCallFinish finished) {
//        return new ConnectionTask(requestURL, postDataParams, true, finished);
//    }
//    public ConnectionTask performPostCallWithoutProgressBar(String requestURL, HashMap<String, String> postDataParams, OnCallFinish finished) {
//        return new ConnectionTask(requestURL, postDataParams, false, finished);
//    }
//
//
//    public class ConnectionTask extends AsyncTask<Void, Integer, String>{
//
//        private String requestURL;
//        private HashMap<String, String> postDataParams;
//        private OnCallFinish finished;
//        private boolean withProgressBar;
//
//        public ConnectionTask(String requestURL, HashMap<String, String> postDataParams, boolean withProgressBar, OnCallFinish finished){
//            this.requestURL = requestURL;
//            this.postDataParams = postDataParams;
//            this.finished = finished;
//            this.withProgressBar = withProgressBar;
//        }
//
//
//        @Override
//        protected void onPreExecute() {
//            super.onPreExecute();
//            if(withProgressBar)ProgressBar.show();
//        }
//
//        @Override
//        protected String doInBackground(Void ... params) {
//            URL url;
//            String response = "";
//            try {
//                url = new URL(requestURL);
//                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//                conn.setReadTimeout(15000);
//                conn.setConnectTimeout(15000);
//                conn.setRequestMethod("POST");
//                conn.setDoInput(true);
//                conn.setDoOutput(true);
//
//                OutputStream os = conn.getOutputStream();
//                BufferedWriter writer = new BufferedWriter(
//                        new OutputStreamWriter(os, "UTF-8"));
//                writer.write(getPostDataString(postDataParams));
//
//                writer.flush();
//                writer.close();
//                os.close();
//                int responseCode=conn.getResponseCode();
//
//                if (responseCode == HttpsURLConnection.HTTP_OK) {
//                    String line;
//                    BufferedReader br=new BufferedReader(new InputStreamReader(conn.getInputStream()));
//                    while ((line=br.readLine()) != null) {
//                        response+=line;
//                    }
//                }
//                else {
//                    response="";
//                }
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//
//            return response;
//        }
//
//        @Override
//        protected void onPostExecute(String s) {
//            super.onPostExecute(s);
//            try {
//                finished.processFinish(s);
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//            if(withProgressBar)ProgressBar.hide();
//        }
//
//        private String getPostDataString(HashMap<String, String> params) throws UnsupportedEncodingException {
//            StringBuilder result = new StringBuilder();
//            boolean first = true;
//            for(Map.Entry<String, String> entry : params.entrySet()){
//                if (first)
//                    first = false;
//                else
//                    result.append("&");
//
//                result.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
//                result.append("=");
//                result.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
//            }
//
//            return result.toString();
//        }
//    }
//    public interface OnCallFinish {
//        void processFinish(String output) throws JSONException;
//    }
//}
