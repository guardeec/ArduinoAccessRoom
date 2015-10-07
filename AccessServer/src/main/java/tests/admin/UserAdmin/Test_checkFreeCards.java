package tests.admin.UserAdmin;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;
import java.util.Map;

/**
 * Created by root on 01.10.15.
 */
public class Test_checkFreeCards {
    @Test
    public void test_checkFreeCards(){
        List<Map> answer = userMethods.getFreeCards();
        if (!answer.get(answer.size()-1).get("message").toString().contains("Success")){
            Assert.fail(this.getClass().getName());
        }
    }
}
