package Controllers.Device;

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
public class addDeviceController {
    @FXML
    private Text addDeviceMessage;
    @FXML
    private TextField addDeviceIp;
    @FXML
    private TextField addDeviceSpecification;
    @FXML
    public void addDeviceBtnAddAction(){
        String ip = addDeviceIp.getText();
        String specification = addDeviceSpecification.getText();

        if(ip.isEmpty() || specification.isEmpty()){
        } else {
            AdminType adminType = AdminType.getInstance();
            String adminName = adminType.getLogin();
            String adminPassword = adminType.getPassword();
            String message =    "adminName="+adminName
                                +"&adminPassword="+adminPassword
                                +"&ip="+ip
                                +"&specification="+specification
            ;

            Map<String, String> answer = httpRequest.makeInMap(message, URL.addDevice);
            if (answer.get("message").contains("Success")){
                DeviceController.addStage.close();
            }else {
                addDeviceIp.setText("Ошибка сервера");
                addDeviceSpecification.setText("Ошибка сервера");
            }
        }
    }
}
