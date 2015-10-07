package Controllers.Security.Accounts;

import Controllers.Methods.AdminType;
import Controllers.Methods.URL;
import Controllers.Methods.httpRequest;
import Controllers.Security.accountsController;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.util.List;
import java.util.Map;

/**
 * Created by root on 25.09.15.
 */
public class DeleteAccountController {
    @FXML
    TextField id;

    @FXML
    public void deleteBtnAction(){
        if (id.getText().isEmpty()){
            id.setText("Введите id");
        }else {
            AdminType adminType = AdminType.getInstance();
            String adminName = adminType.getLogin();
            String adminPassword = adminType.getPassword();
            String message =    "adminName="+adminName
                    +"&adminPassword="+adminPassword
                    +"&id="+id.getText()
                    ;
            List<Map> result = httpRequest.makeInList(message, URL.deleteAccount);
            Map<String, String> answer = result.get(result.size() - 1);
            if (answer.get("message").contains("Success")){
                accountsController.deleteStage.close();
            }else {
                id.setText("Ошибка сервера");
            }
        }
    }
}
