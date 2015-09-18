package Controllers.User;

import Controllers.Device.DeviceController;
import Controllers.Methods.AdminType;
import Controllers.Methods.URL;
import Controllers.Methods.httpRequest;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.util.List;
import java.util.Map;

/**
 * Created by root on 15.07.15.
 */
public class addUserController {
    @FXML
    private TextField addUserName;
    @FXML
    private TextField addUserRole;
    @FXML
    private TextField addUserCardId;

    @FXML
    public void addUserBtnAction(){
        String name = addUserName.getText();
        String role = addUserRole.getText();
        String id   = addUserCardId.getText();
        if (name.isEmpty() || role.isEmpty() || id.isEmpty()){
        }else {
            AdminType adminType = AdminType.getInstance();
            String adminName = adminType.getLogin();
            String adminPassword = adminType.getPassword();
            String message =    "adminName="+adminName
                                +"&adminPassword="+adminPassword
                    ;
            List<Map> answer = httpRequest.makeInList(message, URL.getFreeUserCards);
            Map<String, String> result = answer.get(answer.size()-1);
            if (result.get("message").contains("Success")){
                boolean cardIsAvailable = false;
                for (int i=0; i<answer.size()-1; i++){
                    String card = (String) answer.get(i).get("id");
                    System.out.println(answer.get(i).get("id"));
                    if (card.compareTo(id)==0){
                        cardIsAvailable=true;
                    }
                }
                if (cardIsAvailable){
                    answer = httpRequest.makeInList(message, URL.getUserRoles);
                    result = answer.get(answer.size()-1);
                    if (result.get("message").contains("Success")){
                        boolean roleIsAvailable = false;
                        for (int i=0; i<answer.size()-1; i++){
                            String role_id = (String) answer.get(i).get("id");
                            if (role_id.compareTo(role)==0){
                                roleIsAvailable=true;
                            }
                        }
                        if (roleIsAvailable){
                            message =    "adminName="+adminName
                                    +"&adminPassword="+adminPassword
                                    +"&name="+name
                                    +"&cardId="+id
                                    +"&roleId="+role
                            ;
                            result = httpRequest.makeInMap(message, URL.addUser);
                            if (result.get("message").contains("Success")){
                                UserController.addStage.close();
                            }else {
                                addUserName.setText("Ошибка сервера");
                                addUserRole.setText("Ошибка сервера");
                                addUserCardId.setText("Ошибка сервера");
                            }
                        }else {
                            addUserRole.setText("WRONG ID");
                        }
                    }else {
                        addUserName.setText("Ошибка сервера");
                        addUserRole.setText("Ошибка сервера");
                        addUserCardId.setText("Ошибка сервера");
                    }
                }else {
                    addUserCardId.setText("WRONG ID");
                }
            }else {
                addUserName.setText("Ошибка сервера");
                addUserRole.setText("Ошибка сервера");
                addUserCardId.setText("Ошибка сервера");
            }


        }
    }
}
