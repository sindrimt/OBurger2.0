package ui;

import java.net.URL;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * Main class of the program.
 * Responsible for setting up Controller and FXML.
 */
public class App extends Application {
  @Override
  public void start(Stage stage) throws Exception {

    stage.setTitle("O'Burger");
    FXMLLoader loader = new FXMLLoader(this.getClass().getResource("App.fxml"));
    Parent root = loader.load();

    //AppController controller = loader.getController();

    Scene scene = new Scene(root);
    scene.getStylesheets().add(App.class.getResource("/css/style.css").toString());
    stage.setResizable(false);
    stage.setScene(scene);
    stage.show();
  }

  public static void main(String[] args) {
    launch(args);
  }
}
