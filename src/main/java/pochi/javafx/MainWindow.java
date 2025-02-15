package pochi.javafx;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import pochi.core.Pochi;

/**
 * Controller for the main GUI.
 * Adopted from the JavaFX tutorial: https://se-education.org/guides/tutorials/javaFxPart4.html.
 *
 * @author Hibiki Nishiwaki -reuse
 */
public class MainWindow extends AnchorPane {
    private static final String PATH_TO_USER_ICON = "/icons/DaUser.jpg";
    private static final String PATH_TO_POCHI_ICON = "/icons/DaPochi.jpg";

    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Pochi pochi;

    private Image userIcon =
        new Image(this.getClass().getResourceAsStream(PATH_TO_USER_ICON));
    private Image pochiIcon =
        new Image(this.getClass().getResourceAsStream(PATH_TO_POCHI_ICON));

    /**
     * Initializes the main window.
     * It also prompts Pochi to greet to the user and load the log from previous session.
     */
    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Starts the chat with Pochi and the user.
     * It has Pochi greet and load the log from previous session.
     */
    public void beginConversation() {
        dialogContainer.getChildren().addAll(DialogBox.getPochiDialog(pochi.greet(), pochiIcon));
        dialogContainer.getChildren().addAll(DialogBox.getPochiDialog(pochi.processPreviousLog(), pochiIcon));
    }

    /** Injects the Pochi instance */
    public void setPochi(Pochi p) {
        pochi = p;
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = pochi.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userIcon),
                DialogBox.getPochiDialog(response, pochiIcon)
        );
        userInput.clear();
        if (pochi.isConversationOver(input)) {
            Platform.exit();
        }
    }
}
