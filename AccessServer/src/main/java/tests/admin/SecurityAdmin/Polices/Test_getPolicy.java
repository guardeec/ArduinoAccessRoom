package tests.admin.SecurityAdmin.Polices;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import tests.admin.DeviceAdmin.deviceMethods;
import tests.admin.SecurityAdmin.Roles.rolesMethods;

import java.util.List;
import java.util.Map;

/**
 * Created by root on 02.10.15.
 */
public class Test_getPolicy {
    private final String ip = "IP_Test";
    private final String specification = "Device_Test";
    private String deviceId = null;

    private final String roleTitle = "testRole";
    private String roleId = null;

    @Before
    public void init(){
        deviceMethods.addDevice(ip, specification);
        deviceId = deviceMethods.getDevice(ip, specification, "").get(0).get("id").toString();

        roleId = rolesMethods.addRole(roleTitle).get("id");
    }

    @Test
    public void test_changePolicyOnDevice(){
        List<Map> answer = policesMethods.getPolicy(deviceId,roleId);
        if (!answer.get(answer.size()-1).get("message").toString().contains("Success")){
            Assert.fail(this.getClass().getName());
        }
    }

    @After
    public void clear(){
        deviceMethods.deleteDevice(deviceId);
        rolesMethods.deleteRole(roleId);
    }
}
