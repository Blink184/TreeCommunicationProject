package com.blink.treecommunicationproject.Web;

/**
 * Created by Ahmad on 10/7/2015.
 */
public class Links {
    /*public static String HOST = "http://192.168.1.6/TreeCommunicationProjectWeb/";*/

    public static String HOST = "http://10.0.2.2/TreeCommunicationProjectWeb/";
    //http://localhost:63343/TreeCommunicationProjectWeb/database/api/validateUser.php

    public static String ACTIONS = HOST + "database/api/";
    public static String VALIDATEUSER = ACTIONS + "validateUser.php";
    public static String GETTREE = ACTIONS + "getUserRoleTree.php";
    public static String GETTASKS = ACTIONS + "getTasks.php";
    public static String ACCEPTTASK = ACTIONS + "acceptTask.php";
    public static String FINISHTASK = ACTIONS + "finishTask.php";
}
