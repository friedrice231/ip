package pawpal.core;

import java.io.IOException;
import java.util.Objects;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for PawPal using FXML.
 */
public class Main extends Application {

    private final PawPal pawpal = new PawPal();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);

            // Load CSS for styling (including the background)
            scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/view/style.css"))
                    .toExternalForm());

            // Set window icon
            Image icon = new Image(Objects.requireNonNull(Main.class.getResourceAsStream("/images/cat-icon.png")));
            stage.getIcons().add(icon);

            // Set window title
            stage.setTitle("PawPal Chat Assistant");

            // Set scene and show window
            stage.setScene(scene);
            stage.setMinHeight(600);
            stage.setMinWidth(400);
            fxmlLoader.<MainWindow>getController().setPawPal(pawpal);
            stage.show();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
