package tests;

import implementation.InventoryManager;
import implementation.Item;
import implementation.LoanRecord;

import static org.junit.Assert.*;
import org.junit.Test;

public class InventoryManagerTest {

    private InventoryManager manager;

    @Test
    public void testAddAndRemoveItem() {
        Item item = new Item("Book", "Stationery", 2, 1);

        assertTrue(manager.addItem(item));
        assertEquals(item, manager.getItemById(item.getId()));

        assertTrue(manager.removeItem(item.getId()));
        assertNull(manager.getItemById(item.getId()));
    }

    @Test
    public void testBorrowInsufficientQuantity() {
        Item item = new Item("Pen", "Stationery", 0, 1);
        manager.addItem(item);

        LoanRecord record = manager.borrow(item.getId(), "Alice", "2023-11-01");

        assertNull(record);
    }

    @Test
    public void testBorrowAndReturnItem() {
        Item item = new Item("Laptop", "Electronics", 1, 1);
        manager.addItem(item);

        LoanRecord record = manager.borrow(item.getId(), "Bob", "2023-11-02");
        assertNotNull(record);
        assertEquals(0, item.getQuantity());

        boolean returned = manager.returnItem(record.getLoanId());
        assertTrue(returned);
        assertEquals(1, item.getQuantity());
    }

    @Test
    public void testReturnAlreadyReturned() {
        Item item = new Item("Tablet", "Electronics", 1, 1);
        manager.addItem(item);

        LoanRecord record = manager.borrow(item.getId(), "Carol", "2023-11-03");
        assertNotNull(record);

        assertTrue(manager.returnItem(record.getLoanId()));
        assertFalse(manager.returnItem(record.getLoanId()));
    }
}
