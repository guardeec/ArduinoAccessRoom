package admin.client.enterAndOutAdmin;

import Data.SyslogData;
import Methods.PostRequest;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by root on 07.10.15.
 */
public class Test_enterOrOutAdmin {
    @Test
    public void test_enterAdmin(){
        String message = PostRequest.makeHeader(SyslogData.getAdminClientSessionDATA(), SyslogData.URL_SYSLOG);
        if (message.contains("FAIL")){
            Assert.fail();
        }
    }
}
