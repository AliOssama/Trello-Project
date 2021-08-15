package views;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import sprint1.User;
import sprint2.ViewTransitionModel;

public class HelpViewController
{
	ViewTransitionModel model;
	User user;
	
	public void setModel(ViewTransitionModel newModel, User newUser)
	{
		model=newModel;
		user=newUser;
	}
	
    @FXML
    void goToBoardsPage(ActionEvent event) {
    	model.showBoards(user);
    }
    @FXML
    void logOut(ActionEvent event) {
    	model.showChooseServer();
    }
	

}
