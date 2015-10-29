package arduino.client;

import Data.SyslogData;
import Methods.PostRequest;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by root on 29.10.15.
 */
public class Test_RoomEnterOrOut {
    @Test
    public void test_roomEnterOrOut(){
        String message = PostRequest.makeHeader(SyslogData.getRoomDATA(), SyslogData.URL_SYSLOG);
        if (message.contains("FAIL")){
            Assert.fail();
        }
    }
}
