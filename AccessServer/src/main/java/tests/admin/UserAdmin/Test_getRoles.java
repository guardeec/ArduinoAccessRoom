package tests.admin.UserAdmin;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;
import java.util.Map;

/**
 * Created by root on 01.10.15.
 */
public class Test_getRoles {
    @Test
    public void test_getRoles(){
        List<Map> answer = userMethods.getRoles();
        if (!answer.get(answer.size()-1).get("message").toString().contains("Success")){
            Assert.fail(this.getClass().getName());
        }
    }
}
