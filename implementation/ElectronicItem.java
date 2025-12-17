package implementation;

public class ElectronicItem extends Item {
	private String brand;

	public ElectronicItem(String name, String category, int quantity,
			int threshold, String brand) {
		super(name, category, quantity, threshold);
		this.brand = brand;
	}

	public String toString() {
		return "ElectronicItem{" + "id=" + getId() + ", name='" + getName() + 
				'\'' + ", category='" + getCategory()
				+ '\'' + ", quantity=" + getQuantity() + ", threshold=" + 
				getThreshold() + ", brand='" + brand + '\''
				+ '}';
	}
}
