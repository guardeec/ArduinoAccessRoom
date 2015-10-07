package tests.arduino;

import org.junit.Assert;
import org.junit.Test;
import tests.methods.HttpRequest;
import tests.methods.URL;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by root on 01.10.15.
 */
public class Test_cashAdminDB {
    @Test
    public void test_cashAdminDB() {
        String message = "device_id=1";
        List<Map> answer = HttpRequest.makeInList(message, URL.cashAdminDB);
        if (!answer.get(answer.size() - 1).get("message").toString().contains("Success")) {
            Assert.fail(this.getClass().getName());
        }
    }
}
