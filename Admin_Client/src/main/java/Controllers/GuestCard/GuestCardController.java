package Controllers.GuestCard;

import Controllers.Device.DeviceController;
import POJO.GuestCard;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuButton;
import javafx.scene.control.Tab;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

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
    private MenuButton guestCardStatus;
    @FXML
    private TextField guestCardGuest;

    @FXML
    private TableView<GuestCard> guestTable;



    public void findGuestBtnAction(){
        ObservableList<GuestCard> guestCards = FXCollections.observableArrayList(
                new GuestCard("1343423", "Активна", "Tom"),
                new GuestCard("2234233", "Активна", "Lena"),
                new GuestCard("3234444", "Неактивна", null),
                new GuestCard("4324422", "Неактивна", "Eva")
        );
        guestTable.setItems(guestCards);
    }

    @FXML
    public void guestStatusActiveSelected(){
        System.out.println("Hi");
    }
    @FXML
    public void guestStatusDisactiveSelected(){
        System.out.println("Bye");
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
