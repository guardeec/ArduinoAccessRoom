package tests.arduino;

import org.junit.Assert;
import org.junit.Test;
import tests.methods.HttpRequest;
import tests.methods.URL;

import java.util.List;
import java.util.Map;

/**
 * Created by root on 01.10.15.
 */
public class Test_getAccess {

    @Test
    public void test_getFalseAccess() {
        String message =    "device_id=1"
                            +"&card=45216";
        Map<String, String> answer = HttpRequest.makeInMap(message, URL.getAccess);
        if (answer.get("access").compareTo("true")==0) {
            Assert.fail(this.getClass().getName());
        }
    }
}
