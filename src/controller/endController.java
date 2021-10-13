package controller;



import javafx.fxml.FXML;
import javafx.stage.Stage;
import javafx.scene.control.Label;

public class endController extends Controller {
	

	@FXML
	Label lb;
	String msg;
	
	public endController(Stage s, String msg) {
		super(s);
		this.msg = msg;
	}
	
	@FXML
    public void initialize() {
		lb.setText(msg);
	}
    
}
