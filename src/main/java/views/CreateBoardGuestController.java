package views;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import sprint1.User;
import sprint2.ViewTransitionModel;

public class CreateBoardGuestController
{
	ViewTransitionModel model;
	User user;

	public void setModel(ViewTransitionModel newModel, User newUser)
	{
		user=newUser;
		model=newModel;
	}
    @FXML
    private TextField getBoardName;

	private String getName()
    {
    	String val= getBoardName.textProperty().get();
    	getBoardName.textProperty().set("");
    	return val;
    }
    @FXML
    void backToBoards(ActionEvent event) {
    	model.showBoards(user);
    }

    @FXML
    void goToHelp(ActionEvent event) {
    	model.showHelp(user);
    }

    @FXML
    void newBoardSubmit(ActionEvent event) {
    	String val1=getName(); 
    	String val2= model.vclient.temp.getUsername();
		String val3= model.vclient.temp.getPassword();
		User user= new User(val2, val3);
		model.client.createBoard(val1, user);
		model.showBoards(user);
    }

}
