package admin.client.eventsSecurityDepartment.accounts;

import admin.client.adminClientMethods;
import org.junit.Assert;
import org.junit.Test;

import java.sql.Date;
import java.util.Map;

/**
 * Created by root on 07.10.15.
 */
public class Test_changeAccount {
    @Test
    public void test_changeAccount(){
        Map<String, String> answer = adminClientMethods.accountLog("test_ip", "test_mac", "test_login", "test_id", "1", "test_login", "test_id", "", "test_role", new Date(System.currentTimeMillis()).toString(), "1");
        if (!answer.get("message").contains("Success")){
            Assert.fail(this.getClass().getName());
        }
    }

}
