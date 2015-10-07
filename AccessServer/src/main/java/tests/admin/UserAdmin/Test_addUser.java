package tests.admin.UserAdmin;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Map;

/**
 * Created by root on 01.10.15.
 */
public class Test_addUser {

    private final String cardNumber = "111Q";
    private final String name = "Test";
    private String userId = null;
    private String cardId = null;
    private String roleId = null;

    @Before
    public void init(){
        cardId = userMethods.addCard(cardNumber).get("id");
        roleId = userMethods.getRoles().get(0).get("id").toString();
    }

    @Test
    public void test_addUser(){
        Map<String , String> answer = userMethods.addUser(name,cardId,roleId);
        if (!answer.get("message").contains("Success")){
            Assert.fail(this.getClass().getName());
        }
    }

    @After
    public void clear(){
        userId = userMethods.getUser("", name, roleId).get(0).get("id").toString();
        userMethods.deleteUser(userId);
        userMethods.deleteCard(cardId);
    }

}
