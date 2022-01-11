# Release 1

The application functions as described in the project description with some exceptions:

The app is able to run and show the user a basic UI. 

Orders can be made, but only burgers can be ordered through the UI. 

The receipts-file (SaveOrders.java) overwrites old orders, and is only able to show one order at a time. 

Both the receipt, order and their respective buttons are shown on the same page, unlike in the concept art.

## Java classes

oburger/core/src/main/java/core:

- Logic.java: The model class in the model-view architecture. 

- MenuItem.java: Creates and stores data for new menu items, and can be added to an order.

- Mode.java: Which Mode is currently active restricts which actions are legal.

- Order.java: Creates and stores data for finished and incomplete orders. 

- ReadOrder.java: Reads the order(s) from a .txt-file.

- SaveOrder.java: Writes an order to a .txt-file.

- Some tests have also been added to oburger/core/src/test/java/core


oburger/ui/src/main/java/ui:

- App.java: Runs the application.

- AppController.java: Connects the logic (core java-classes) with the UI (fxml).


oburger/ui/src/main/resources/ui:

- App.fxml: Responsible for the visuals in the UI.
