package admin.client;

import methods.HttpRequest;
import methods.URL;

import java.util.Map;

/**
 * Created by root on 07.10.15.
 */
public class adminClientMethods {

    /*
    Вход и выход из помещения
     */

    public static Map<String, String> enterOrOutAdminLog(String host_ip, String host_mac, String login_name, String login_id, String result_type, String admin_type, String datetime, String event_type){
        String message =    "host_ip=" + host_ip
                            +"&host_mac=" + host_mac
                            +"&login_name=" + login_name
                            +"&login_id=" + login_id
                            +"&result_type=" +result_type
                            +"&datetime=" + datetime
                            +"&admin_type=" + admin_type

                            +"&event_type=" + event_type
                ;
        return HttpRequest.makeInMap(message, URL.adminClient_enterOrOutTheSessionLog);
    }

    /*
    технический департамент
     */

    public static Map<String, String> deviceLog (String host_ip, String host_mac, String login_name, String login_id, String result_type, String device_id, String device_ip, String device_specification, String datetime, String event_type){
        String message =    "host_ip=" + host_ip
                +"&host_mac=" + host_mac
                +"&login_name=" + login_name
                +"&login_id=" + login_id
                +"&result_type=" +result_type
                +"&datetime=" +datetime

                +"&device_id=" +device_id
                +"&device_ip=" +device_ip
                +"&device_specification=" + device_specification

                +"&event_type=" + event_type
                ;
        return HttpRequest.makeInMap(message, URL.adminClient_technical_deviceLog);
    }


    /*
    отдел кадров
     */

    public static Map<String, String> hrLog (String host_ip, String host_mac, String login_name, String login_id, String result_type, String employee_name, String employee_id, String employee_role_id, String card_id, String datetime, String event_type){
        String message =    "host_ip=" + host_ip
                +"&host_mac=" + host_mac
                +"&login_name=" + login_name
                +"&login_id=" + login_id
                +"&result_type=" +result_type
                +"&datetime=" +datetime

                +"&employee_name=" + employee_name
                +"&employee_id=" + employee_id
                +"&employee_role_id=" + employee_role_id
                +"&card_id=" + card_id

                +"&event_type=" + event_type
                ;
        return HttpRequest.makeInMap(message, URL.adminClient_hr_employeeLog);
    }

    /*
    рецепшн
     */

    public static Map<String, String> guestLog (String host_ip, String host_mac, String login_name, String login_id, String result_type, String guest_name, String guest_id, String card_id, String datetime, String event_type){
        String message =    "host_ip=" + host_ip
                +"&host_mac=" + host_mac
                +"&login_name=" + login_name
                +"&login_id=" + login_id
                +"&result_type=" +result_type
                +"&datetime=" +datetime

                +"&guest_name=" + guest_name
                +"&guest_id=" + guest_id
                +"&card_id=" + card_id

                +"&event_type=" + event_type
                ;
        return HttpRequest.makeInMap(message, URL.adminClient_reception_guestLog);
    }

    /*
    секьюрити
     */

    public static Map<String, String> accountLog (String host_ip, String host_mac, String login_name, String login_id, String result_type, String account_login, String account_id, String employee_id, String  db_roles, String datetime, String event_type){
        String message =    "host_ip=" + host_ip
                +"&host_mac=" + host_mac
                +"&login_name=" + login_name
                +"&login_id=" + login_id
                +"&result_type=" +result_type
                +"&datetime=" +datetime

                +"&account_login=" + account_login
                +"&employee_id=" + employee_id
                +"&db_roles=" + db_roles
                +"&account_id=" + account_id

                +"&event_type=" + event_type
                ;
        return HttpRequest.makeInMap(message, URL.adminClient_security_accountLog);
    }


    public static Map<String, String> cardLog (String host_ip, String host_mac, String login_name, String login_id, String result_type, String card_id, String datetime, String event_type){
        String message =    "host_ip=" + host_ip
                +"&host_mac=" + host_mac
                +"&login_name=" + login_name
                +"&login_id=" + login_id
                +"&result_type=" +result_type
                +"&datetime=" +datetime

                +"&card_id=" + card_id

                +"&event_type=" + event_type
                ;
        return HttpRequest.makeInMap(message, URL.adminClient_security_cardLog);
    }

    public static Map<String, String> changeAccesOnDeviceLog (String host_ip, String host_mac, String login_name, String login_id, String result_type, String device_id, String role_id, String access_id, String datetime){
        String message =    "host_ip=" + host_ip
                +"&host_mac=" + host_mac
                +"&login_name=" + login_name
                +"&login_id=" + login_id
                +"&result_type=" +result_type
                +"&datetime=" +datetime

                +"&device_id=" + device_id
                +"&role_id=" + role_id
                +"&access_type=" + access_id
                ;
        return HttpRequest.makeInMap(message, URL.adminClient_security_changeRightsLog);
    }

    public static Map<String, String> roleLog (String host_ip, String host_mac, String login_name, String login_id, String result_type, String role_id, String role_title, String datetime, String event_type){
        String message =    "host_ip=" + host_ip
                +"&host_mac=" + host_mac
                +"&login_name=" + login_name
                +"&login_id=" + login_id
                +"&result_type=" +result_type
                +"&datetime=" +datetime

                +"&role_id=" + role_id
                +"&role_title=" + role_title

                +"&event_type=" + event_type
                ;
        return HttpRequest.makeInMap(message, URL.adminClient_security_roleLog);
    }


}
