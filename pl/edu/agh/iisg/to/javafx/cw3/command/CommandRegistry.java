package pl.edu.agh.iisg.to.javafx.cw3.command;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class CommandRegistry {

	private ObservableList<Command> commandStack = FXCollections
			.observableArrayList();
	
	private ObservableList<Command> undoCommandStack = FXCollections
			.observableArrayList();

	public void executeCommand(Command command) {
		command.execute();
		commandStack.add(command);
		undoCommandStack.clear();
	}

	public void redo() {
		if(undoCommandStack.isEmpty()) return;
		
		Command lastCommand = undoCommandStack.get(undoCommandStack.size()-1);
		undoCommandStack.remove(undoCommandStack.size()-1);
		commandStack.add(lastCommand);
		lastCommand.redo();
	}

	public void undo() {
		if(commandStack.isEmpty()) return;
		
		Command lastCommand = commandStack.get(commandStack.size()-1);	
		commandStack.remove(commandStack.size()-1);
		undoCommandStack.add(lastCommand);
		lastCommand.undo();
	}

	public ObservableList<Command> getCommandStack() {
		return commandStack;
	}

	public ObservableList<Command> getUndoCommandStack() {
		return undoCommandStack;
	}
}
