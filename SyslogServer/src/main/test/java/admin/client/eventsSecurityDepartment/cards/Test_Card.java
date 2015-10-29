package admin.client.eventsSecurityDepartment.cards;

import Data.SyslogData;
import Methods.PostRequest;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by root on 07.10.15.
 */
public class Test_Card {
    @Test
    public void test_addCard(){
        String message = PostRequest.makeHeader(SyslogData.getCardDATA(), SyslogData.URL_SYSLOG);
        if (message.contains("FAIL")){
            Assert.fail();
        }
    }
}
