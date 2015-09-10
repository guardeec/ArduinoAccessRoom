package Controllers.GuestCard;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

/**
 * Created by root on 15.07.15.
 */
public class deleteGuestCardController {
    @FXML
    TextField deleteGuestCardNumber;
    @FXML
    TextField deleteGuestCardStatus;
    @FXML
    TextField deleteGuestCardName;

    @FXML
    public void deleteGuestCardDeleteBtnAction(){
        String number = deleteGuestCardNumber.getText();
        if (number.isEmpty()){
        }else {
            //http
            GuestCardController.deleteStage.close();
        }
    }

    @FXML
    public void deleteGuestCardFindBtnAction(){
        String number = deleteGuestCardNumber.getText();
        if (number.isEmpty()) {
        }else {
            //http
        }
    }
}
