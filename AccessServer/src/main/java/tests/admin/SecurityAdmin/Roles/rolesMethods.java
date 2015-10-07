package tests.admin.SecurityAdmin.Roles;

import tests.methods.HttpRequest;
import tests.methods.URL;

import java.util.List;
import java.util.Map;

/**
 * Created by root on 02.10.15.
 */
public class rolesMethods {
    public static Map<String,String> addRole(String title){
        String message =    "adminName="+ URL.adminName
                +"&adminPassword="+URL.password
                +"&title="+title
                ;
        return HttpRequest.makeInMap(message, URL.addRole);
    }

    public static Map<String,String> changeRole(String title, String roleId){
        String message =    "adminName="+ URL.adminName
                +"&adminPassword="+URL.password
                +"&title="+title
                +"&roleId="+roleId
                ;
        return HttpRequest.makeInMap(message, URL.changeRole);
    }

    public static Map<String,String> deleteRole(String roleId){
        String message =    "adminName="+ URL.adminName
                +"&adminPassword="+URL.password
                +"&id="+roleId
                ;
        return HttpRequest.makeInMap(message, URL.deleteRole);
    }

    public static List<Map> getRole(String roleId, String title){
        String message =    "adminName="+ URL.adminName
                +"&adminPassword="+URL.password
                +"&title="+title
                +"&roleId="+roleId
                ;
        return HttpRequest.makeInList(message, URL.getSecurityRoles);
    }
}
