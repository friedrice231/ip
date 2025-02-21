package pawpal.core;

import java.util.Objects;

import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import pawpal.utils.Printer;

/**
 * Controller for the main GUI.
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private PawPal pawpal;

    private final Image userImage = new Image(Objects
            .requireNonNull(this.getClass().getResourceAsStream("/images/cat-right.png")));
    private final Image pawpalImage = new Image(Objects
            .requireNonNull(this.getClass().getResourceAsStream("/images/cat-left.png")));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /** Injects the PawPal instance */
    public void setPawPal(PawPal p) {
        pawpal = p;
        String greeting = Printer.getGreetingMessage("PawPal");
        dialogContainer.getChildren().add(DialogBox.getPawPalDialog(greeting, pawpalImage));
    }

    /**
     * Handles user input by displaying it and generating a response.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = pawpal.getResponse(input);
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getPawPalDialog(response, pawpalImage)
        );
        userInput.clear();

        // Exit the application when "bye" is typed
        if (input.equalsIgnoreCase("bye")) {
            PauseTransition delay = new PauseTransition(Duration.seconds(1));
            delay.setOnFinished(event -> Platform.exit());
            delay.play();
        }
    }
    public void setBackground(String imagePath) {
        this.getScene().lookup("#mainPane")
                .setStyle("-fx-background-image: url('" + imagePath + "'); "
                        + "-fx-background-size: cover; "
                        + "-fx-background-position: center; "
                        + "-fx-background-repeat: no-repeat;");
    }
}
