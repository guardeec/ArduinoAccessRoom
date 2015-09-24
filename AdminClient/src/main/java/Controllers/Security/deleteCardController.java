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
public class deleteCardController {
    @FXML
    TextField deleteSecurityCard;

    @FXML
    public void deleteSecurityCardBtnAction(){
        String id = deleteSecurityCard.getText();
        if (id.isEmpty()){

        }else {
            AdminType adminType = AdminType.getInstance();
            String adminName = adminType.getLogin();
            String adminPassword = adminType.getPassword();
            String message =    "adminName="+adminName
                    +"&adminPassword="+adminPassword
                    +"&cardId="+id
                    ;
            Map<String, String> answer = httpRequest.makeInMap(message, URL.deleteCard);
            if (answer.get("message").contains("Success")){
                SecurityController.deleteCardStage.close();
            }else {
                deleteSecurityCard.setText("Ошибка сервера");
            }

        }
    }
}
