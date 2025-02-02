package pochi.javafx;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import pochi.core.Pochi;

/**
 * A class that serves as the main GUI to the user.
 * Adopted from the JavaFX tutorial: https://se-education.org/guides/tutorials/javaFxPart4.html.
 *
 * @author Hibiki Nishiwaki -reuse
 */
public class Main extends Application {
    private Pochi pochi = new Pochi();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setPochi(pochi);
            fxmlLoader.<MainWindow>getController().beginConversation();
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
