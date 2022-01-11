package ui;

import core.Menu;
import core.MenuItem;
import core.Model;
import core.Order;
import jackson.Receipt;
import jackson.OburgerPersistence;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.nio.channels.IllegalSelectorException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;

/**
 * Controller class of the Model-View-Controller architecture.
 */

public class AppController {
  private Model model;
  private static final Font RECEIPTFONT = new Font(20);
  private static final String ENDPOINT_URI = "http://localhost:8080/";

  @FXML
  private Pane orderPage;
  @FXML
  private Pane viewReceiptsPage;
  @FXML 
  private ImageView backgroundImage; //-------
  @FXML
  private GridPane receiptsGrid;
  @FXML
  private GridPane menuGrid;
  @FXML
  private ImageView goHomeButton;
  @FXML
  private Button goOrderButton;
  @FXML
  private Button viewReceiptsButton;
  @FXML
  private Button finishOrderButton;
  
  @FXML
  private Pane expandedReceipt;
  @FXML
  private Label expandedReceiptText;

  @FXML
  private ImageView xButton;

  private HashMap<MenuItem, Label> itemCountLabels;
  private RemoteOburgerAccess remoteAccess;

  /**
   * Starts up the model and the view.
   * Must be run first before calling any other method
   */
  @FXML
  public void initialize() {
    try {
      remoteAccess = new RemoteOburgerAccess(new URI(ENDPOINT_URI));
    } catch(Exception e) {
      System.out.println("O'fuck");
    }
    // minusImg and plusImg are immutable.
    // they're therefore only loaded once here
    model = new Model();
    //xButton.setPreserveRatio(true);
    //xButton.setDisable(true);
    goHome();
  }

  @FXML
  private void goHome() {
    hidePane(orderPage);
    hidePane(viewReceiptsPage);
    showBackground(backgroundImage);
    hidePane(expandedReceipt);
  }

  @FXML
  private void goOrder() {
    showPane(orderPage);
    hidePane(viewReceiptsPage);
    hideBackground(backgroundImage);
    hidePane(expandedReceipt);

    // Recreate order page from what is currently on the menu
    itemCountLabels = new HashMap<>();
    List<MenuItem> items = model.getMenu().getItems();
    int gridWidth = menuGrid.getColumnCount();
    int gridHeight = menuGrid.getRowCount();
    int slotCount = gridWidth*gridHeight;
    int itemCount = items.size();
    if (itemCount > slotCount) {
      throw new IllegalStateException("Order page does not have enough slots in its grid");
    }
    for (int i = 0; i < itemCount; i++) {
      MenuItem item = items.get(i);
      Pane pane = (Pane)menuGrid.getChildren().get(i);

      // Register item count label to find it when updating count
      Label itemCountLabel = (Label)pane.getChildren().get(0);
      itemCountLabels.put(item, itemCountLabel);
      itemCountLabel.setText("0");

      // Display the image found by item.imgFileName
      ImageView itemImageView = (ImageView)pane.getChildren().get(1);
      itemImageView.setImage(loadImage(item.getImgFileName()));

      // Find minus and plus buttons, and add/remove this item when clicked
      ImageView minusButton = (ImageView)pane.getChildren().get(2);
      minusButton.setOnMouseClicked(event -> {addItem(item, 1);});
      ImageView plusButton = (ImageView)pane.getChildren().get(3);
      plusButton.setOnMouseClicked(event -> {removeItem(item, 1);});
    }
    model.beginOrder();
  }
  private Image loadImage(String name) {

    try {

      URL url = AppController.class.getResource("/images/"+name);
      return new Image(url.toString());
    }
    catch(Exception e) {
      return null;
    }
  }
  @FXML
  private void goViewReceipts() {
    hidePane(orderPage);
    
    hidePane(expandedReceipt);
    showPane(viewReceiptsPage);
    hideBackground(backgroundImage);

    viewReceipts();
  }

  private void hideBackground(ImageView backgroundImage) {
    backgroundImage.setDisable(true);
    backgroundImage.setOpacity(0f);
  }

  private void showBackground(ImageView backgroundImage) {
    backgroundImage.setDisable(false);
    backgroundImage.setOpacity(1f);
  }

  private void hidePane(Pane pane) {
    pane.setDisable(true);
    pane.setOpacity(0f);
  }

  private void showPane(Pane pane) {
    pane.setDisable(false);
    pane.setOpacity(1f);
  }

  

  private void addItem(MenuItem item, int count) {
    model.addItem(item, count);
    updateCountLabel(item);
  }

  private void removeItem(MenuItem item, int count) {
    model.removeItem(item, count);
    updateCountLabel(item);
  }

  @FXML
  private void finishOrder() {
    model.finishOrder();
    
    remoteAccess.postReceipt(new Receipt(model.getOrder()));
    //saveOrder = new ReceiptSerializer(new Receipt(model.getOrder()));
    //saveOrder.receiptToJson();

    goHome();
  }

  private void viewReceipts() {
    List<Receipt> receipts = remoteAccess.getReceipts();
    // List<Order> receipts = new ArrayList<Order>();
    // receipts.add(logic.getOrder());

    receiptsGrid.getChildren().clear(); // Refresh
    for (int i = 0; i < receipts.size(); i++) {
      if (i >= 8) {
        break;  // Only supports reading the last 8
      }
      Button el = new Button();
      Receipt receipt = receipts.get(i);

      StringBuffer buffer = new StringBuffer();
      for (int j = 0; j < receipt.getItemNames().size(); j++) {
        String name = receipt.getItemNames().get(j);
        int count = receipt.getCounts().get(j);
        buffer.append(count + "x " + name);
        if (j != receipt.getItemNames().size() - 1) {
          buffer.append(", ");
        }
      }
      String text = buffer.toString();
      el.setText(text);
      
      el.setFont(RECEIPTFONT);
      GridPane.setConstraints(el, 0, i);

      // Expand receipt on click
      el.setOnAction(event -> {expandReceipt(receipt);});
      receiptsGrid.getChildren().add(el);
    }
  }
  private void expandReceipt(Receipt receipt) {
    System.out.println("expand");
    hidePane(viewReceiptsPage);
    String txt = "";
    receiptsGrid.toBack();
    expandedReceipt.setOpacity(1f);
    txt += receipt.getDescription();
    expandedReceiptText.setText(txt);
    showPane(expandedReceipt);
  }
  @FXML
  private void minimizeReceipt() {
        showPane(viewReceiptsPage);

    hidePane(expandedReceipt);
    System.out.println("minimize");
  }

  private void updateCountLabel(MenuItem item) {
    if (item == null) {
      throw new IllegalStateException("Can't view the count of null item");
    }
    Order order = model.getOrder();

    // Find the label that represents this item's count
    if (!itemCountLabels.containsKey(item)) {
      throw new IllegalStateException("Found no label for menuitem " + item);
    } else {
      Label label = itemCountLabels.get(item);
      // Update count
      label.setText(Integer.toString(order.getCount(item)));
    }
  }
}
