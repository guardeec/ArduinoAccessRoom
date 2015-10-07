package tests.admin.SecurityAdmin.Roles;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Map;

/**
 * Created by root on 02.10.15.
 */
public class Test_getRole {

    @Test
    public void test_addRole(){
        List<Map> answer = rolesMethods.getRole("","");
        if (!answer.get(answer.size()-1).get("message").toString().contains("Success")){
            Assert.fail(this.getClass().getName());
        }
    }
}
