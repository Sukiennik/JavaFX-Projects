package pl.edu.agh.iisg.to.javafx.lab2_cw3.command;

import pl.edu.agh.iisg.to.javafx.lab2_cw3.model.Account;
import pl.edu.agh.iisg.to.javafx.lab2_cw3.model.Transaction;

public class AddTransactionCommand implements Command {

	private Transaction transactionToAdd;
	private Account account;
	
	public AddTransactionCommand(Transaction transactionToAdd, Account account) {
		this.transactionToAdd = transactionToAdd;
		this.account = account;
	}
	
	@Override
	public String getName() {
		return "New transaction: " + transactionToAdd.toString();
	}
	
	@Override
	public void undo() {
		account.removeTransaction(transactionToAdd);
	}
	
	@Override
	public void redo() {
		account.addTransaction(transactionToAdd);
	}
	
	@Override
	public void execute() {
		account.addTransaction(transactionToAdd);
	}
}
