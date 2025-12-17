package implementation;

import java.util.*;
import java.io.*;

public class InventoryManager implements Borrowable {
	private ArrayList<Item> items;
	private ArrayList<LoanRecord> loanRecords;

	public InventoryManager() {
		items = new ArrayList<>();
		loanRecords = new ArrayList<>();
		loadItemsFromFile("items.txt");
	}

	// format "name,category,quantity,threshold"
	private void loadItemsFromFile(String filename) {
		try {
			Scanner scanner = new Scanner(new File(filename));
			while (scanner.hasNextLine()) {
				String line = scanner.nextLine();
				String[] parts = line.split(",");
				if (parts.length >= 4) {
					String name = parts[0].trim();
					String category = parts[1].trim();
					int quantity = Integer.parseInt(parts[2].trim());
					int threshold = Integer.parseInt(parts[3].trim());
					items.add(new Item(name, category, quantity, threshold));
				}
			}
			scanner.close();
		} catch (FileNotFoundException e) {
			System.out.println("Inventory file not found: " + filename);
		}
	}

	public void printInventory() {
		System.out.println("Current Inventory:");
		for (Item item : items) {
			System.out.println(item);
		}
	}

	public Item getItemById(int id) {
		for (Item item : items) {
			if (item.getId() == id) {
				return item;
			}
		}
		return null;
	}

	public boolean addItem(Item item) {
		return items.add(item);
	}

	public boolean removeItem(int id) {
		Iterator<Item> iter = items.iterator();
		while (iter.hasNext()) {
			Item item = iter.next();
			if (item.getId() == id) {
				iter.remove();
				return true;
			}
		}
		return false;
	}

	public LoanRecord borrow(int itemId, String borrower, String loanDate) {
		Item item = getItemById(itemId);
		if (item == null) {
			System.out.println("Item ID not found: " + itemId);
			return null;
		}
		if (item.getQuantity() <= 0) {
			System.out.println("Insufficient quantity for item: " +
		item.getName());
			return null;
		}
		item.setQuantity(item.getQuantity() - 1);
		LoanRecord record = new LoanRecord(itemId, borrower, loanDate);
		loanRecords.add(record);
		return record;
	}

	public boolean returnItem(int loanId) {
		for (LoanRecord record : loanRecords) {
			if (record.getLoanId() == loanId) {
				if (record.isReturned()) {
					System.out.println("Item already returned for loan ID: " +
				loanId);
					return false;
				}
				record.setReturned(true);
				Item item = getItemById(record.getItemId());
				if (item != null) {
					item.setQuantity(item.getQuantity() + 1);
				}
				return true;
			}
		}
		System.out.println("Loan record not found for ID: " + loanId);
		return false;
	}

	public void printLoanRecords() {
		System.out.println("Loan Records:");
		for (LoanRecord record : loanRecords) {
			System.out.println(record);
		}
	}

	public void getItemsBelowThreshold() {
		System.out.println("Items below threshold:");
		for (Item item : items) {
			if (item.getQuantity() <= item.getThreshold()) {
				System.out.println(item);
			}
		}
	}

	public void reversePrintItems() {
		System.out.println("Reverse Inventory List:");
		reversePrintHelper(items.size() - 1);
	}

	private void reversePrintHelper(int index) {
		if (index < 0) {
			return;
		}
		System.out.println(items.get(index));
		reversePrintHelper(index - 1);
	}

	public static void main(String[] args) {
		InventoryManager manager = new InventoryManager();
		manager.printInventory();
		manager.reversePrintItems();
		System.out.println("Adding an ElectronicItem:");
		ElectronicItem elec = new ElectronicItem("Laptop",
				"Electronics", 2, 1, "Dell");
		manager.addItem(elec);
		manager.printInventory();
	}
}
