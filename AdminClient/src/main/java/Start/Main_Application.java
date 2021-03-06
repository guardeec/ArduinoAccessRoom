package Start;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Created by guardeec on 19.05.15.
 */
public class Main_Application extends Application {

    public static Stage stage;

    public static void main(String[] args) throws Exception {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        this.stage = stage;

        FXMLLoader loader = new FXMLLoader();
        Parent root = (Parent) loader.load(getClass().getResourceAsStream("/fxml/Welcome.fxml"));

        stage.setTitle("Arduino Admin");
        stage.setScene(new Scene(root));
        stage.show();
    }
}
