package Controllers.Device;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

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

        if(ip.isEmpty()){
            addDeviceMessage.setText("Введите IP");
        }else
        if(specification.isEmpty()){
            addDeviceMessage.setText("Введите описание");
        }
        else {
            //http
            DeviceController.addStage.close();
        }
    }
}
