package tests.admin.GuestAdmin;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Map;

/**
 * Created by root on 01.10.15.
 */
public class Test_changeGuestCard {
    private final String cardNumber = "111Q";
    private final String name = "Test";
    private String cardId = null;
    private String guestId = null;

    @Before
    public void addGuest(){
        cardId = guestMethods.addCard(cardNumber).get("id");
        guestMethods.addGuestCard(name, cardId);
        guestId = guestMethods.getGuestCard(name, "", cardId).get(0).get("id").toString();
    }

    @Test
    public void test_changeGuestCard(){
        Map<String, String> answer = guestMethods.changeGuestCard("Test2", guestId);
        if (!answer.get("message").contains("Success")){
            Assert.fail(this.getClass().getName());
        }
    }

    @After
    public void clear(){
        guestMethods.deleteGuestCard(guestId);
        guestMethods.deleteCard(cardId);
    }
}
