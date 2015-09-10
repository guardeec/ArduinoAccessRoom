package Controllers.Security;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

/**
 * Created by root on 15.07.15.
 */
public class addRoleController {
    @FXML
    TextField addSecurityRole;

    @FXML
    public void addSecurityAddBtnAction(){
        String name = addSecurityRole.getText();

        if (name.isEmpty()){
        }else {
            //http
            SecurityController.addStage.close();
        }

    }


}
