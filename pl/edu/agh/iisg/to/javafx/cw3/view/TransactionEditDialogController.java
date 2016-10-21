package pl.edu.agh.iisg.to.javafx.cw3.view;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import javafx.util.converter.LocalDateStringConverter;
import pl.edu.agh.iisg.to.javafx.cw3.model.Category;
import pl.edu.agh.iisg.to.javafx.cw3.model.Transaction;

public class TransactionEditDialogController {

	private Transaction transaction;

	@FXML
	private TextField dateTextField;

	@FXML
	private TextField payeeTextField;

	@FXML
	private TextField categoryTextField;

	@FXML
	private TextField inflowTextField;

	private Stage dialogStage;
	
	private boolean approved;

	private LocalDateStringConverter converter;

	@FXML
	public void initialize() {
		String pattern = "yyyy-MM-dd";
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
		converter = new LocalDateStringConverter(formatter, formatter);

	}

	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}

	public void setData(Transaction transaction) {
		this.transaction = transaction;
		updateControls();
	}

	public boolean isApproved() {
		return approved;
	}
	
	@FXML
	private void handleOkAction(ActionEvent event) {
		if (isInputValid()) {
			updateModel();
			approved = true;
			dialogStage.close();
		}
	}

	@FXML
	private void handleCancelAction(ActionEvent event) {
		dialogStage.close();
	}

	private boolean isInputValid() {
String errorMsg="";
		
		
		if(dateTextField.getText() == null || dateTextField.getText().length() == 0) {
			errorMsg+="Invalid date.\n";
		}
		else {
			try {
				final DateTimeFormatter DATE_FORMATTER = 
			            DateTimeFormatter.ofPattern("yyyy-MM-dd");
				DATE_FORMATTER.parse(dateTextField.getText(), LocalDate::from);
			}
			catch(DateTimeParseException e) {
				errorMsg+="Invalid date. Use the yyyy-MM-dd format!\n";
			}
		}
		if(payeeTextField.getText() == null || payeeTextField.getText().length() == 0) {
			errorMsg+="Invalid payee value!\n";
		}
		if(categoryTextField.getText() == null || categoryTextField.getText().length() == 0) {
			errorMsg+="Invalid category!\n";
		}
		if(inflowTextField.getText() == null || inflowTextField.getText().length() == 0) {
			errorMsg+="Invalid inflow value!\n";
		}
		else {
			try {
				new BigDecimal(inflowTextField.getText());;
			}
			catch(NumberFormatException e)//ParseException e)
			{
				errorMsg+="Invalid inflow value! Must be BigDecimal";
			}
		}
		
		if(errorMsg.length() == 0) {
			return true;			
		}
		else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.initOwner(dialogStage);
			alert.setTitle("Invalid fields");
            alert.setHeaderText("Please correct invalid fields");
            alert.setContentText(errorMsg);

            alert.showAndWait();
            return false;
		}
	}

	private void updateModel() {
		transaction.setDate(converter.fromString(dateTextField.getText()));
		transaction.setPayee(payeeTextField.getText());
		transaction.setCategory(new Category(categoryTextField.getText()));
		DecimalFormat decimalFormatter = new DecimalFormat();
		decimalFormatter.setParseBigDecimal(true);
		try {
			transaction.setInflow((BigDecimal) decimalFormatter.parse(inflowTextField.getText()));
		} catch (ParseException e) {
			e.printStackTrace();
		}

	}

	private void updateControls() {
		dateTextField.setText(converter.toString(transaction.getDate()));
		payeeTextField.setText(transaction.getPayee());
		categoryTextField.setText(transaction.getCategory().getName());
		inflowTextField.setText(transaction.getInflow().toString());
	}
}
