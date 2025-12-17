package implementation;

public class LoanRecord {
	private static int nextId = 1;
	private int loanId;
	private int itemId;
	private String borrower;
	private String loanDate;
	private boolean returned;

	public LoanRecord(int itemId, String borrower, String loanDate) {
		this.loanId = nextId++;
		this.itemId = itemId;
		this.borrower = borrower;
		this.loanDate = loanDate;
		this.returned = false;
	}

	public int getLoanId() {
		return loanId;
	}

	public int getItemId() {
		return itemId;
	}

	public String getBorrower() {
		return borrower;
	}

	public String getLoanDate() {
		return loanDate;
	}

	public boolean isReturned() {
		return returned;
	}

	public void setReturned(boolean returned) {
		this.returned = returned;
	}

	public String toString() {
		return "LoanRecord{" + "loanId=" + loanId + ", itemId=" +
	itemId + ", borrower='" + borrower + '\''
				+ ", loanDate='" + loanDate + '\'' + ", returned=" +
	returned + '}';
	}
}
