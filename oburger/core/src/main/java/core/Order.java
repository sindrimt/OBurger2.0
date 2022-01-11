package core;

import java.util.HashMap;

/**
 * Order stores the user's chosen items.
 * This class is constantly modified, until finish() is called,
 * after which no further modifications is allowed.
 */
public class Order {
  private static final int COUNTLIMIT = 999;
  private HashMap<MenuItem, Integer> items = new HashMap<>();
  private boolean finished;

  /**
   * Adds one unit of the given item.
   * Quantity may not exceed COUNTLIMIT

   * @param item which item to add?
   * @throws IllegalStateException if order is already finished
   */
  public void addItem(MenuItem item, int count) {
    if (finished) {
      throw new IllegalStateException("Tried adding item to a finished order");
    }
    if (count < 1) {
      throw new IllegalArgumentException("Tried adding "+count+" of "+item.getName()+", which is less than the minimum 1");
    }
    int newCount = getCount(item) + count;
    if (newCount <= COUNTLIMIT) {
      items.put(item, newCount);
    }
  }

  /**
   * Removes one unit of the given item.
   * Removes the item completely if new quantity is 0.

   * @param item which item to remove?
   * @throws IllegalStateException if the order is finished already
   */
  public void removeItem(MenuItem item, int count) {
    if (finished) {
      throw new IllegalStateException("Tried removing item from a finished order");
    }
    if (count < 1) {
      throw new IllegalArgumentException("Tried removing "+count+" of "+item.getName()+", which is less than the minimum 1");
    }
    int newCount = getCount(item) - count;
    if (newCount <= 0) {  // This item should be removed completely from the order
      items.remove(item);
    }
    if (newCount > 0) {
      items.put(item, newCount);
    }
  }

  public HashMap<MenuItem, Integer> getItems() {
    HashMap<MenuItem, Integer> copy = new HashMap<>();
    copy.putAll(items);
    return copy;
  }

  /**
   * Get the ordered quantity of a given item.

   * @param item quantity of which item?
   * @return ordered quantity of the item (0 if not found)
   */
  public int getCount(MenuItem item) {
    if (items.containsKey(item)) {
      return items.get(item);
    }
    return 0;
  }

  /**
   * Finishes the order.

   * @throws IllegalStateException if the order is already finished.
   */
  public void finish() {
    if (finished) {
      throw new IllegalStateException("Tried finishing an already finished order");
    }
    finished = true;
  }

  public boolean isFinished() {
    return finished;
  }
}
