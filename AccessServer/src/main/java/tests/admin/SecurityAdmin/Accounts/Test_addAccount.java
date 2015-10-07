package tests.admin.SecurityAdmin.Accounts;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import tests.admin.SecurityAdmin.Cards.cardsMethods;
import tests.admin.UserAdmin.userMethods;

import java.util.Map;

/**
 * Created by root on 02.10.15.
 */
public class Test_addAccount {

    private final String cardNumber = "111Q";
    private final String userName = "TestUser4AddAccount";
    private final String login = "TestLogin4AddAccount";
    private final String password = "TestPassword4AddAccount";


    private String cardId = null;
    private String roleId = null;
    private String userId = null;
    private String accountId = null;

    @Before
    public void init(){
        cardId = userMethods.addCard(cardNumber).get("id");
        roleId = userMethods.getRoles().get(0).get("id").toString();
        userMethods.addUser(userName,cardId,roleId);
        userId = userMethods.getUser("", userName, roleId).get(0).get("id").toString();
    }

    @Test
    public void test_addAccount(){
        Map<String, String> answer = accountMethods.addAccount(login,password,userId,"true","","","");
        accountId = accountMethods.getAccount("", "", login).get(0).get("id").toString();
        if (!answer.get("message").contains("Success")){
            Assert.fail(this.getClass().getName());
        }
    }

    @After
    public void clear(){
        accountMethods.deleteAccount(accountId);
        userMethods.deleteUser(userId);
        cardsMethods.deleteCard(cardId);
    }

}
