package pochi.javafx;

import java.io.IOException;
import java.util.Collections;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

/**
 * A class that represents one dialog box.
 * Adopted from the JavaFX tutorial: https://se-education.org/guides/tutorials/javaFxPart4.html.
 *
 * @author Hibiki Nishiwaki -reuse
 */
public class DialogBox extends HBox {
    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

    private DialogBox(String message, Image icon) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        dialog.setText(message);
        displayPicture.setImage(icon);
    }

    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        getChildren().setAll(tmp);
        setAlignment(Pos.TOP_LEFT);
    }

    /**
     * A factory method of dialog box for the user.
     *
     * @param message The message appeared in this dialog box.
     * @param icon The icon of sender.
     */
    public static DialogBox getUserDialog(String message, Image icon) {
        return new DialogBox(message, icon);
    }

    /**
     * A factory method of dialog box for the Pochi.
     *
     * @param message The message appeared in this dialog box.
     * @param icon The icon of sender.
     */
    public static DialogBox getPochiDialog(String message, Image icon) {
        var db = new DialogBox(message, icon);
        db.flip();
        return db;
    }
}
