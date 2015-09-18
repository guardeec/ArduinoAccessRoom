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
public class addGuestCardController {
    @FXML
    private TextField addGuestCardNumber;
    @FXML
    private TextField addGuestName;

    @FXML
    public void addGuestCardAddBtnAction(){
        String number = addGuestCardNumber.getText();
        String name = addGuestName.getText();
        if (number.isEmpty() || name.isEmpty()){
        }else {
            AdminType adminType = AdminType.getInstance();
            String adminName = adminType.getLogin();
            String adminPassword = adminType.getPassword();
            String message =    "adminName="+adminName
                    +"&adminPassword="+adminPassword
                    ;
            List<Map> answer = httpRequest.makeInList(message, URL.getFreeCards);
            message = (String) answer.get(answer.size()-1).get("message");
            if (message.contains("Success")){
                boolean freeCardAvailable = false;
                for (int i=0; i<answer.size()-1; i++){
                    String cardId = (String) answer.get(i).get("id");
                    if (cardId.compareTo(number)==0){
                        freeCardAvailable = true;
                    }
                }

                if (freeCardAvailable){
                    message =    "adminName="+adminName
                                +"&adminPassword="+adminPassword
                                +"&name="+name
                                +"&cardId="+number
                    ;
                    Map<String, String> result = httpRequest.makeInMap(message, URL.addGuest);
                    if (result.get("message").contains("Success")){
                        GuestCardController.addStage.close();
                    }else {
                        addGuestCardNumber.setText(result.get("message"));
                        addGuestName.setText(result.get("message"));
                    }
                }else {
                    addGuestCardNumber.setText("Выберите другую карту");
                }
            }else {
                addGuestCardNumber.setText("Ошибка сервера");
                addGuestName.setText("Ошибка сервера");
            }
        }
    }
}
