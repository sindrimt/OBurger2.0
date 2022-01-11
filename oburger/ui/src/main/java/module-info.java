module oburger.ui {

  requires oburger.core;
  requires oburger.jackson;
  requires javafx.controls;
  requires javafx.fxml;
  requires java.net.http;

  opens ui to javafx.graphics, javafx.fxml;
}
