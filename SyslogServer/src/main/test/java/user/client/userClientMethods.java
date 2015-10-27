package user.client;

import methods.HttpRequest;
import methods.URL;

import java.util.Map;

/**
 * Created by root on 07.10.15.
 */
public class userClientMethods {

    public static Map<String, String> enterOrOutTheSystemLog(String host_ip, String host_mac, String user_id, String event_type, String datetime){
        String message =    "host_ip=" + host_ip
                            +"&host_mac=" + host_mac
                            +"&datetime=" + datetime

                            +"&user_id=" + user_id
                            +"&event_type=" + event_type
                ;
        return HttpRequest.makeInMap(message, URL.userClient_enterOrOutTheSystemLog);
    }

}
