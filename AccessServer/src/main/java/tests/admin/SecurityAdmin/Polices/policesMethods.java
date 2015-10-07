package tests.admin.SecurityAdmin.Polices;

import tests.methods.HttpRequest;
import tests.methods.URL;

import java.util.List;
import java.util.Map;

/**
 * Created by root on 02.10.15.
 */
public class policesMethods {
    public static Map<String,String> changePolicyOnDevice(String deviceId, String  roleId, String status){
        String message =    "adminName="+ URL.adminName
                +"&adminPassword="+URL.password
                +"&deviceId="+ deviceId
                +"&roleId="+roleId
                +"&status="+status
                ;
        return HttpRequest.makeInMap(message, URL.changeSecurityPermission);
    }

    public static Map<String,String> getPolicyOnDevice(String deviceId, String roleId){
        String message =    "adminName="+ URL.adminName
                +"&adminPassword="+URL.password
                +"&deviceId="+ deviceId
                +"&roleId="+roleId
                ;
        return HttpRequest.makeInMap(message, URL.getPolicyOnDevice);
    }

    public static List<Map> getPolicy(String deviceId, String roleId){
        String message =    "adminName="+ URL.adminName
                +"&adminPassword="+URL.password
                +"&deviceId="+ deviceId
                +"&roleId="+roleId
                ;
        return HttpRequest.makeInList(message, URL.getPolicy);
    }
}
