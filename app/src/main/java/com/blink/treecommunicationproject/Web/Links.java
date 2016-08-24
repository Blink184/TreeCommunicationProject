package com.blink.treecommunicationproject.Web;

/**
 * Created by Ahmad on 10/7/2015.
 */
public class Links {
    /*public static String HOST = "http://192.168.1.6/TreeCommunicationProjectWeb/";*/

    public static String IP = "10.0.2.2:80";
    public static String HOST = "http://" + IP + "/TreeCommunicationProjectWeb/";
    public static String PROFILEPICTURESFOLDER = HOST + "resources/images/employee/users/";
    //http://localhost:63343/TreeCommunicationProjectWeb/database/api/validateUser.php

    public static String ACTIONS = HOST + "database/api/";
    public static String VALIDATEUSER = ACTIONS + "validateUser.php";
    public static String GETTREE = ACTIONS + "getUserRoleTree.php";
    public static String GETTASKS = ACTIONS + "getTasks.php";
    public static String ACCEPTTASK = ACTIONS + "acceptTask.php";
    public static String FINISHTASK = ACTIONS + "finishTask.php";
    public static String GETUSERROLES = ACTIONS + "getUserRoles.php";
    public static String INSERTTASK = ACTIONS + "insertTask.php";
    public static String GETBROADCASTS = ACTIONS + "getBroadcasts.php";
    public static String INSERTBROADCAST = ACTIONS + "insertBroadcast.php";

    public static void ipChanged(){
        HOST = "http://" + IP + "/TreeCommunicationProjectWeb/";
        ACTIONS = HOST + "database/api/";
        PROFILEPICTURESFOLDER = HOST + "resources/images/employee/users/";
        VALIDATEUSER = ACTIONS + "validateUser.php";
        GETTREE = ACTIONS + "getUserRoleTree.php";
        GETTASKS = ACTIONS + "getTasks.php";
        ACCEPTTASK = ACTIONS + "acceptTask.php";
        FINISHTASK = ACTIONS + "finishTask.php";
        GETUSERROLES = ACTIONS + "getUserRoles.php";
        INSERTTASK = ACTIONS + "insertTask.php";
        GETBROADCASTS = ACTIONS + "getBroadcasts.php";
        INSERTBROADCAST = ACTIONS + "insertBroadcast.php";
    }
}
