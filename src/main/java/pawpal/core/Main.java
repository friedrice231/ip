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
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    private final PawPal pawpal = new PawPal();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            Image icon =
                    new Image(String.valueOf(Objects.requireNonNull(Main.class.getResource("/images/cat-icon.png"))));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setScene(scene);
            stage.setMinHeight(220);
            stage.setMinWidth(417);
            fxmlLoader.<MainWindow>getController().setPawPal(pawpal); // inject the Duke instance
            stage.getIcons().add(icon);
            stage.setTitle("PawPal");
            stage.show();
        } catch (IOException e) {
            System.out.println(e.getMessage());;
        }
    }
}
