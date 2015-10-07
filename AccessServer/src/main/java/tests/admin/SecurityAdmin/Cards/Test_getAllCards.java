package tests.admin.SecurityAdmin.Cards;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Map;

/**
 * Created by root on 02.10.15.
 */
public class Test_getAllCards {

    @Test
    public void test_deleteCard(){
        List<Map> answer = cardsMethods.getAllCards();
        if (!answer.get(answer.size()-1).get("message").toString().contains("Success")){
            Assert.fail(this.getClass().getName());
        }
    }
}
