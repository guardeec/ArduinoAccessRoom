package tests.admin.GuestAdmin;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Map;

/**
 * Created by root on 01.10.15.
 */
public class Test_getHistory {

    @Test
    public void test_getHistory(){
        List<Map> answer = guestMethods.getHistory("", "");
        if (!answer.get(answer.size()-1).get("message").toString().contains("Success")){
            Assert.fail(this.getClass().getName());
        }
    }
}
