package tests.admin.UserAdmin;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;
import java.util.Map;

/**
 * Created by root on 01.10.15.
 */
public class Test_getUser {
    @Test
    public void test_getUser(){
        List<Map> answer = userMethods.getUser("", "", "");
        if (!answer.get(answer.size()-1).get("message").toString().contains("Success")){
            Assert.fail(this.getClass().getName());
        }
    }
}
