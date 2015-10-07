package tests.authorisation;

import org.junit.Assert;
import org.junit.Test;
import tests.methods.HttpRequest;
import tests.methods.URL;

import java.util.Map;

/**
 * Created by root on 01.10.15.
 */
public class Test_checkServerRunnable {
    @Test
    public void test_checkRunnable_Server(){
        Map<String, String> answer = HttpRequest.makeInMap("", URL.checkRunnable);
        if (!answer.get("Server").contains("true")){
            Assert.fail("Сервер недоступен");
        }
    }
}
