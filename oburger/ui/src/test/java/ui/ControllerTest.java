package ui;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;

import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ControllerTest extends ApplicationTest {

  private AppController controller;
  private Parent root;

  @Override
  public void start(Stage stage) throws IOException {
    FXMLLoader loader = new FXMLLoader(getClass().getResource("App.fxml"));
    root = loader.load();
    controller = loader.getController();
    stage.setScene(new Scene(root));
    stage.show();
  }

  @Test
  public void navigateAround() {
    clickOn("#goOrderButton");
    clickOn("#goOrderButton");
    //clickOn("#viewReceiptsButton"); Requires server to be running
    clickOn("#goHomeButton");
    // should not throw throughout this process
  }

  /*
  @Test
  public void testOrdering() {
    clickOn("#goOrderButton");
    clickOn("#removeBurger");
    clickOn("#addBurger");
    clickOn("#removeShake");
    clickOn("#addShake");
    clickOn("#removeFries");
    clickOn("#addFries");
    clickOn("#removeNuggets");
    clickOn("#addNuggets");
    clickOn("#finishOrderButton");
  }*/

}
