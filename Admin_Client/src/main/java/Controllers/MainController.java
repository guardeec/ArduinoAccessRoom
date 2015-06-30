package Controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.util.Callback;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by guardeec on 19.05.15.
 */
public class MainController {

    /*
    Server address
     */
    String ServerAddresGet = "http://localhost:8080/Get";
    String ServerAddresAdd = "http://localhost:8080/Add";
    String ServerAddresChange = "http://localhost:8080/Change";
    String ServerAddresDelete = "http://localhost:8080/Delete";

    /*
    ** return String of Http_response
    */
    private static String readStreamToString(InputStream in, String encoding) throws IOException {
        StringBuffer b = new StringBuffer();
        InputStreamReader r = new InputStreamReader(in, encoding);
        int c;
        while ((c = r.read()) != -1) {
            b.append((char)c);
        }
        return b.toString();
    }

    /*
    ** Search
     */
    @FXML
    private TextField Search_User_ID;
    @FXML
    private TextField Search_Name;
    @FXML
    private TextField Search_Role;
    @FXML
    private TextField Search_Card;
    @FXML
    private TextField Search_State;
    @FXML
    private Text Search_Message;

    /*
    **  Add
     */
    @FXML
    private TextField Add_User_ID;
    @FXML
    private TextField Add_Name;
    @FXML
    private TextField Add_Card;
    @FXML
    private Text Add_Message;

    /*
    ** Change
     */
    @FXML
    private TextField Change_User_ID;
    @FXML
    private TextField Change_Name;
    @FXML
    private TextField Change_Role;
    @FXML
    private TextField Change_Card;
    @FXML
    private TextField Change_State;
    @FXML
    private Text Change_Message;

    /*
    ** Delete
     */
    @FXML
    private TextField Delete_User_ID;
    @FXML
    private TextField Delete_Name;
    @FXML
    private Text Delete_Message;

    /*
    ** Methods
     */

    //Clever_Search can search user in different combination of his params
    @FXML
    public void Search_SearchAction(){

        String message;

        URLConnection conn = null;
        try {
            message =   "user_id="+Search_User_ID.getText()+"&"+
                        "user_name="+Search_Name.getText()+"&"+
                        "user_role_id=&"+
                        "user_role_title="+Search_Role.getText()+"&"+
                        "user_card="+Search_Card.getText()+"&"+
                        "card_state="+Search_State.getText()
            ;
            conn = new URL(ServerAddresGet + "?" + message).openConnection();
            message = Parsing.parseArrayHashMap(readStreamToString(conn.getInputStream(), "UTF-8"));

        } catch (IOException e) {
            message = "NO_SERVER_CONNECTION";
            e.printStackTrace();
        }

        Search_Message.setText(message);
    }

    @FXML
    public void Change_SearchAction(){

        String message;

        if(!Change_User_ID.getText().isEmpty()){
            URLConnection conn = null;
            try {

                message =   "user_id="+Change_User_ID.getText()+"&"+
                            "user_name=&"+
                            "user_role_id=&"+
                            "user_role_title=&"+
                            "user_card=&"+
                            "card_state="
                ;

                conn = new URL(ServerAddresGet + "?" + message).openConnection();
                message = readStreamToString(conn.getInputStream(), "UTF-8");

                String[] userData = Parsing.parseHashMap(message);
                Change_User_ID.setText(userData[0]);
                Change_Name.setText(userData[1]);
                Change_Role.setText(userData[2]);
                Change_Card.setText(userData[4]);
                Change_State.setText(userData[5]);

                message = "User found";

            } catch (IOException e) {
                message = "NO_SERVER_CONNECTION";
                e.printStackTrace();
            }
        }
        else{
            message = "Enter User ID for Search";
        }

        Change_Message.setText(message);

    }

    @FXML
    public void Delete_SearchAction(){

        String message;

        if(!Delete_User_ID.getText().isEmpty()){
            URLConnection conn = null;
            try {
                message =   "user_id="+Delete_User_ID.getText()+"&"+
                            "user_name=&"+
                            "user_role_id=&"+
                            "user_role_title=&"+
                            "user_card=&"+
                            "card_state="
                ;
                conn = new URL(ServerAddresGet + "?" + message).openConnection();
                message = readStreamToString(conn.getInputStream(), "UTF-8");

                String[] userData = Parsing.parseHashMap(message);
                Delete_User_ID.setText(userData[0]);
                Delete_Name.setText(userData[1]);

                message = "User found";

            } catch (IOException e) {
                message = "NO_SERVER_CONNECTION";
                e.printStackTrace();
            }
        }
        else{
            message = "Enter User ID for Search";
        }

        Delete_Message.setText(message);

    }

    @FXML
    public void AddAction(){
        String message;

        if(!Add_Name.getText().isEmpty() && !Add_Card.getText().isEmpty()){
            URLConnection conn = null;

            message =   "user_name="+Add_Name.getText()+"&"+
                        "user_card="+Add_Card.getText();

            try {
                conn = new URL(ServerAddresAdd+ "?" + message).openConnection();
                message = readStreamToString(conn.getInputStream(), "UTF-8");
                Add_User_ID.setText(Parsing.parseHashMap(message)[0]);
                message = "User added";
            } catch (IOException e) {
                e.printStackTrace();
                message = "NO SERVER CONNECTION";
            }
        }else {
            message = "Enter the Name\n and Card";
        }

        Add_Message.setText(message);
    }


    @FXML
    public void ChangeAction() {
        String message;

        if(!Change_User_ID.getText().isEmpty()){

            URLConnection conn = null;
            try {
                message =   "user_id="+Change_User_ID.getText()+"&"+
                            "user_name="+Change_Name.getText()+"&"+
                            "user_role_id="+Change_Role.getText()+"&"+
                            "user_card="+Change_Card.getText()+"&"+
                            "card_state="+Change_State.getText()
                ;
                conn = new URL(ServerAddresChange + "?" + message).openConnection();
                message = readStreamToString(conn.getInputStream(), "UTF-8");
            } catch (IOException e) {
                message = "NO_SERVER_CONNECTION";
                e.printStackTrace();
            }
        }else {
            message = "Enter the User ID";
        }

        Change_Message.setText(message);
    }

    @FXML
    public void DeleteAction(){

        String message;

        if(!Delete_User_ID.getText().isEmpty()){

            URLConnection conn = null;
            try {
                message = "user_id="+Delete_User_ID.getText();
                conn = new URL(ServerAddresDelete + "?" + message).openConnection();
                message = readStreamToString(conn.getInputStream(), "UTF-8");
            } catch (IOException e) {
                message = "NO_SERVER_CONNECTION";
                e.printStackTrace();
            }
        }else {
            message = "Enter the User ID";
        }

        Delete_Message.setText(message);

    }

}
