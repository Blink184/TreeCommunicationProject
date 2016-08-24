package com.blink.treecommunicationproject.Web;

import com.blink.treecommunicationproject.Objects.Employee;

import java.util.Date;
import java.util.HashMap;
import static com.blink.treecommunicationproject.Web.Links.*;

/**
 * Created by AYNUR on 4/10/2016.
 */
public class DatabaseMethods {

    public static Employee getEmployee (String username, String password) {
        return new Employee(0 , "Raul", "Gimenez", "78526965", Employee.EmployeeType.President );
    }

    public static Connection.ConnectionTask validateUser(HashMap<String, String> postDataParams, Connection.OnCallFinish finished) {
        return new Connection().performPostCall(VALIDATEUSER, postDataParams, finished);
    }

    public static Connection.ConnectionTask getTree(int userRoleId, Connection.OnCallFinish finished) {
        HashMap<String, String> postDataParams = new HashMap<>();
        postDataParams.put("parentid", String.valueOf(userRoleId));
        return new Connection().performPostCall(GETTREE, postDataParams, finished);
    }

    public static Connection.ConnectionTask getTasks(int userRoleId, int limit, Connection.OnCallFinish finished) {
        HashMap<String, String> postDataParams = new HashMap<>();
        postDataParams.put("userroleid", String.valueOf(userRoleId));
        postDataParams.put("limit", String.valueOf(limit));
        return new Connection().performPostCall(GETTASKS, postDataParams, finished);
    }

    public static Connection.ConnectionTask getBroadcasts(int userRoleId, int limit, Connection.OnCallFinish finished) {
        HashMap<String, String> postDataParams = new HashMap<>();
        postDataParams.put("userroleid", String.valueOf(userRoleId));
        postDataParams.put("limit", String.valueOf(limit));
        return new Connection().performPostCall(GETBROADCASTS, postDataParams, finished);
    }

    public static Connection.ConnectionTask insertTask(String title, String content, int empnameto, int empnamefrom, String duedate,Connection.OnCallFinish finished) {
        HashMap<String, String> postDataParams = new HashMap<>();
        postDataParams.put("title", String.valueOf(title));
        postDataParams.put("content", String.valueOf(content));
        postDataParams.put("empnameto", String.valueOf(empnameto));
        postDataParams.put("empnamefrom", String.valueOf(empnamefrom));
        postDataParams.put("duedate", String.valueOf(duedate));
        return new Connection().performPostCall(INSERTTASK, postDataParams, finished);
    }

    public static Connection.ConnectionTask getUserRoles(HashMap<String, String> postDataParams, Connection.OnCallFinish finished) {
        return new Connection().performPostCall(GETUSERROLES, postDataParams, finished);
    }

    public static Connection.ConnectionTask acceptTask(int taskid, Date date, Connection.OnCallFinish finished) {
        HashMap<String, String> postDataParams = new HashMap<>();
        postDataParams.put("taskid", String.valueOf(taskid));
        postDataParams.put("date", String.valueOf(date));
        return new Connection().performPostCall(ACCEPTTASK, postDataParams, finished);
    }

    public static Connection.ConnectionTask finishTask(int taskid, Date date, Connection.OnCallFinish finished) {
        HashMap<String, String> postDataParams = new HashMap<>();
        postDataParams.put("taskid", String.valueOf(taskid));
        postDataParams.put("date", String.valueOf(date));
        return new Connection().performPostCall(FINISHTASK, postDataParams, finished);
    }

    public static Connection.ConnectionTask insertBroadcast(int fromUserRoleId, String toType, String toUserRoleIds, String title, String content, Connection.OnCallFinish finished) {
        HashMap<String, String> postDataParams = new HashMap<>();
        postDataParams.put("fromuserroleid", String.valueOf(fromUserRoleId));
        postDataParams.put("totype", String.valueOf(toType));
        postDataParams.put("touserroleids", toUserRoleIds);
        postDataParams.put("title", title);
        postDataParams.put("content", content);
        return new Connection().performPostCall(INSERTBROADCAST, postDataParams, finished);
    }




}
