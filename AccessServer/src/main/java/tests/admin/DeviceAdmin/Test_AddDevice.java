package tests.admin.DeviceAdmin;

import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import tests.methods.HttpRequest;
import tests.methods.URL;

import java.util.List;
import java.util.Map;

/**
 * Created by root on 01.10.15.
 */
public class Test_AddDevice {

    private final String ip = "test_IP_DA";
    private final String specification = "test_SP_DA";

    @Test
    public void test_addDevice(){
        Map <String, String> answer = deviceMethods.addDevice(ip, specification);
        if (!answer.get("message").contains("Success")){
            Assert.fail(this.getClass().getName());
        }
    }

    @After
    public void clean(){
        List<Map> id = deviceMethods.getDevice(ip, specification, "");
        deviceMethods.deleteDevice(id.get(0).get("id").toString());
    }

}
