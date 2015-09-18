package Controllers.Security;

import Controllers.Methods.AdminType;
import Controllers.Methods.URL;
import Controllers.Methods.httpRequest;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

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
            if (status.contains("true")){
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
                permissionSecurityStatus.setText(status);
                if (status.contains("true")){
                    permissionSecurityStatus.setFill(Color.GREEN);
                }else {
                    permissionSecurityStatus.setFill(Color.RED);
                }
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
            Map<String, String> answer = httpRequest.makeInMap(message, URL.getSecurityPermission);
            if (answer.get("message").contains("Success")){
                String status = answer.get("GetAccess");
                if (status.contains("true")){
                    permissionSecurityStatus.setFill(Color.GREEN);
                }else {
                    permissionSecurityStatus.setFill(Color.RED);
                }
                permissionSecurityStatus.setText(status);
            }else {
                permissionSecurityStatus.setText("Ошибка сервера");
                permissionSecurityStatus.setFill(Color.BLACK);
            }
        }
    }
}
