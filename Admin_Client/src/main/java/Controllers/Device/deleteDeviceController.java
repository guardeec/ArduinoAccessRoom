package Controllers.Device;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

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
        if(id.isEmpty()){
        }else {
            //http
            DeviceController.deleteStage.close();
        }
    }

    @FXML
    public void deleteDeviceFindAction(){
        String id = deleteDeviceId.getText();
        if(id.isEmpty()){
        }else {
            //http
        }
    }
}
