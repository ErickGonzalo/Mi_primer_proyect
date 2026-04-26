package pe.edu.upeu;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.net.URL;

public class App extends Application {

    @Override
    public void start(Stage stage) throws Exception {

        // Cargar FXML
        URL fxml = getClass().getResource("/escuela.fxml");

        if (fxml == null) {
            throw new RuntimeException("No se encontró escuela.fxml en resources");
        }

        FXMLLoader loader = new FXMLLoader(fxml);
        Scene scene = new Scene(loader.load(), 600, 500);

        // 👉 CARGAR CSS (BOTONES CON COLOR)
        scene.getStylesheets().add(
                getClass().getResource("/styles.css").toExternalForm()
        );

        stage.setTitle("Padrón de Escuelas");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}