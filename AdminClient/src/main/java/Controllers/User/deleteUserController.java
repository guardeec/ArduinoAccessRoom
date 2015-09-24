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
            List<Map> answer = httpRequest.makeInList(message, URL.getUser);
            Map<String, String> result = answer.get(answer.size() - 1);
            if (result.get("message").contains("Success")){
                deleteUserName.setText((String)answer.get(0).get("name"));
                deleteUserRole.setText((String)answer.get(0).get("role"));
            }else {
                deleteUserId.setText("Ошибка Сервера");
                deleteUserName.setText("Ошибка Сервера");
                deleteUserRole.setText("Ошибка Сервера");
            }

        }
    }
}
