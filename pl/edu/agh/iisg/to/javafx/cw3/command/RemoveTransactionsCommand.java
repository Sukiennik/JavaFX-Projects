package pl.edu.agh.iisg.to.javafx.cw3.command;

import javafx.scene.control.TableView;
import pl.edu.agh.iisg.to.javafx.cw3.model.Account;
import pl.edu.agh.iisg.to.javafx.cw3.model.Transaction;

public class RemoveTransactionsCommand implements Command {

	private TableView<Transaction> transactionsTable;
	private Transaction transactionToRemove;
	private Account account;
	
	public RemoveTransactionsCommand(Transaction transactionToRemove, TableView<Transaction> transactionsTable, Account account) {
		this.account = account;
		this.transactionToRemove = transactionToRemove;
		this.transactionsTable = transactionsTable;
	}
	
	@Override
	public String getName() {
		final int presentSize = transactionsTable.getSelectionModel().getSelectedItems().size();
		return presentSize + "transaction/s removed";
	}
	
	@Override
	public void undo() {
		// TODO Auto-generated method stub

	}

	@Override
	public void redo() {
		// TODO Auto-generated method stub

	}

	@Override
	public void execute() {
		account.removeTransaction(transactionToRemove);

	}

}
