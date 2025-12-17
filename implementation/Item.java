package implementation;

public class Item {
	private static int nextId = 1;
	private int id;
	private String name;
	private String category;
	private int quantity;
	private int threshold;

	public Item(String name, String category, int quantity, int threshold) {
	        this.id = nextId++;
	        this.name = name;
	        this.category = category;
	        this.quantity = quantity;
	        this.threshold = threshold;
	    }

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getCategory() {
		return category;
	}

	public int getQuantity() {
		return quantity;
	}

	public int getThreshold() {
		return threshold;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public void setThreshold(int threshold) {
		this.threshold = threshold;
	}

	public String toString() {
		return "Item{" + "id=" + id + ", name='" + name + '\'' +
				", category='" + category + '\'' + ", quantity="
				+ quantity + ", threshold=" + threshold + '}';
	}
}
