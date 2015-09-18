package Controllers.GuestCard;

import Controllers.Methods.AdminType;
import Controllers.Methods.URL;
import Controllers.Methods.httpRequest;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.util.List;
import java.util.Map;

/**
 * Created by root on 15.07.15.
 */
public class deleteGuestCardController {
    @FXML
    private TextField deleteGuestId;

    @FXML
    public void deleteGuestCardDeleteBtnAction(){
        String number = deleteGuestId.getText();
        if (number.isEmpty()){
        }else {
            AdminType adminType = AdminType.getInstance();
            String adminName = adminType.getLogin();
            String adminPassword = adminType.getPassword();
            String message =    "adminName="+adminName
                    +"&adminPassword="+adminPassword
                    +"&guestId="+number
                    ;
            List<Map> result = httpRequest.makeInList(message, URL.deleteGuest);
            Map<String, String> answer = result.get(result.size()-1);
            if (answer.get("message").contains("Success")){
                GuestCardController.deleteStage.close();
            }else {
                deleteGuestId.setText("Ошибка сервера");
            }
        }
    }

}
