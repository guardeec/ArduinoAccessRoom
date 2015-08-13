package Controllers;

import Controllers.Device.DeviceController;
import Controllers.Methods.AdminType;
import Controllers.Methods.URL;
import Controllers.Methods.httpRequest;
import Controllers.Security.SecurityController;
import Start.Main_Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by root on 15.07.15.
 */
public class WelcomeController {
    public static Stage stage;

    @FXML
    private TextField welcomeName;
    @FXML
    private PasswordField welcomePassword;
    @FXML
    private Text welcomeMessage;

    @FXML
    public void welcomeAction(){
        String name = welcomeName.getText();
        String pass = welcomePassword.getText();

        Map<String, String> enter = httpRequest.makeInMap("adminName=" + name + "&" + "adminPasswordHash=" + pass, URL.getAdminType);
        if(!enter.get("message").toLowerCase().equals("error")){

            AdminType adminType = AdminType.getInstance();
            adminType.set(enter);

            if(!Boolean.parseBoolean(enter.get("deviceAdmin"))){
                welcomeMessage.setText("Пользователь не найден");
            }else {
                FXMLLoader loader = new FXMLLoader();
                Parent root = null;
                try {
                    root = (Parent) loader.load(getClass().getResourceAsStream("/fxml/MainWindow.fxml"));
                } catch (IOException e) {
                    e.printStackTrace();
                }

                Scene scene = null;
                if (root != null) {
                    scene = new Scene(root);
                    stage = new Stage();
                    stage.setTitle("Arduino Access");
                    stage.setScene(scene);
                    stage.show();
                }
                Main_Application.stage.close();
            }
        }else {
            welcomeMessage.setText("Нет соединения");
        }

    }
}
