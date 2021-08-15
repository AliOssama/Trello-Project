package views;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import sprint1.Board;
import sprint1.List;
import sprint1.User;
import sprint2.ViewTransitionModel;

public class EditListController
{
	ViewTransitionModel model;
	Board board;
	User user;
	List list;
	
	public void setModel(ViewTransitionModel newModel, Board newBoard,
			User newUser, List newList)
	{
		model=newModel;
		board=newBoard;
		list=newList;
		user=newUser;
	}
    @FXML
    private TextField newListNameLabel;
	
	private String getNewListName()
    {
    	String val= newListNameLabel.textProperty().get();
		newListNameLabel.textProperty().set("");
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
    void submitNewListName(ActionEvent event) {
    	String val= getNewListName();
    	model.client.editListName(board.boardName, 
    			list.listName, val);
    	model.showBoard(board, user);
    }

}
