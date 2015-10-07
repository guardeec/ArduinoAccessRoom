package tests.admin.UserAdmin;

import tests.methods.HttpRequest;
import tests.methods.URL;

import java.util.List;
import java.util.Map;

/**
 * Created by root on 01.10.15.
 */
public class userMethods {

    public static Map<String, String> addCard(String cardNumber){
        String message =    "adminName="+ URL.adminName
                            +"&adminPassword="+URL.password
                            +"&cardNumber="+cardNumber
                            ;
        return HttpRequest.makeInMap(message, URL.addCard);
    }

    public static Map<String, String> deleteCard(String cardId){
        String message =    "adminName="+ URL.adminName
                            +"&adminPassword="+URL.password
                            +"&cardId="+cardId
                            ;
        return HttpRequest.makeInMap(message, URL.deleteCard);
    }

    public static Map<String, String> addUser(String name, String card_id, String role_id){
        String message =    "adminName="+ URL.adminName
                            +"&adminPassword="+URL.password
                            +"&name="+name
                            +"&cardId="+card_id
                            +"&roleId="+role_id;
        return HttpRequest.makeInMap(message, URL.addUser);
    }

    public static Map<String, String> changeUser(String name, String user_id, String role_id){
        String message =    "adminName="+ URL.adminName
                            +"&adminPassword="+URL.password
                            +"&name="+name
                            +"&userId="+user_id
                            +"&roleId="+role_id;
        return HttpRequest.makeInMap(message, URL.changeUser);
    }

    public static Map<String, String> deleteUser(String user_id){
        String message =    "adminName="+ URL.adminName
                            +"&adminPassword="+URL.password
                            +"&userId="+user_id;
        return HttpRequest.makeInMap(message, URL.deleteUser);
    }

    public static List<Map> getUser(String user_id, String name, String role_id){
        String message =    "adminName="+ URL.adminName
                            +"&adminPassword="+URL.password
                            +"&userId="+user_id
                            +"&name="+name
                            +"&roleId="+role_id;
        return HttpRequest.makeInList(message, URL.getUser);
    }

    public static List<Map> getFreeCards(){
        String message =    "adminName="+ URL.adminName
                            +"&adminPassword="+URL.password;
        return HttpRequest.makeInList(message, URL.getFreeUserCards);
    }

    public static List<Map> getRoles(){
        String message =    "adminName="+ URL.adminName
                            +"&adminPassword="+URL.password;
        return HttpRequest.makeInList(message, URL.getUserRoles);
    }

}
