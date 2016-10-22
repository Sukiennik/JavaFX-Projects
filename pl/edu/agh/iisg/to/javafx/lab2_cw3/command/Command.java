package pl.edu.agh.iisg.to.javafx.lab2_cw3.command;

public interface Command {

	void execute();

	void undo();

	void redo();

	String getName();
}
