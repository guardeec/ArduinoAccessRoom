package Controllers.User;

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
public class deleteUserController {
    @FXML
    TextField deleteUserId;

    @FXML
    TextField deleteUserName;

    @FXML
    TextField deleteUserRole;

    @FXML
    public void deleteUserDeleteBtnAction(){
        String id = deleteUserId.getText();
        AdminType adminType = AdminType.getInstance();
        String adminName = adminType.getLogin();
        String adminPassword = adminType.getPassword();
        String message =    "adminName="+adminName
                +"&adminPassword="+adminPassword
                +"&userId="+id
                ;
        Map<String, String> answer = httpRequest.makeInMap(message, URL.deleteUser);
        if (answer.get("message").contains("Success")){
            UserController.deleteStage.close();
        }else {
            deleteUserId.setText("Ошибка Сервера");
            deleteUserName.setText("Ошибка Сервера");
            deleteUserRole.setText("Ошибка Сервера");
        }

    }

    @FXML
    public void deleteUserFindBtnAction(){
        String id = deleteUserId.getText();
        if (id.isEmpty()){
        }else {
            AdminType adminType = AdminType.getInstance();
            String adminName = adminType.getLogin();
            String adminPassword = adminType.getPassword();
            String message =    "adminName="+adminName
                    +"&adminPassword="+adminPassword
                    +"&userId="+id
                    +"&name="
                    +"&roleId="
                    ;
            Map<String, String> answer = httpRequest.makeInMap(message, URL.getUser);
            if (answer.get("message").contains("Success")){
                deleteUserName.setText(answer.get("name"));
                deleteUserRole.setText(answer.get("role"));
            }else {
                deleteUserId.setText("Ошибка Сервера");
                deleteUserName.setText("Ошибка Сервера");
                deleteUserRole.setText("Ошибка Сервера");
            }

        }
    }
}
