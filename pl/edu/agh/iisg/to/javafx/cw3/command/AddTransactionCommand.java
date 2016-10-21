package pl.edu.agh.iisg.to.javafx.cw3.command;

import pl.edu.agh.iisg.to.javafx.cw3.model.Account;
import pl.edu.agh.iisg.to.javafx.cw3.model.Transaction;

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
		
	}
	
	@Override
	public void redo() {
		
	}
	
	@Override
	public void execute() {
		account.addTransaction(transactionToAdd);
	}
}
