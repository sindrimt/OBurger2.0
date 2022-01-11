package core;

import java.util.ArrayList;
import java.util.List;

/**
 * Model class of the Model-View-Controller architecture.
 */
public class Model {
  // Logic for App
  private Menu menu = new Menu(true);  // Currently available for ordering
  private Order order;  // The current active order

  /**
   * Gets a copy of the menu.

   * @return the menu
   */
  public Menu getMenu() {
    return menu;
  }

  /**
   * Gets the MenuItem with given name
   
   * @throws IllegalStateException if name is null or not in Menu
   * @return the item, if existing
   */
  public MenuItem getMenuItem(String name) {
    return menu.getItem(name);
  }

  /**
   * Begins a new order.
   */
  public void beginOrder() {
    order = new Order();
  }

  /**
   * Delegate method to finish current order.

   * @throws IllegalStateException if order is null
   */
  public void finishOrder() {
    if (order == null) throw new IllegalStateException("Tried finishing order null");
    order.finish();
  }

  /**
   * Delegate method to add one unit of given item.

   * @param item the item to add
   * @throws IllegalStateException if not currently in order mode
   * @throws IllegalArgumentException if item is null or count is < 1
   */
  public void addItem(MenuItem item, int count) {
    if (order == null) {
      String msg = "Tried adding item " + item + " when order = null";
      throw new IllegalStateException(msg);
    }
    if (item == null) {
      throw new IllegalArgumentException("Tried adding null item");
    }
    order.addItem(item, count);
  }

  /**
   * Delegate method to remove one unit of given item.

   * @param item the item to remove
   * @throws IllegalStateException if not currently in order mode
   * @throws IllegalArgumentException if item is null or count is < 1
   */
  public void removeItem(MenuItem item, int count) {
    if (order == null) {
      String msg = "Tried removing item " + item + " when order = null";
      throw new IllegalStateException(msg);
    }
    if (item == null) {
      throw new IllegalArgumentException("Tried removing null item");
    }
    order.removeItem(item, count);
  }

  public Order getOrder() {
    return order;
  }
}