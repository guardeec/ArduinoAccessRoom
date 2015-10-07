package tests.admin.SecurityAdmin.Accounts;

import tests.methods.HttpRequest;
import tests.methods.URL;

import java.util.List;
import java.util.Map;

/**
 * Created by root on 02.10.15.
 */
public class accountMethods {

    public static Map<String, String> addAccount(String login, String password, String userId, String technical, String reception, String hr, String security){
        String message =    "adminName="+ URL.adminName
                            +"&adminPassword="+URL.password
                            +"&login="+login
                            +"&password="+password
                            +"&userId="+userId
                            + "&technical=" + technical
                            + "&reception=" + reception
                            + "&hr=" + hr
                            + "&security=" + security
                ;
        return HttpRequest.makeInMap(message, URL.addAccount);
    }

    public static Map<String, String> changeAccount(String id, String login, String password, String technical, String reception, String hr, String security){
        String message =    "adminName="+ URL.adminName
                +"&adminPassword="+URL.password
                + "&id=" + id
                + "&login=" + login
                + "&password=" + password
                + "&technical=" + technical
                + "&reception=" + reception
                + "&hr=" + hr
                + "&security=" + security
                ;
        return HttpRequest.makeInMap(message, URL.changeAccount);
    }

    public static Map<String, String> deleteAccount(String accountId){
        String message =    "adminName="+ URL.adminName
                +"&adminPassword="+URL.password
                + "&id=" + accountId
                ;
        return HttpRequest.makeInMap(message, URL.deleteAccount);
    }

    public static List<Map> getAccount(String userId, String accountId, String login){
        String message =    "adminName="+ URL.adminName
                            +"&adminPassword="+URL.password
                            +"&employee_id="+userId
                            +"&id="+accountId
                            +"&login="+login
                ;
        return HttpRequest.makeInList(message, URL.getAccounts);
    }

}
