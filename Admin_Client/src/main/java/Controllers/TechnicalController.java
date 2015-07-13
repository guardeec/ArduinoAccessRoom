package Controllers;

import POJO.Device;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;


/**
 * Created by root on 13.07.15.
 */
public class TechnicalController extends mainController {

    @FXML
    private TextField deviceId;
    @FXML
    private TextField deviceSpecification;
    @FXML
    private TextField deviceIp;

    @FXML
    private TableView<Device> deviceTable;

    @FXML
    public void deviceFindBtnAction(){
        ObservableList<Device> devices = FXCollections.observableArrayList(
                new Device("1","1st Door", "146.323.43.23"),
                new Device("2","3st Door", "142.323.43.23"),
                new Device("3","4st Door", "156.323.43.23"),
                new Device("4","14st Door", "124.323.13.23")
        );
        deviceTable.setItems(devices);
    }
}
