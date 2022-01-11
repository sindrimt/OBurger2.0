package ui;

import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.Start;

import javafx.stage.Stage;

public class AppTest {
  @Start
  public void start(Stage stage) throws Exception {
    App app = new App();
    app.start(stage);
  }

  @Test
  public void testApp() {
    
  }
}
