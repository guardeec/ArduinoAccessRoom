package tests.admin.SecurityAdmin.Cards;

import tests.methods.HttpRequest;
import tests.methods.URL;

import java.util.List;
import java.util.Map;

/**
 * Created by root on 02.10.15.
 */
public class cardsMethods {
    public static Map<String,String> addCard(String cardNumber){
        String message =    "adminName="+ URL.adminName
                            +"&adminPassword="+URL.password
                            +"&cardNumber="+cardNumber
                            ;
        return HttpRequest.makeInMap(message, URL.addCard);
    }

    public static Map<String,String> deleteCard(String cardId){
        String message =    "adminName="+ URL.adminName
                            +"&adminPassword="+URL.password
                            +"&cardId="+cardId
                            ;
        return HttpRequest.makeInMap(message, URL.deleteCard);
    }

    public static List<Map> getAllCards(){
        String message =    "adminName="+ URL.adminName
                            +"&adminPassword="+URL.password
                            ;
        return HttpRequest.makeInList(message, URL.getFreeSecurityCards);
    }
}
