package user.client.enterAndOutTheSystem;

import org.junit.Assert;
import org.junit.Test;
import user.client.userClientMethods;

import java.sql.Date;
import java.util.Map;

/**
 * Created by root on 07.10.15.
 */
public class Test_enterTheSystem {

    public void test_enterTheSystem(){
        Map<String, String> answer = userClientMethods.enterOrOutTheSystemLog("test_ip", "test_mac", "test_id", "1", new Date(System.currentTimeMillis()).toString());
        if (!answer.get("message").contains("Success")){
            Assert.fail(this.getClass().getName());
        }
    }
}
