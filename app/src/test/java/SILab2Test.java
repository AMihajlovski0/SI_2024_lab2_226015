import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Collections;

public class SILab2Test {
    @Test
    public void testAllBranches() {
        {
            RuntimeException e = Assertions.assertThrows(
                    RuntimeException.class,
                    () -> SILab2.checkCart(null, 1)
            );
            Assertions.assertEquals(e.getMessage(), "allItems list can't be null!");
        }

        {
            RuntimeException e = Assertions.assertThrows(
                    RuntimeException.class,
                    () -> SILab2.checkCart(Collections.singletonList(new Item(null,null,0,0)),1)
            );
            Assertions.assertEquals(e.getMessage(), "No barcode!");
        }

        {
            RuntimeException e = Assertions.assertThrows(
                    RuntimeException.class,
                    () -> SILab2.checkCart(Collections.singletonList(new Item("A","a",0,0)),1)
            );
            Assertions.assertEquals(e.getMessage(), "Invalid character in item barcode!");
        }

        Assertions.assertTrue(SILab2.checkCart(Collections.singletonList(new Item("A", "0", 0, 0)), 1));
        Assertions.assertFalse(SILab2.checkCart(Collections.singletonList(new Item("A", "0", 301, 1)), 1));
    }

    @Test
    public void testMultipleConditions() {
        Assertions.assertFalse(SILab2.checkCart(Collections.singletonList(new Item("A", "1", 300, 0)), 250));
        Assertions.assertFalse(SILab2.checkCart(Collections.singletonList(new Item("A", "1", 301, 0)), 250));
        Assertions.assertFalse(SILab2.checkCart(Collections.singletonList(new Item("A", "1", 301, 0.9f)), 250));
        Assertions.assertTrue(SILab2.checkCart(Collections.singletonList(new Item("A", "0", 301, 0.9f)), 250));
    }
}
