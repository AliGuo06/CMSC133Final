# CMSC133Final
The Final Project of Zhihui Gup

Project overview:
This project is a small Java program I wrote to help keep track of items in my dorm room. The idea is simple: I want to know what items I have, how many of each item I own, and whether something is running low. I also added a basic borrow and return feature so I can record when a friend borrows something and mark it as returned later. The program reads initial inventory data from a text file and then manages everything in memory while it runs.


Program structure and classes:
The program is centered around the InventoryManager class, which acts as the main controller and also contains the main method. InventoryManager keeps two ArrayLists: one for Item objects and one for LoanRecord objects. It is responsible for adding and removing items, borrowing and returning items, printing inventory information, and checking for low-stock items. InventoryManager implements the Borrowable interface, which defines the borrow and return methods. The Item class represents a single inventory item. Each item has an automatically generated id, a name, a category, a quantity, and a low-stock threshold. It includes constructors, getters and setters, and a toString method for easy printing. The ElectronicItem class extends Item and adds a brand field. This subclass exists mainly to demonstrate inheritance and overrides toString so the brand information is included. The LoanRecord class represents one borrow event. It stores a loan id, the id of the item being borrowed, the name of the borrower, the loan date, and whether the item has been returned. InventoryManager uses LoanRecord objects to keep track of all borrow and return actions. The Borrowable interface defines two methods: borrow and returnItem. InventoryManager implements this interface so that borrowing and returning logic is clearly separated and easy to test.


File input and recursion:
When the program starts, InventoryManager attempts to read inventory data from a file named items.txt using Scanner. Each line of the file is formatted as name, category, quantity, and threshold. If the file is not found, the program prints a message but continues running. A small recursive method, reversePrintItems, is included to print the inventory list in reverse order. 


How to run:
To run the program, use java InventoryManager. When the program runs, it loads items from items.txt, prints the inventory, demonstrates the recursive reverse printing, and adds a sample ElectronicItem.


Testing:
The project includes a JUnit test class called InventoryManagerTest. They check that items can be added and removed, that borrowing fails when an item has no stock, that borrowing and returning updates quantities correctly, and that returning the same loan twice is not allowed.


Debugging summary:
While working on the project, I fixed two main bugs. The first was a NullPointerException caused by forgetting to initialize the ArrayLists in the InventoryManager constructor. I found this by reading the exception stack trace and noticing that the lists were being used before they were created. The fix was to properly initialize both lists in the constructor. The second bug was a logic error where items could still be borrowed even when their quantity was zero, which caused negative inventory counts. I discovered this when a test for borrowing with zero quantity failed and also by printing values during execution. The fix was to add an if check in the borrow method to make sure the quantity is greater than zero before allowing a borrow and creating a LoanRecord.


Tools and techniques used:
I mainly used print debugging and JUnit tests to identify and fix issues. Writing small tests helped confirm that fixes worked correctly.

Tests:
  @Test
    public void testAddAndRemoveItem() {
        Item item = new Item("Book", "Stationery", 2, 1);
        assertTrue("Should add item successfully", manager.addItem(item));
        assertEquals("Should retrieve the added item by ID", item, manager.getItemById(item.getId()));
        assertTrue("Should remove item successfully", manager.removeItem(item.getId()));
        assertNull("After removal, item should no longer exist", manager.getItemById(item.getId()));
    }

    @Test
    public void testBorrowInsufficientQuantity() {
        Item item = new Item("Pen", "Stationery", 0, 1);
        manager.addItem(item);
        LoanRecord record = manager.borrow(item.getId(), "Alice", "2023-11-01");
        assertNull("Borrow should fail when quantity is insufficient", record);
    }

    @Test
    public void testBorrowAndReturnItem() {
        Item item = new Item("Laptop", "Electronics", 1, 1);
        manager.addItem(item);
        LoanRecord record = manager.borrow(item.getId(), "Bob", "2023-11-02");
        assertNotNull("Borrow should succeed when quantity is available", record);
        assertEquals("Quantity should decrease after borrow", 0, item.getQuantity());
        boolean returned = manager.returnItem(record.getLoanId());
        assertTrue("Return should succeed for borrowed item", returned);
        assertEquals("Quantity should restore after return", 1, item.getQuantity());
    }

    @Test
    public void testReturnAlreadyReturned() {
        Item item = new Item("Tablet", "Electronics", 1, 1);
        manager.addItem(item);
        LoanRecord record = manager.borrow(item.getId(), "Carol", "2023-11-03");
        assertNotNull(record);
        boolean firstReturn = manager.returnItem(record.getLoanId());
        assertTrue("First return should succeed", firstReturn);
        boolean secondReturn = manager.returnItem(record.getLoanId());
        assertFalse("Second return should fail because item is already returned", secondReturn);
    }
