package pochi.javafx;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

/**
 * A class that represents one dialog box.
 * The idea is adopted from the JavaFX tutorial: https://se-education.org/guides/tutorials/javaFxPart2.html.
 *
 * @author Hibiki Nishiwaki
 */
public class DialogBox extends HBox {
    private Label text;
    private ImageView displayPicture;

    private DialogBox(String message, Image icon) {
        text = new Label(message);
        displayPicture = new ImageView(icon);

        text.setWrapText(true);
        displayPicture.setFitWidth(80.0);
        displayPicture.setFitHeight(80.0);
        this.setAlignment(Pos.TOP_LEFT);

        this.getChildren().addAll(displayPicture, text);
    }

    private void flip() {
        this.setAlignment(Pos.TOP_RIGHT);
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        FXCollections.reverse(tmp);
        this.getChildren().setAll(tmp);
    }

    /**
     * A factory method of dialog box for the user.
     *
     * @param message The message appeared in this dialog box.
     * @param icon The icon of sender.
     */
    public static DialogBox getUserDialog(String message, Image icon) {
        var db = new DialogBox(message, icon);
        db.flip();
        return db;
    }

    /**
     * A factory method of dialog box for the Pochi.
     *
     * @param message The message appeared in this dialog box.
     * @param icon The icon of sender.
     */
    public static DialogBox getPochiDialog(String message, Image icon) {
        return new DialogBox(message, icon);
    }
}
