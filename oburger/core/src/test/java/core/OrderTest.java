package core;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class OrderTest {
  private MenuItem testMenuItem;
  private Order order;

  @BeforeEach
  public void prepare() {
    testMenuItem = new MenuItem("a", 30, "test");
    order = new Order();
  }

  @Test
  public void testSimpleOrder() {
    order.addItem(testMenuItem, 1);
    Assertions.assertEquals(1, order.getCount(testMenuItem));
    order.addItem(testMenuItem, 4);
    Assertions.assertEquals(5, order.getCount(testMenuItem));
    order.removeItem(testMenuItem, 2);
    Assertions.assertEquals(3, order.getCount(testMenuItem));
  }

  @Test
  public void testFinishRestriction() {
    order.finish();
    Assertions.assertThrows(IllegalStateException.class, () -> order.addItem(testMenuItem, 1));
    Assertions.assertThrows(IllegalStateException.class, () -> order.removeItem(testMenuItem, 1));
    Assertions.assertThrows(IllegalStateException.class, () -> order.finish());
  }

  @Test
  public void testRemovePastZero() {
    order.addItem(testMenuItem, 1);
    order.removeItem(testMenuItem, 1);
    Assertions.assertEquals(0, order.getCount(testMenuItem));
    order.removeItem(testMenuItem, 1);
    Assertions.assertEquals(0, order.getCount(testMenuItem));
  }

  @Test
  public void testGetItems() {
    Assertions.assertTrue(order.getItems() != null);
  }

  @Test
  public void testFinish() {
    Assertions.assertFalse(order.isFinished());
    order.finish();
    Assertions.assertTrue(order.isFinished());
    Assertions.assertThrows(IllegalStateException.class, () -> {order.finish();});
  }
}
