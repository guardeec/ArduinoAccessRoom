package tests.admin.SecurityAdmin.Devices;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;
import java.util.Map;

/**
 * Created by root on 02.10.15.
 */
public class Test_getDevice {
    @Test
    public void test_getDevices(){
        List<Map> answer = devicesMethods.getDevice();
        if (!answer.get(answer.size()-1).get("message").toString().contains("Success")){
            Assert.fail(this.getClass().getName());
        }
    }
}
