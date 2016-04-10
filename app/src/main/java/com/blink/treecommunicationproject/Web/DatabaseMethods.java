package com.blink.treecommunicationproject.Web;

import com.blink.treecommunicationproject.Objects.Employee;


/**
 * Created by AYNUR on 4/10/2016.
 */
public class DatabaseMethods {

    public static Employee getEmployee (String username, String password) {
        return new Employee(0 , "Raul", "Gimenez", "78526965", Employee.EmployeeType.President );
    }

//    public static Connection.ConnectionTask insertUser(HashMap<String, String> postDataParams, Connection.OnCallFinish finished){
//        return new Connection().performPostCall(INSERTUSER, postDataParams, finished);
//    }
//    public static Connection.ConnectionTask getUser(HashMap<String, String> postDataParams, Connection.OnCallFinish finished){
//        return new Connection().performPostCall(GETUSER, postDataParams, finished);
//    }
//    public static Connection.ConnectionTask insertBroadcast(HashMap<String, String> postDataParams, Connection.OnCallFinish finished){
//        return new Connection().performPostCall(INSERTBROADCAST, postDataParams, finished);
//    }
//    public static Connection.ConnectionTask insertImageBroadcast(HashMap<String, String> postDataParams, Connection.OnCallFinish finished){
//        return new Connection().performPostCall(INSERTIMAGEBROADCAST, postDataParams, finished);
//    }
//    public static void insertProfilePicture(Bitmap bitmap, String name, Connection.OnCallFinish finished){
//        HashMap<String, String> postDataParams = new HashMap<>();
//        postDataParams.put("name", name);
//        postDataParams.put("bytes", Functions.getByteImageFromBitmap(bitmap));
//        new Connection().performPostCall(INSERTPROFILEPICTURE, postDataParams, finished).execute();
//    }
//    public static void updateUser(int ID, String firstname, String lastname, String password, Connection.OnCallFinish finished){
//        HashMap<String, String> postDataParams = new HashMap<>();
//        postDataParams.put("id", String.valueOf(ID));
//        postDataParams.put("firstname", firstname);
//        postDataParams.put("lastname", lastname);
//        postDataParams.put("password", password);
//        new Connection().performPostCall(UPDATEUSER, postDataParams, finished).execute();
//    }
//    public static void getBroadcasts(int ID, double lat, double lng, double distance, int time, int lastReceivedID, Connection.OnCallFinish finished){
//        HashMap<String, String> postDataParams = new HashMap<>();
//        postDataParams.put("lat", String.valueOf(lat));
//        postDataParams.put("lng", String.valueOf(lng));
//        postDataParams.put("id", String.valueOf(ID));
//        postDataParams.put("distance", String.valueOf(distance));
//        postDataParams.put("time", String.valueOf(time));
//        postDataParams.put("lastReceivedID", String.valueOf(lastReceivedID));
//        new Connection().performPostCallWithoutProgressBar(GETBROADCASTS, postDataParams, finished).execute();
//    }
//    public static void insertUserStatus(int ID, String data, Connection.OnCallFinish finished){
//        HashMap<String, String> postDataParams = new HashMap<>();
//        postDataParams.put("id", String.valueOf(ID));
//        postDataParams.put("data", data);
//        new Connection().performPostCallWithoutProgressBar(INSERTUSERSTATUS, postDataParams, finished).execute();
//    }
//    public static void getTrafficLightsStatus(Connection.OnCallFinish finished){
//        HashMap<String, String> postDataParams = new HashMap<>();
//        new Connection().performPostCall(GETTRAFFICLIGHTSSTATUS, postDataParams, finished).execute();
//    }
//    public static void getTrafficLightsStatusWithBroadcasts(int range, double lat, double lng, Connection.OnCallFinish finished){
//        HashMap<String, String> postDataParams = new HashMap<>();
//        postDataParams.put("range", String.valueOf(range));
//        postDataParams.put("lat", String.valueOf(lat));
//        postDataParams.put("lng", String.valueOf(lng));
//        new Connection().performPostCall(GETTRAFFICLIGHTSSTATUSWITHBROADCASTS, postDataParams, finished).execute();
//    }
//    public static void getTrafficLightHistory(int ID, Connection.OnCallFinish finished){
//        HashMap<String, String> postDataParams = new HashMap<>();
//        postDataParams.put("id", String.valueOf(ID));
//        new Connection().performPostCall(GETTRAFFICLIGHTHISTORY, postDataParams, finished).execute();
//    }
//    public static void getTraffic(Connection.OnCallFinish finished){
//        HashMap<String, String> postDataParams = new HashMap<>();
//        new Connection().performPostCall(GETTRAFFIC, postDataParams, finished).execute();
//    }
//    public static void getPath(String latlngs, int roadNb, Connection.OnCallFinish finished){
//        HashMap<String, String> postDataParams = new HashMap<>();
//        postDataParams.put("latlngs", latlngs);
//        postDataParams.put("roadNb", String.valueOf(roadNb));
//        new Connection().performPostCall(GETPATH, postDataParams, finished).execute();
//    }
//    public static void sendMessage(int senderId, int receiverId, String message, Connection.OnCallFinish finished){
//        HashMap<String, String> postDataParams = new HashMap<>();
//        postDataParams.put("senderId", String.valueOf(senderId));
//        postDataParams.put("receiverId", String.valueOf(receiverId));
//        postDataParams.put("message", message);
//        new Connection().performPostCall(SENDMESSAGE, postDataParams, finished).execute();
//    }
//    public static void getMessages(int userId, Connection.OnCallFinish finished){
//        HashMap<String, String> postDataParams = new HashMap<>();
//        postDataParams.put("userId", String.valueOf(userId));
//        new Connection().performPostCall(GETMESSAGES, postDataParams, finished).execute();
//    }
//    public static void getLastMessage(int userId, int lastId, Connection.OnCallFinish finished){
//        HashMap<String, String> postDataParams = new HashMap<>();
//        postDataParams.put("userId", String.valueOf(userId));
//        postDataParams.put("lastId", String.valueOf(lastId));
//        new Connection().performPostCallWithoutProgressBar(GETLASTMESSAGE, postDataParams, finished).execute();
//    }
}
