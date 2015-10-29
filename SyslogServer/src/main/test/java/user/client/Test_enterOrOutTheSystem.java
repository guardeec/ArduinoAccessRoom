package user.client;

import Data.SyslogData;
import Methods.PostRequest;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by root on 07.10.15.
 */
public class Test_enterOrOutTheSystem {

    @Test
    public void test_enterTheSystem(){
        String message = PostRequest.makeHeader(SyslogData.getUserClientSessionDATA(), SyslogData.URL_SYSLOG);
        if (message.contains("FAIL")){
            Assert.fail();
        }
    }
}
