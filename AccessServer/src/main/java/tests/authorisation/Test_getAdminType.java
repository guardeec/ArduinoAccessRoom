package tests.authorisation;

import org.junit.Assert;
import org.junit.Test;
import tests.methods.HttpRequest;
import tests.methods.URL;

import java.util.Map;

/**
 * Created by root on 01.10.15.
 */
public class Test_getAdminType {

    @Test
    public void test_getTrueAdminType(){
        String message =    "adminName="+URL.adminName
                            +"&adminPasswordHash="+URL.password;
        Map<String, String> answer = HttpRequest.makeInMap(message, URL.getAdminType);
        if (answer.get("message").compareTo("false")==0){
            Assert.fail(this.getClass().getName());
        }
    }

    @Test
    public void test_getFalseAdminType(){
        String message =    "adminName="+URL.adminName+"1"
                +"&adminPasswordHash="+URL.password;
        Map<String, String> answer = HttpRequest.makeInMap(message, URL.getAdminType);
        if (answer.get("message").compareTo("true")==0){
            Assert.fail(this.getClass().getName());
        }
    }


}
