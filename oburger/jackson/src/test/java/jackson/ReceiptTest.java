package jackson;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import core.MenuItem;
import core.Order;

public class ReceiptTest {
  private MenuItem testItem;
  private Order filledOrder;
  private Order emptyOrder; 
  @BeforeEach
  public void prepare() {
    testItem = new MenuItem("name", 1000, "img");
    filledOrder = new Order();
    filledOrder.addItem(testItem, 1);
    filledOrder.finish();
    emptyOrder = new Order();
    emptyOrder.finish();
  }
  @Test
  public void testEmptyConstructor() {
    new Receipt();
  }
  @Test
  public void testOrderConstructor() {
    Order a = new Order();
    Assertions.assertThrows(IllegalArgumentException.class, () -> { new Receipt(null); });
    new Receipt(a);  // Should not throw
    a.finish();
    new Receipt(a);
    Order b = new Order();
    b.addItem(testItem, 1);
    b.finish();
    new Receipt(b);
  }
  @Test
  public void testGetItemNames() {
    Receipt a = new Receipt();
    Assertions.assertThrows(IllegalStateException.class, () -> { a.getItemNames(); });
    Receipt b = new Receipt(emptyOrder);
    Assertions.assertEquals(0, b.getItemNames().size());
    Receipt c = new Receipt(filledOrder);
    Assertions.assertTrue(c.getItemNames().get(0).equals(testItem.getName()));
  }
  @Test
  public void testGetCounts() {
    Receipt a = new Receipt();
    Assertions.assertThrows(IllegalStateException.class, () -> { a.getCounts(); });
    Receipt b = new Receipt(emptyOrder);
    Assertions.assertEquals(0, b.getCounts().size());
    Receipt c = new Receipt(filledOrder);
    Assertions.assertEquals(1, c.getCounts().get(0));
  }

  @Test
  public void setItemNamesTest() {
    Receipt a = new Receipt();
    Assertions.assertThrows(IllegalArgumentException.class, () -> { a.setItemNames(null); });
    List<String> names = new ArrayList<>();
    names.add("aaa");
    a.setItemNames(names);
    Assertions.assertTrue(names.get(0).equals(a.getItemNames().get(0)));
  }
  @Test
  public void setCountsTest() {
    Receipt a = new Receipt();
    Assertions.assertThrows(IllegalArgumentException.class, () -> { a.setCounts(null); });
    List<Integer> counts = new ArrayList<>();
    counts.add(123);
    a.setCounts(counts);
    Assertions.assertTrue(counts.get(0).equals(a.getCounts().get(0)));
  }
}
