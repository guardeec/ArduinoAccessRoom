package Controllers;

import Controllers.Methods.AdminType;
import Controllers.Security.SecurityController;
import javafx.fxml.FXML;
import javafx.scene.control.Tab;

/**
 * Created by root on 13.07.15.
 */
public class mainController extends SecurityController {
    @FXML
    private Tab deviceTab;
    @FXML
    private Tab guestTab;
    @FXML
    private Tab userTab;
    @FXML
    private Tab securityTab;

    @FXML
    public void init(){
        AdminType adminType = AdminType.getInstance();
        System.out.println(adminType.get().entrySet());

        deviceTab.setDisable(!Boolean.parseBoolean(adminType.get().get("deviceAdmin")));
        guestTab.setDisable(!Boolean.parseBoolean(adminType.get().get("guestAdmin")));
        userTab.setDisable(!Boolean.parseBoolean(adminType.get().get("userAdmin")));
        securityTab.setDisable(!Boolean.parseBoolean(adminType.get().get("securityAdmin")));
    }


}
