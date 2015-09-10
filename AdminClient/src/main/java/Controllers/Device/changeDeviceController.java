package Controllers.Device;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

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

        if(id.isEmpty()){
        }else{
            //http
            DeviceController.changeStage.close();
        }
    }
    @FXML
    public void changeDeviceFindBtnAction(){
        //http
    }
}
