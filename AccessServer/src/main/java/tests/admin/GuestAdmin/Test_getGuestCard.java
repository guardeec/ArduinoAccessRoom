package tests.admin.GuestAdmin;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Map;

/**
 * Created by root on 01.10.15.
 */
public class Test_getGuestCard {
    @Test
    public void test_getGuestCard (){
        List<Map> answer = guestMethods.getGuestCard("", "", "");
        if (!answer.get(answer.size()-1).get("message").toString().contains("Success")){
            Assert.fail(this.getClass().getName());
        }
    }
}
