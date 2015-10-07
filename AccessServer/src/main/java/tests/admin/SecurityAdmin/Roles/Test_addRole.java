package tests.admin.SecurityAdmin.Roles;

import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

import java.util.Map;

/**
 * Created by root on 02.10.15.
 */
public class Test_addRole {
    private final String roleTitle = "testRole";
    private String roleId = null;

    @Test
    public void test_addRole(){
        Map<String,String> answer = rolesMethods.addRole(roleTitle);
        if (!answer.get("message").contains("Success")){
            Assert.fail(this.getClass().getName());
        }
        roleId = answer.get("id");
    }

    @After
    public void clean(){
        rolesMethods.deleteRole(roleId);
    }
}
