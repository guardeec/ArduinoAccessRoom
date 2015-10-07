package tests.admin.UserAdmin;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Map;

/**
 * Created by root on 01.10.15.
 */
public class Test_changeUser {
    private final String cardNumber = "111Q";
    private final String name = "Test";
    private String userId = null;
    private String cardId = null;
    private String roleId = null;

    @Before
    public void init(){
        cardId = userMethods.addCard(cardNumber).get("id");
        roleId = userMethods.getRoles().get(0).get("id").toString();
        userMethods.addUser(name,cardId,roleId);
    }

    @Test
    public void test_changeUser(){
        userId = userMethods.getUser("", name, roleId).get(0).get("id").toString();
        Map<String , String> answer = userMethods.changeUser(name, userId, roleId);
        if (!answer.get("message").contains("Success")){
            Assert.fail(this.getClass().getName());
        }
    }

    @After
    public void clear(){
        userMethods.deleteUser(userId);
        userMethods.deleteCard(cardId);
    }
}
