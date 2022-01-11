package jackson;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import core.MenuItem;
import core.Order;

/**
 * Receipts are finished Orders converted to a serializable format.
 */
public class Receipt {
  // After an order is finished, it is converted to a Receipt.
  // Receipts are easier to serialize/deserialize than Order, because
  // this class keeps track of Strings instead of MenuItems.
  private List<String> itemNames;
  private List<Integer> counts;

  public Receipt() {
    // Empty constructor necessary for Deserialization :)
  }

  /**
   * Creates a Receipt from a finished Order.

   * @param order the Order to represent with this Receipt
   * @throws IllegalStateException if the given Order is unfinished
   * @throws IllegalArgumentException if the given Order is null
   */
  public Receipt(Order order) {
    if (order == null) {
      throw new IllegalArgumentException("Tried making a receipt of null");
    }
    itemNames = new ArrayList<>();
    counts = new ArrayList<>();
    for (MenuItem item : order.getItems().keySet()) {
      int count = order.getCount(item);
      String name = item.getName();

      itemNames.add(name);
      counts.add(count);
    }
  }

  /**
   * Strings representing the names of items ordered.

   * @throws IllegalStateException if not properly setup
   */
  public List<String> getItemNames() {
    if (itemNames == null) {
      throw new IllegalStateException("Tried getting itemNames but it was null");
    }
    List<String> result = new ArrayList<>();
    for (String s : itemNames) {
      result.add(s);
    }
    return result;
  }

  /**
   * Integers representing quantity of the different items ordered.

   * @throws IllegalStateException if not properly setup
   */
  public List<Integer> getCounts() {
    if (counts == null) {
      throw new IllegalStateException("Tried getting counts but it was null");
    }
    List<Integer> result = new ArrayList<>();
    for (Integer n : counts) {
      result.add(n);
    }
    return result;
  }

  /**
   * Sets names property.
   * Used by perstistence (and tests)

   * @param names
   * @throws IllegalArgumentException if names is null
   */
  public void setItemNames(List<String> names) {
    if (names == null) {
      throw new IllegalArgumentException("Tried setting null item names on a Receipt");
    }
    itemNames = names;
  }

  /**
   * Sets counts property.
   * Used by perstistence (and tests)

   * @param counts
   * @throws IllegalArgumentException if counts is null
   */
  public void setCounts(List<Integer> counts) {
    if (counts == null) {
      throw new IllegalArgumentException("Tried setting null counts on a Receipt");
    }
    this.counts = counts;
  }

  @JsonIgnore
  public String getDescription() {
    StringBuffer buffer = new StringBuffer();
      for (int j = 0; j < this.getItemNames().size(); j++) {
        String name = this.getItemNames().get(j);
        int count = this.getCounts().get(j);
        buffer.append(count + "x " + name);
        if (j != this.getItemNames().size() - 1) {
          buffer.append(", ");
        }
      }
      String text = buffer.toString();
      return text;
  }

}
