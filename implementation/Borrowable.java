package implementation;

public interface Borrowable {

	LoanRecord borrow(int itemId, String borrower, String loanDate);

	boolean returnItem(int loanId);
}
