package Controllers.Security;

import Controllers.Methods.AdminType;
import Controllers.Methods.URL;
import Controllers.Methods.httpRequest;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

import java.util.Map;

/**
 * Created by root on 15.07.15.
 */
public class deleteRoleController {
    @FXML
    TextField deleteSecurityRoleId;

    @FXML
    public void deleteSecurityDeleteBtnAction(){
        String id = deleteSecurityRoleId.getText();
        if (id.isEmpty()){
        }
        else {
            AdminType adminType = AdminType.getInstance();
            String adminName = adminType.getLogin();
            String adminPassword = adminType.getPassword();
            String message =    "adminName="+adminName
                    +"&adminPassword="+adminPassword
                    +"&id="+id
                    ;
            Map<String, String> answer = httpRequest.makeInMap(message, URL.deleteRole);
            if (answer.get("message").contains("Success")){
                SecurityController.deleteRoleStage.close();
            }else {
                deleteSecurityRoleId.setText("Ошибка сервера");
            }
        }
    }
}
