package Controllers.Device;

import Controllers.Methods.AdminType;
import Controllers.Methods.URL;
import Controllers.Methods.httpRequest;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


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

        AdminType adminType = AdminType.getInstance();
        String adminName = adminType.getLogin();
        String adminPassword = adminType.getPassword();
        String ip = deviceIp.getText();
        String specification = deviceSpecification.getText();
        String id = deviceId.getText();
        String message =    "adminName="+adminName
                +"&adminPassword="+adminPassword
                +"&ip="+ip
                +"&specification="+specification
                +"&deviceId="+id
                ;

        List<Map> answer = httpRequest.makeInList(message, URL.getDevice);
        Map<String, String> result = answer.get(answer.size()-1);
        if (result.get("message").contains("Success")){
            ObservableList<Device> devices = FXCollections.observableArrayList();
            for (int i=0; i<answer.size()-1; i++) {
                Device device = new Device(
                        (String) answer.get(i).get("id"),
                        (String) answer.get(i).get("specification"),
                        (String) answer.get(i).get("ip")
                );
                devices.add(device);
            }
            deviceTable.setItems(devices);
        }else {
            ObservableList<Device> devices = FXCollections.observableArrayList();

            Device device = new Device(
                    "Ошибка Сервера",
                    "Ошибка сервера",
                    "Ошибка Сервера"
            );
            devices.add(device);
            deviceTable.setItems(devices);
        }

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
