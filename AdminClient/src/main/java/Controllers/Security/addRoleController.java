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
public class addRoleController {
    @FXML
    TextField addSecurityRole;

    @FXML
    public void addSecurityAddBtnAction(){
        String name = addSecurityRole.getText();

        if (name.isEmpty()){
        }else {
            AdminType adminType = AdminType.getInstance();
            String adminName = adminType.getLogin();
            String adminPassword = adminType.getPassword();
            String message =    "adminName="+adminName
                    +"&adminPassword="+adminPassword
                    +"&title="+name
                    ;
            Map<String, String> answer = httpRequest.makeInMap(message, URL.addRole);
            if (answer.get("message").contains("Success")){
                SecurityController.addRoleStage.close();
            }else {
                addSecurityRole.setText("Ошибка сервера");
            }

        }

    }


}
