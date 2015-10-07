package tests.admin.SecurityAdmin.Devices;

import tests.methods.HttpRequest;
import tests.methods.URL;

import java.util.List;
import java.util.Map;

/**
 * Created by root on 02.10.15.
 */
public class devicesMethods {
    public static List<Map> getDevice(){
        String message =    "adminName="+ URL.adminName
                            +"&adminPassword="+URL.password
                            +"&ip="
                            +"&specification="
                            + "&deviceId="
                ;
        return HttpRequest.makeInList(message, URL.getSecurityDevices);
    }
}
