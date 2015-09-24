package Controllers.Security;

import Controllers.Methods.AdminType;
import Controllers.Methods.URL;
import Controllers.Methods.httpRequest;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.util.List;
import java.util.Map;

/**
 * Created by root on 17.09.15.
 */
public class changePermissionController {
    @FXML
    TextField permissionSecurityId;

    @FXML
    TextField permissionSecurityRole;

    @FXML
    Text permissionSecurityStatus;

    @FXML
    public void permissionSecurityChangeBtnAction(){

        String deviceId = permissionSecurityId.getText();
        String roleId   = permissionSecurityRole.getText();
        String status   = permissionSecurityStatus.getText();
        if (deviceId.isEmpty() || roleId.isEmpty() || status.isEmpty() && (status.contains("true") || status.contains("false"))){

        }else {
            if (status.contains("Есть доступ")){
                status="false";
            }else {
                status="true";
            }
            AdminType adminType = AdminType.getInstance();
            String adminName = adminType.getLogin();
            String adminPassword = adminType.getPassword();
            String message =    "adminName="+adminName
                                +"&adminPassword="+adminPassword
                                +"&deviceId="+ deviceId
                                +"&roleId="+roleId
                                +"&status="+status
                    ;

            Map<String, String> answer = httpRequest.makeInMap(message, URL.changeSecurityPermission);
            if (answer.get("message").contains("Success")){
                if (status.contains("Нет доступа")){
                    permissionSecurityStatus.setFill(Color.GREEN);
                    status="Есть доступ";
                }else {
                    permissionSecurityStatus.setFill(Color.RED);
                    status="Нет доступа";
                }
                permissionSecurityStatus.setText(status);
            }else {
                permissionSecurityStatus.setText("Ошибка сервера");
                permissionSecurityStatus.setFill(Color.BLACK);
            }
        }

    }
    @FXML
    public void permissionSecurityFindBtnAction(){
        String id = permissionSecurityId.getText();
        String role = permissionSecurityRole.getText();

        if (id.isEmpty() || role.isEmpty()){
        }else {
            AdminType adminType = AdminType.getInstance();
            String adminName = adminType.getLogin();
            String adminPassword = adminType.getPassword();
            String message =    "adminName="+adminName
                    +"&adminPassword="+adminPassword
                    +"&deviceId="+ id
                    +"&roleId="+role
                    ;
            List<Map> answer = httpRequest.makeInList(message, URL.getSecurityPermission);
            Map<String, String> resultPermissions = answer.get(answer.size() - 1);
            if (resultPermissions.get("message").contains("Success")){
                String status = (String) answer.get(0).get("access");
                if (status.contains("t")){
                    permissionSecurityStatus.setFill(Color.GREEN);
                    status="Есть доступ";
                }else {
                    permissionSecurityStatus.setFill(Color.RED);
                    status="Нет доступа";
                }
                permissionSecurityStatus.setText(status);
            }else {
                permissionSecurityStatus.setText("Ошибка сервера");
                permissionSecurityStatus.setFill(Color.BLACK);
            }
        }
    }
}
