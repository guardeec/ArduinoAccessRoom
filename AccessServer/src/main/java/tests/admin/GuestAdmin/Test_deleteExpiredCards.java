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
public class Test_deleteExpiredCards {
    private String cardId = null;

    @Before
    public void init(){
        cardId = guestMethods.addCard("111Q").get("id");
        guestMethods.addGuestCard("ExpiredGuest", cardId);
    }

    @Test
    public void test_deleteExpiredCards(){
        guestMethods.deleteExpiredCards();
        List<Map> answer = guestMethods.getGuestCard("", "", "");
        if (answer.size()>1){
            Assert.fail(this.getClass().getName());
        }
    }

    @After
    public void clear(){
        guestMethods.deleteCard(cardId);
    }
}
