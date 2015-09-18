package Controllers.Device;

import Controllers.Methods.AdminType;
import Controllers.Methods.URL;
import Controllers.Methods.httpRequest;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.util.List;
import java.util.Map;

/**
 * Created by root on 15.07.15.
 */
public class changeDeviceController {
    @FXML
    private TextField changeDeviceId;
    @FXML
    private TextField changeDeviceSpecification;
    @FXML
    private TextField changeDeviceIp;

    @FXML
    public void changeDeviceChangeBtnAction(){
        String id = changeDeviceId.getText();
        String specification = changeDeviceSpecification.getText();
        String ip = changeDeviceIp.getText();

        if(!id.isEmpty() && !specification.isEmpty() && !ip.isEmpty()){
            AdminType adminType = AdminType.getInstance();
            String adminName = adminType.getLogin();
            String adminPassword = adminType.getPassword();
            String message =    "adminName="+adminName
                    +"&adminPassword="+adminPassword
                    +"&ip="+ip
                    +"&specification="+specification
                    +"&deviceId="+id
                    ;
            Map<String, String> answer = httpRequest.makeInMap(message, URL.changeDevice);
            if (answer.get("message").contains("Success")){
                DeviceController.changeStage.close();
            }else {
                changeDeviceIp.setText("Ошибка сервера");
                changeDeviceSpecification.setText("Ошибка сервера");
                changeDeviceId.setText("Ошибка сервера");
            }
        }
    }
    @FXML
    public void changeDeviceFindBtnAction(){
        String id = changeDeviceId.getText();
        if (!id.isEmpty()){
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
                changeDeviceSpecification.setText((String) result.get(0).get("specification"));
                changeDeviceIp.setText((String) result.get(0).get("ip"));
            }else {
                changeDeviceIp.setText("Ошибка сервера");
                changeDeviceSpecification.setText("Ошибка сервера");
                changeDeviceId.setText("Ошибка сервера");
            }
        }
    }
}
