package tests.admin.DeviceAdmin;

import tests.methods.HttpRequest;
import tests.methods.URL;

import java.util.List;
import java.util.Map;

/**
 * Created by root on 01.10.15.
 */
public class deviceMethods {

    public static Map<String, String> addDevice(String ip, String specification){
        String message =    "adminName="+ URL.adminName
                +"&adminPassword="+URL.password
                +"&ip="+ip
                +"&specification="+specification
                ;

       return HttpRequest.makeInMap(message, URL.addDevice);
    }

    public static Map<String, String> deleteDevice(String id){
        String message =    "adminName="+ URL.adminName
                +"&adminPassword="+URL.password
                +"&deviceId="+id
                ;
        return HttpRequest.makeInMap(message, URL.deleteDevice);
    }

    public static Map<String, String> changeDevice(String device_id, String ip, String  specification){
        String message =    "adminName="+ URL.adminName
                +"&adminPassword="+URL.password
                +"&ip="+ip
                +"&specification="+specification
                +"&deviceId="+device_id
                ;
        return HttpRequest.makeInMap(message, URL.changeDevice);
    }

    public static List<Map> getDevice(String ip, String specification, String device_id){
        String message =    "adminName="+ URL.adminName
                            +"&adminPassword="+URL.password
                            +"&ip="+ip
                            +"&specification="+specification
                            +"&deviceId="+device_id
                            ;
        return HttpRequest.makeInList(message, URL.getDevice);
    }
}
