package tests.admin.GuestAdmin;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Map;

/**
 * Created by root on 01.10.15.
 */
public class Test_addGuestCard {
    private final String cardNumber = "111Q";
    private final String name = "Test";
    private String cardId = null;
    private String guestId = null;

    @Before
    public void addCard(){
        cardId = guestMethods.addCard(cardNumber).get("id");
    }

    @Test
    public void test_addGuestCard(){
        Map<String, String> answer = guestMethods.addGuestCard(name, cardId);
        if (!answer.get("message").contains("Success")){
            Assert.fail(this.getClass().getName());
        }
    }

    @After
    public void deleteCard(){
        guestId = guestMethods.getGuestCard(name, "", cardId).get(0).get("id").toString();
        guestMethods.deleteGuestCard(guestId);
        guestMethods.deleteCard(cardId);
    }
}
