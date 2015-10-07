package tests.admin.GuestAdmin;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Map;

/**
 * Created by root on 01.10.15.
 */
public class Test_deleteGuestCard {
    private final String cardNumber = "111Q";
    private String cardId = null;
    private final String name = "Test";
    private String guestId = null;

    @Before
    public void init(){
        cardId = guestMethods.addCard(cardNumber).get("id");
        guestMethods.addGuestCard(name, cardId);
        guestId = guestMethods.getGuestCard(name, "", cardId).get(0).get("id").toString();
    }

    @Test
    public void test_deleteGuestCard(){
        Map<String, String> answer = guestMethods.deleteGuestCard(guestId);
        if (!answer.get("message").contains("Success")){
            Assert.fail(this.getClass().getName());
        }
    }

    @After
    public void clear(){
        guestMethods.deleteCard(cardId);
    }
}
