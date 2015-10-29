package admin.client.eventsSecurityDepartment.rights;

import Data.SyslogData;
import Methods.PostRequest;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by root on 07.10.15.
 */
public class Test_changeAccessOnDevice {
    @Test
    public void test_changeAccessOnDevice(){
        String message = PostRequest.makeHeader(SyslogData.getRightDATA(), SyslogData.URL_SYSLOG);
        if (message.contains("FAIL")){
            Assert.fail();
        }
    }
}
