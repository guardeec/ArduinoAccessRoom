package admin.client.eventsSecurityDepartment.roles;

import Data.SyslogData;
import Methods.PostRequest;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by root on 07.10.15.
 */
public class Test_Role {
    @Test
    public void test_addRole(){
        String message = PostRequest.makeHeader(SyslogData.getRoleDATA(), SyslogData.URL_SYSLOG);
        if (message.contains("FAIL")){
            Assert.fail();
        }
    }
}
