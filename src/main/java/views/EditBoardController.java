package views;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import sprint1.Board;
import sprint1.User;
import sprint2.ViewTransitionModel;

public class EditBoardController
{
	ViewTransitionModel model;
	Board board;
	User user;
	
	public void setModel(ViewTransitionModel newModel, Board newBoard,
			User newUser)
	{
		model=newModel;
		board=newBoard;
		user=newUser;
	}

    @FXML
    private TextField newBoardNameLabel;

    private String getNewBoardName()
    {
    	String val= newBoardNameLabel.textProperty().get();
    	newBoardNameLabel.textProperty().set("");
    	return val;
    }
    @FXML
    void goToBoards(ActionEvent event) {
    	model.showBoards(user);
    }
    @FXML
    void goToHelp(ActionEvent event) {
    	model.showHelp(user);
    }
    @FXML
    void submitNewName(ActionEvent event) {
    	String val= getNewBoardName();
    	model.client.editBoardName(board.boardName, val);
    	model.showBoards(user);
    }
	

}
