package arduino.client;

import methods.HttpRequest;
import methods.URL;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;

/**
 * Created by root on 07.10.15.
 */
public class arduinoClientMethods {

    public static Map<String, String> enterOrOutTheRoomLog(String device_id, String device_ip, String user_id, String user_name, String card_id, String user_type , String result_type, String datetime, String event_type){
        String message =    "device_id="+device_id
                            +"&device_ip=" + device_ip
                            +"&user_id=" + user_id
                            +"&user_name=" + user_name
                            +"&card_id=" + card_id
                            +"&user_type=" + user_type
                            +"&result_type=" +result_type
                            +"&datetime=" +datetime

                            +"&event_type=" + event_type
                ;
         return HttpRequest.makeInMap(message, URL.arduinoClient_enterOrOutTheRoomLog);
    }

    public static Map<String, String> initialisationErrorLog(String device_id, String device_ip, String error_type, String datetime){
        String message =    "device_id=" + device_id
                            +"&device_ip=" + device_ip
                            +"&error_type=" + error_type
                            +"&datetime=" +datetime
                ;
        return HttpRequest.makeInMap(message, URL.arduinoClient_initialisationErrorLog);
    }

    public static Map<String, String> workErrorLog(String device_id, String device_ip, String client_error_type, String arduino_error_type, String datetime){
        String message =    "device_id=" + device_id
                            +"&device_ip=" + device_ip
                            +"&client_error_type=" + client_error_type
                            +"&arduino_error_type=" + arduino_error_type
                            +"&datetime=" +datetime
                            ;
        return HttpRequest.makeInMap(message, URL.arduinoClient_workErrorsLog);
    }


    public static Map<String, String> alarmEneterAndOutLog(File cashedLogs){
        /*
        нужно создать метод передачи файла
         */
        return null;
    }

    public static Map<String,String> unautorizedAccessLog(String device_id, String device_ip, String sensor_type, String event_type, String datetime){
        String message =    "device_id=" + device_id
                            +"&device_ip=" + device_ip
                            +"&sensor_type=" + sensor_type
                            +"&event_type=" + event_type
                            +"&datetime=" +datetime
                            ;
        return HttpRequest.makeInMap(message, URL.arduinoClient_unauthorizedAccessLog);
    }



}