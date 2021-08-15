package views;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import sprint1.Board;
import sprint1.User;
import sprint2.ViewTransitionModel;

public class visitorSaveController
{
	ViewTransitionModel model;
	User user;
	Board board;
	
	public void setModel(ViewTransitionModel newModel, User newUser)
	{
		model=newModel;
		user=newUser;
	}
	
    @FXML
    void goBack(ActionEvent event) {
    	model.showBoards(user);
    }
	
	

	

}
