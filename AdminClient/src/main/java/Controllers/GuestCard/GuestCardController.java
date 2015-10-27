package Controllers.GuestCard;

import Controllers.Device.DeviceController;
import Controllers.Methods.AdminType;
import Controllers.Methods.URL;
import Controllers.Methods.httpRequest;
import POJO.Guest;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Created by root on 13.07.15.
 */
public class GuestCardController extends DeviceController {

    public static Stage addStage;
    public static Stage changeStage;
    public static Stage deleteStage;

    @FXML
    private TextField guestCardNumber;
    @FXML
    private TextField guestDate;
    @FXML
    private TextField guestName;

    @FXML
    private TableView<Guest> guestTable;



    public void findHistoryBtnAction(){
        AdminType adminType = AdminType.getInstance();
        String adminName = adminType.getLogin();
        String adminPassword = adminType.getPassword();
        String date = guestDate.getText();
        String name = guestName.getText();

        String message =    "adminName="+adminName
                +"&adminPassword="+adminPassword
                +"&name="+name
                +"&date="+date
                ;

        List<Map> answer = httpRequest.makeInList(message, URL.getHistory);
        Map<String, String> result = answer.get(answer.size() - 1);
        if (result.get("message").contains("Success")){
            ObservableList<Guest> guests = FXCollections.observableArrayList();
            for (int i=0; i<answer.size()-1; i++){
                Guest guest = new Guest(
                        (String) answer.get(i).get("id"),
                        (String) answer.get(i).get("name"),
                        (String) answer.get(i).get("card_id"),
                        (String) answer.get(i).get("time_start"),
                        (String) answer.get(i).get("time_end"),
                        (String) answer.get(i).get("date")

                );
                guests.add(guest);
            }
            guestTable.setItems(guests);
        }else {
            ObservableList<Guest> guests = FXCollections.observableArrayList();
            Guest guest = new Guest(
                    "Ошибка Сервера",
                    "Ошибка Сервера",
                    "Ошибка Сервера",
                    "Ошибка Сервера",
                    "Ошибка Сервера",
                    "Ошибка Сервера"
            );
            guests.add(guest);
            guestTable.setItems(guests);
        }
    }

    public void findGuestBtnAction(){
        AdminType adminType = AdminType.getInstance();
        String adminName = adminType.getLogin();
        String adminPassword = adminType.getPassword();
        String cardNumber = guestCardNumber.getText();
        String date = guestDate.getText();
        String name = guestName.getText();

        String message =    "adminName="+adminName
                +"&adminPassword="+adminPassword
                +"&name="+name
                +"&date="+date
                +"&cardId="+cardNumber
                ;

        List<Map> answer = httpRequest.makeInList(message, URL.getGuest);
        Map<String, String> result = answer.get(answer.size() - 1);
        if (result.get("message").contains("Success")){
            ObservableList<Guest> guests = FXCollections.observableArrayList();
            for (int i=0; i<answer.size()-1; i++){
                System.out.println(answer.get(i).entrySet());
                Guest guest = new Guest(
                        (String) answer.get(i).get("id"),
                        (String) answer.get(i).get("name"),
                        (String) answer.get(i).get("card_id"),
                        (String) answer.get(i).get("time_start"),
                        (String) answer.get(i).get("time_end"),
                        (String) answer.get(i).get("date")
                );
                guests.add(guest);
            }
            guestTable.setItems(guests);
        }else {
            ObservableList<Guest> guests = FXCollections.observableArrayList();
            Guest guest = new Guest(
                    "Ошибка Сервера",
                    "Ошибка Сервера",
                    "Ошибка Сервера",
                    "Ошибка Сервера",
                    "Ошибка Сервера",
                    "Ошибка Сервера"
            );
            guests.add(guest);
            guestTable.setItems(guests);
        }
    }

    @FXML
    public void findFreeGuestCardsBtnAction(){
        AdminType adminType = AdminType.getInstance();
        String adminName = adminType.getLogin();
        String adminPassword = adminType.getPassword();
        String message =    "adminName="+adminName
                            +"&adminPassword="+adminPassword
                ;
        List<Map> answer = httpRequest.makeInList(message, URL.getFreeCards);
        Map<String, String> result = answer.get(answer.size() - 1);
        if (result.get("message").contains("Success")){
            ObservableList<Guest> guests = FXCollections.observableArrayList();
            for (int i=0; i<answer.size()-1; i++){
                Guest guest = new Guest(
                        (String) answer.get(i).get("id"),
                        null,
                        null,
                        null,
                        null,
                        null
                );
                guests.add(guest);
            }
            guestTable.setItems(guests);
        }else {
            ObservableList<Guest> guests = FXCollections.observableArrayList();
            Guest guest = new Guest(
                    "Ошибка Сервера",
                    null,
                    null,
                    null,
                    null,
                    null
            );
            guests.add(guest);
            guestTable.setItems(guests);
        }
    }

    @FXML
    public void addGuestCardBtnAction(){
        FXMLLoader loader = new FXMLLoader();
        Parent root = null;
        try {
            root = (Parent) loader.load(getClass().getResourceAsStream("/fxml/GuestCard/addGuestCard.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene scene = new Scene(root);
        addStage = new Stage();
        addStage.setTitle("Добавление Гостевой Карты");
        addStage.setScene(scene);
        addStage.show();
    }

    @FXML
    public void changeGuestCardBtnAction(){
        FXMLLoader loader = new FXMLLoader();
        Parent root = null;
        try {
            root = (Parent) loader.load(getClass().getResourceAsStream("/fxml/GuestCard/changeGuestCard.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene scene = new Scene(root);
        changeStage = new Stage();
        changeStage.setTitle("Изменение Гостевой Карты");
        changeStage.setScene(scene);
        changeStage.show();
    }

    @FXML
    public void deleteGuestCardBtnAction(){
        FXMLLoader loader = new FXMLLoader();
        Parent root = null;
        try {
            root = (Parent) loader.load(getClass().getResourceAsStream("/fxml/GuestCard/deleteGuestCard.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene scene = new Scene(root);
        deleteStage = new Stage();
        deleteStage.setTitle("Удаление Гостевой Карты");
        deleteStage.setScene(scene);
        deleteStage.show();
    }
}
