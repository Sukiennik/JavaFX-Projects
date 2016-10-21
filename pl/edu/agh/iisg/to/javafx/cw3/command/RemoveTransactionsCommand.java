package pl.edu.agh.iisg.to.javafx.cw3.command;


import java.util.List;
import pl.edu.agh.iisg.to.javafx.cw3.model.Account;
import pl.edu.agh.iisg.to.javafx.cw3.model.Transaction;

public class RemoveTransactionsCommand implements Command {

	private List<Transaction> transactionsToRemove;
	private Account account;
	
	public RemoveTransactionsCommand(List<Transaction> transactionsToRemove, Account account) {
		this.account = account;
		this.transactionsToRemove = transactionsToRemove;
	}
	
	@Override
	public String getName() {
		return transactionsToRemove.size() + " transaction/s removed";
	}
	
	@Override
	public void undo() {
		for (Transaction transaction : transactionsToRemove) {
			account.addTransaction(transaction);
		}

	}

	@Override
	public void redo() {
		for (Transaction transaction : transactionsToRemove) {
			account.removeTransaction(transaction);
		}

	}

	@Override
	public void execute() {
		for (Transaction transaction : transactionsToRemove) {
			account.removeTransaction(transaction);
		}
	}

}
