package admin.client.eventsTechnicalDepartment;

import Data.SyslogData;
import Methods.PostRequest;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by root on 07.10.15.
 */
public class Test_Device {
    @Test
    public void test_addDevice(){
        String message = PostRequest.makeHeader(SyslogData.getDeviceDATA(), SyslogData.URL_SYSLOG);
        if (message.contains("FAIL")){
            Assert.fail();
        }
    }
}
