# Release 2

It is now possible to order items on the order-page, and view the receipts on the receipt-page. The application functions as described in the project description with some exceptions:

There is no representation of time or location on the receipt-page.

The order-page and receipt-page do not list any prices.

The order-page does not allow the user to input the location of the store, or any other information needed for the order such as contact information and address.

The order-page does not allow the user to confirm any of the details after pressing the “Order”-button.

## Java modules and classes

oburger/core/src/main/java/core:

- Model.java: The model class in the model-view-controller architecture.

- MenuItem.java: Creates and stores data for new menu items, and can be added to an order.

- Mode.java: Which Mode is currently active restricts which actions are legal.

- Order.java: Creates and stores data for finished and incomplete orders.

- (NEW) Receipt.java: Serializable and deserializable representation of finished orders.

oburger/jackson/src/main/java/jackson:

- (NEW) ReceiptDeserializer.java: Reads the receipt(s) from a .json-file using Jackson.

- (NEW) ReceiptSerializer.java: Writes a receipt to a .json-file using Jackson.

oburger/ui/src/main/java/ui:

- App.java: Runs the application.

- AppController.java: Connects Model with the View (fxml).

oburger/ui/src/main/resources/ui:

- App.fxml: Responsible for the GUI (View) of the entire application

Dependencies:
ui-module requires core-module and jackson-module
jackson-module requires core-module
