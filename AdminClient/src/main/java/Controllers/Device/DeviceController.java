package Controllers.Device;

import Controllers.Methods.AdminType;
import POJO.Device;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;


/**
 * Created by root on 13.07.15.
 */
public class DeviceController {



    public static Stage addStage;
    public static Stage changeStage;
    public static Stage deleteStage;

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
                new Device("4", "5", "124.323.13.23")
        );

        deviceTable.setItems(devices);
    }

    @FXML
    public void addDeviceBtnAction(){


        FXMLLoader loader = new FXMLLoader();
        Parent root = null;
        try {
            root = (Parent) loader.load(getClass().getResourceAsStream("/fxml/Device/addDevice.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene scene = new Scene(root);
        addStage = new Stage();
        addStage.setTitle("Добавление Устройства");
        addStage.setScene(scene);
        addStage.show();
    }
    @FXML
    public void changeDeviceBtnAction(){
        FXMLLoader loader = new FXMLLoader();
        Parent root = null;
        try {
            root = (Parent) loader.load(getClass().getResourceAsStream("/fxml/Device/changeDevice.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene scene = new Scene(root);
        changeStage = new Stage();
        changeStage.setTitle("Изменение Устройства");
        changeStage.setScene(scene);
        changeStage.show();
    }
    @FXML
    public void deleteDeviceBtnAction(){
        FXMLLoader loader = new FXMLLoader();
        Parent root = null;
        try {
            root = (Parent) loader.load(getClass().getResourceAsStream("/fxml/Device/deleteDevice.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene scene = new Scene(root);
        deleteStage = new Stage();
        deleteStage.setTitle("Удаление Устройства");
        deleteStage.setScene(scene);
        deleteStage.show();
    }

}