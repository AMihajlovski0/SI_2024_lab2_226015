import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.util.Collections;

public class SILab2Test {
    @Test
    public void testAllBranches() {
        {
            RuntimeException e = assertThrows(
                    RuntimeException.class,
                    () -> SILab2.checkCart(null, 1)
            );
            assertEquals("allItems list can't be null!",e.getMessage());
        }

        {
            RuntimeException e = assertThrows(
                    RuntimeException.class,
                    () -> SILab2.checkCart(Collections.singletonList(new Item(null,null,0,0)),1)
            );
            assertEquals("No barcode!",e.getMessage());
        }

        {
            RuntimeException e = assertThrows(
                    RuntimeException.class,
                    () -> SILab2.checkCart(Collections.singletonList(new Item("A","a",0,0)),1)
            );
            assertEquals("Invalid character in item barcode!",e.getMessage());
        }

        assertTrue(SILab2.checkCart(Collections.singletonList(new Item("A", "0", 0, 0)), 1));
        assertFalse(SILab2.checkCart(Collections.singletonList(new Item("A", "0", 301, 1)), 1));
    }

    @Test
    public void testMultipleConditions() {
        assertFalse(SILab2.checkCart(Collections.singletonList(new Item("A", "1", 300, 0)), 250));
        assertFalse(SILab2.checkCart(Collections.singletonList(new Item("A", "1", 301, 0)), 250));
        assertFalse(SILab2.checkCart(Collections.singletonList(new Item("A", "1", 301, 0.9f)), 250));
        assertTrue(SILab2.checkCart(Collections.singletonList(new Item("A", "0", 301, 0.9f)), 250));
    }
}
