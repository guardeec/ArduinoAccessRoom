package tests.admin.GuestAdmin;

import tests.methods.HttpRequest;
import tests.methods.URL;

import java.util.List;
import java.util.Map;

/**
 * Created by root on 01.10.15.
 */
public class guestMethods {

    public static Map<String, String> addGuestCard(String name, String cardId){
        String message =    "adminName="+ URL.adminName
                            +"&adminPassword="+URL.password
                            +"&name="+name
                            +"&cardId="+cardId
                            ;
        return HttpRequest.makeInMap(message, URL.addGuest);
    }

    public static Map<String, String> changeGuestCard(String name, String id){
        String message =    "adminName="+ URL.adminName
                            +"&adminPassword="+URL.password
                            +"&name="+name
                            +"&id="+id
                            ;
        return HttpRequest.makeInMap(message, URL.changeGuest);
    }

    public static Map<String, String> deleteGuestCard(String guestId){
        String message =    "adminName="+ URL.adminName
                            +"&adminPassword="+URL.password
                            +"&guestId="+guestId
                            ;
        return HttpRequest.makeInMap(message, URL.deleteGuest);
    }

    public static List<Map> getGuestCard(String name, String date, String cardId){
        String message =    "adminName="+ URL.adminName
                            +"&adminPassword="+URL.password
                            +"&name="+name
                            +"&date="+date
                            +"&cardId="+cardId
                            ;
        return HttpRequest.makeInList(message, URL.getGuest);
    }

    public static List<Map> getHistory(String name, String date){
        String message =    "adminName="+ URL.adminName
                +"&adminPassword="+URL.password
                +"&name="+name
                +"&date="+date
                ;
        return HttpRequest.makeInList(message, URL.getHistory);
    }

    public static void deleteExpiredCards(){
        String message =    "adminName="+ URL.adminName
                +"&adminPassword="+URL.password
                ;
        HttpRequest.makeInMap(message, URL.deleteExpiredCards);
    }

    public static List<Map> checkFreeCards(){
        String message =    "adminName="+ URL.adminName
                            +"&adminPassword="+URL.password
                            ;
        return HttpRequest.makeInList(message, URL.getFreeGuestCards);
    }

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

}
