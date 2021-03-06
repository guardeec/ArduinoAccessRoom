package tests.admin.SecurityAdmin.Roles;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Map;

/**
 * Created by root on 02.10.15.
 */
public class Test_changeRole {
    private final String roleTitle = "testRole";
    private String roleId = null;

    @Before
    public void init(){
        roleId = rolesMethods.addRole(roleTitle).get("id");
    }

    @Test
    public void test_addRole(){
        Map<String,String> answer = rolesMethods.changeRole(roleTitle+"1",roleId);
        if (!answer.get("message").contains("Success")){
            Assert.fail(this.getClass().getName());
        }
    }

    @After
    public void clean(){
        rolesMethods.deleteRole(roleId);
    }
}
