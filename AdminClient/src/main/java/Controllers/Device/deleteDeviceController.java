package Controllers.Device;

import Controllers.GuestCard.GuestCardController;
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
public class deleteDeviceController {
    @FXML
    TextField deleteDeviceId;
    @FXML
    TextField deleteDeviceSpecification;
    @FXML
    TextField deleteDeviceIp;

    @FXML
    public void deleteDeviceDeleteAction(){
        String id = deleteDeviceId.getText();
        if(!id.isEmpty()){
            AdminType adminType = AdminType.getInstance();
            String adminName = adminType.getLogin();
            String adminPassword = adminType.getPassword();
            String message =    "adminName="+adminName
                    +"&adminPassword="+adminPassword
                    +"&deviceId="+id;
            ;
            Map<String, String> answer = httpRequest.makeInMap(message, URL.deleteDevice);
            if (answer.get("message").contains("Success")){
                DeviceController.deleteStage.close();
            }else {
                deleteDeviceIp.setText("Ошибка сервера");
                deleteDeviceSpecification.setText("Ошибка сервера");
                deleteDeviceIp.setText("Ошибка сервера");
            }
        }
    }

    @FXML
    public void deleteDeviceFindAction(){
        String id = deleteDeviceId.getText();
        if(!id.isEmpty()){
            AdminType adminType = AdminType.getInstance();
            String adminName = adminType.getLogin();
            String adminPassword = adminType.getPassword();
            String message =    "adminName="+adminName
                    +"&adminPassword="+adminPassword
                    +"&deviceId="+id
                    +"&ip="
                    +"&specification="
                    ;
            List<Map> result = httpRequest.makeInList(message, URL.getDevice);
            Map<String, String> answer = result.get(result.size()-1);
            if (answer.get("message").contains("Success")){
                deleteDeviceSpecification.setText((String)result.get(0).get("specification"));
                deleteDeviceIp.setText((String) result.get(0).get("ip"));
            }else {
                deleteDeviceIp.setText("Ошибка сервера");
                deleteDeviceSpecification.setText("Ошибка сервера");
                deleteDeviceIp.setText("Ошибка сервера");
            }
        }
    }
}
