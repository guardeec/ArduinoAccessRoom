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
public class changeUserController {
    @FXML
    private TextField changeUserId;
    @FXML
    private TextField changeUserName;
    @FXML
    private TextField changeUserRole;

    @FXML
    public void changeUserChangeBtnAction(){
        String name = changeUserName.getText();
        String role = changeUserRole.getText();
        String id   = changeUserId.getText();
        if (name.isEmpty() || role.isEmpty() || id.isEmpty()){
        }else {
            AdminType adminType = AdminType.getInstance();
            String adminName = adminType.getLogin();
            String adminPassword = adminType.getPassword();
            String message =    "adminName="+adminName
                                +"&adminPassword="+adminPassword
                    ;
            List<Map> answer = httpRequest.makeInList(message, URL.getUserRoles);
            Map<String, String> result = answer.get(answer.size()-1);
            if (result.get("message").contains("Success")){
                boolean roleIsAvailable = false;
                for (int i=0; i<answer.size()-1; i++){
                    String role_id = (String) answer.get(i).get("id");
                    if (role_id.compareTo(role)==0){
                        roleIsAvailable=true;
                    }
                }
                if (roleIsAvailable){
                    message =    "adminName="+adminName
                            +"&adminPassword="+adminPassword
                            +"&name="+name
                            +"&userId="+id
                            +"&roleId="+role
                    ;
                    httpRequest.makeInList(message, URL.changeUser);
                    UserController.changeStage.close();
                }else {
                    changeUserRole.setText("WRONG ID");
                }
            }else {
                changeUserId.setText("Ошибка Сервера");
                changeUserName.setText("Ошибка Сервера");
                changeUserRole.setText("Ошибка Сервера");
            }

        }

    }

    @FXML
    public void changeUserFindBtnAction(){
        String id = changeUserId.getText();
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
            Map<String, String> result = answer.get(answer.size()-1);
            if (result.get("message").contains("Success")) {
                changeUserName.setText((String)answer.get(0).get("name"));
                changeUserRole.setText((String)answer.get(0).get("role"));
            }else {
                changeUserId.setText("Ошибка Сервера");
                changeUserName.setText("Ошибка Сервера");
                changeUserRole.setText("Ошибка Сервера");
            }

        }
    }
}
