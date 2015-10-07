package tests.admin.SecurityAdmin.Cards;

import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

import java.util.Map;

/**
 * Created by root on 02.10.15.
 */
public class Test_addCard {
    private final String cardNumber = "111Q";
    private String cardId = null;

    @Test
    public void test_addCard(){
        Map<String,String> answer = cardsMethods.addCard(cardNumber);
        if (!answer.get("message").contains("Success")){
            Assert.fail(this.getClass().getName());
        }
        cardId = answer.get("id");
    }

    @After
    public void clear(){
        cardsMethods.deleteCard(cardId);
    }
}
