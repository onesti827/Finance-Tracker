import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Parent;

public class UILoader extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/ui.fxml"));
        Parent root = loader.load();

        Scene scene = new Scene(root, 700, 500);
        scene.getStylesheets().add("/Style/design.css");

        primaryStage.setTitle("Personal Finance Tracker");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
