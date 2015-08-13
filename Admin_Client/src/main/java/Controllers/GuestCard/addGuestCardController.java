package Controllers.GuestCard;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

/**
 * Created by root on 15.07.15.
 */
public class addGuestCardController {
    @FXML
    private TextField addGuestCardNumber;

    @FXML
    public void addGuestCardAddBtnAction(){
        String number = addGuestCardNumber.getText();
        if (number.isEmpty()){

        }else {
            //http
            GuestCardController.addStage.close();
        }
    }
}
