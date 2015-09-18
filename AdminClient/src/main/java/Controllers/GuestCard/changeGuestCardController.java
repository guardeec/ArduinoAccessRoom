package Controllers.GuestCard;

import Controllers.Methods.AdminType;
import Controllers.Methods.URL;
import Controllers.Methods.httpRequest;
import javafx.fxml.FXML;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextField;

import java.awt.*;
import java.util.*;
import java.util.List;

/**
 * Created by root on 15.07.15.
 */
public class changeGuestCardController {
    @FXML
    private TextField changeGuestName;
    @FXML
    private TextField changeGuestId;

    @FXML
    public void changeGuestCardChangeBtnAction(){
        String name = changeGuestName.getText();
        String id = changeGuestId.getText();
        if(name.isEmpty() || id.isEmpty()){
        }else {
            AdminType adminType = AdminType.getInstance();
            String adminName = adminType.getLogin();
            String adminPassword = adminType.getPassword();
            String message =    "adminName="+adminName
                    +"&adminPassword="+adminPassword
                    +"&id="+id
                    +"&name="+name
                    ;
            List<Map> result = httpRequest.makeInList(message, URL.changeGuest);
            Map<String, String> answer = result.get(result.size()-1);
            if (answer.get("message").contains("Success")){
                GuestCardController.changeStage.close();
            }else {
                changeGuestName.setText("Ошибка сервера");
                changeGuestId.setText("Ошибка сервера");
            }

        }
    }

}
