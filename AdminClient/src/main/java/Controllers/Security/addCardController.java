package Controllers.Security;

import Controllers.Methods.AdminType;
import Controllers.Methods.URL;
import Controllers.Methods.httpRequest;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.util.Map;

/**
 * Created by root on 18.09.15.
 */
public class addCardController {
    @FXML
    TextField addSecurityCardNumber;

    @FXML
    public void addSecurityAddCardBtnAction(){
        String number = addSecurityCardNumber.getText();

        if (number.isEmpty()){
        }else {
            AdminType adminType = AdminType.getInstance();
            String adminName = adminType.getLogin();
            String adminPassword = adminType.getPassword();
            String message =    "adminName="+adminName
                    +"&adminPassword="+adminPassword
                    +"&cardNumber="+number
                    ;
            Map<String, String> answer = httpRequest.makeInMap(message, URL.addCard);
            if (answer.get("message").contains("Success")){
                SecurityController.addCardStage.close();
            }else {
                addSecurityCardNumber.setText("Ошибка сервера");
            }

        }
    }
}
