package Controllers.Security;

import Controllers.Methods.AdminType;
import Controllers.Methods.URL;
import Controllers.Methods.httpRequest;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.util.Map;

/**
 * Created by root on 15.07.15.
 */
public class changeRoleController {
    @FXML
    private TextField changeSecurityRoleId;

    @FXML
    private TextField changeSecurityRoleTitle;

    @FXML
    public void changeSecurityChangeBtnAction(){
        String id = changeSecurityRoleId.getText();
        String title = changeSecurityRoleTitle.getText();

        if (id.isEmpty() || title.isEmpty()){
        }else {
            AdminType adminType = AdminType.getInstance();
            String adminName = adminType.getLogin();
            String adminPassword = adminType.getPassword();
            String message =    "adminName="+adminName
                    +"&adminPassword="+adminPassword
                    +"&title="+title
                    +"&roleId="+id
                    ;
            Map<String, String> answer = httpRequest.makeInMap(message, URL.changeRole);
            if (answer.get("message").contains("Success")){
                SecurityController.changeRoleStage.close();
            }else {
                changeSecurityRoleId.setText("Ошибка сервера");
                changeSecurityRoleTitle.setText("Ошибка сервера");
            }
        }
    }
}
