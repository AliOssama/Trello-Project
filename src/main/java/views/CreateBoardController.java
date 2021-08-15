package views;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import sprint1.User;
import sprint2.ViewTransitionModel;

public class CreateBoardController
{
	ViewTransitionModel model;
	User user;
	
	public void setModel(ViewTransitionModel newModel, User newUser)
	{
		user=newUser;
		model=newModel;
	}
	@FXML
	private TextField boardName;
	@FXML
	private TextField ownerUsername;
	@FXML
	private TextField ownerPassword;

	private String getBoardName()
    {
    	String val= boardName.textProperty().get();
    	boardName.textProperty().set("");
    	return val;
    }
	private String getUsername()
    {
    	String val= ownerUsername.textProperty().get();
    	ownerUsername.textProperty().set("");
    	return val;
    }
	private String getPassword()
    {
    	String val= ownerPassword.textProperty().get();
    	ownerPassword.textProperty().set("");
    	return val;
    }
    @FXML
    void goToHelp(ActionEvent event) {
    	model.showHelp(user);
    }
	
	@FXML
	void backToBoards(ActionEvent event) {
		
		model.showBoards(user);
	}

	//Setup
	@FXML
	void newBoardSubmit(ActionEvent event) {
		String val1= getBoardName();
		String val2= getUsername();
		String val3= getPassword();
		User user= new User(val2, val3);
		model.client.createBoard(val1, user);
		model.showBoards(user);
	}
	
}
