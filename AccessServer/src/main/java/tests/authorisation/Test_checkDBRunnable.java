package tests.authorisation;

import org.junit.Assert;
import org.junit.Test;
import tests.methods.HttpRequest;
import tests.methods.URL;

import java.util.Map;

/**
 * Created by root on 01.10.15.
 */
public class Test_checkDBRunnable {
    @Test
    public void test_checkRunnable_DB(){
        Map<String, String> answer = HttpRequest.makeInMap("", URL.checkRunnable);
        if (!answer.get("DB").contains("true")){
            Assert.fail("БД недоступна");
        }

    }
}
