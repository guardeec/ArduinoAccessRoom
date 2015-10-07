package tests.admin.DeviceAdmin;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Map;

/**
 * Created by root on 01.10.15.
 */
public class Test_ChangeDevice {

    private final String ip = "6.6.6.6";
    private final String specification = "test";

    @Before
    public void addDevice(){
        deviceMethods.addDevice(ip,specification);
    }

    @Test
    public void test_changeDevice(){
        List<Map> id = deviceMethods.getDevice(ip, specification, "");
        Map<String, String> answer = deviceMethods.changeDevice(id.get(0).get("id").toString(), ip, specification);
        if (!answer.get("message").contains("Success")){
            Assert.fail(this.getClass().getName());
        }
    }

    @After
    public void deleteDevice(){
        List<Map> id = deviceMethods.getDevice(ip, specification, "");
        deviceMethods.deleteDevice(id.get(0).get("id").toString());
    }
}
