package Controllers.GuestCard;

import javafx.fxml.FXML;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextField;

import java.awt.*;

/**
 * Created by root on 15.07.15.
 */
public class changeGuestCardController {
    @FXML
    private TextField changeGuestCardNumber;
    @FXML
    private TextField changeGuestCardName;
    @FXML
    private MenuButton changeGuestCardStatus;

    @FXML
    public void changeGuestCardStatusActiveBtnAction(){
        changeGuestCardStatus.setText("Активна");
    }
    @FXML
    public void changeGuestCardStatusDisactiveBtnAction(){
        changeGuestCardStatus.setText("Неактивна");
    }
    @FXML
    public void changeGuestCardStatusUnknownBtnAction(){
        changeGuestCardStatus.setText("Неизвестно");
    }

    @FXML
    public void changeGuestCardChangeBtnAction(){
        String number = changeGuestCardNumber.getText();
        String status;
        String name;
        if(number.isEmpty()){
        }else {
            //http
            GuestCardController.changeStage.close();
        }
    }

    @FXML
    public void changeGuestCardFindBtnAction(){
        String number = changeGuestCardNumber.getText();
        if(number.isEmpty()){
        }else {
            //http
        }
    }
}
