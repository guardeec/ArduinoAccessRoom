package tests.admin.SecurityAdmin.Cards;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Map;

/**
 * Created by root on 02.10.15.
 */
public class Test_deleteCard {
    private final String cardNumber = "111Q";
    private String cardId = null;

    @Before
    public void init(){
        cardId = cardsMethods.addCard(cardNumber).get("id");
    }

    @Test
    public void test_deleteCard(){
        Map<String,String> answer = cardsMethods.deleteCard(cardId);
        if (!answer.get("message").contains("Success")){
            Assert.fail(this.getClass().getName());
        }
    }

}
