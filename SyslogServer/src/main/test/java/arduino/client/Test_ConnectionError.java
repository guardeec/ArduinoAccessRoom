package arduino.client;

import Data.SyslogData;
import Methods.PostRequest;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by root on 29.10.15.
 */
public class Test_ConnectionError {
    @Test
    public void test_connectionError(){
        String message = PostRequest.makeHeader(SyslogData.getConnectionErrorDATA(), SyslogData.URL_SYSLOG);
        if (message.contains("FAIL")){
            Assert.fail();
        }
    }
}
