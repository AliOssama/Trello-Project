package views;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import sprint1.Board;
import sprint1.User;
import sprint2.ViewTransitionModel;

public class CreateListController
{
	ViewTransitionModel model;
	Board board;
	User user;
	
	public void setModel(ViewTransitionModel newModel,
			Board newBoard, User newUser)
	{
		model=newModel;
		board=newBoard;
		user=newUser;
	}
	
    @FXML
    private TextField newListNameLabel;


	private String getnewName()
	{
	    String val= newListNameLabel.textProperty().get();
	    System.out.println(newListNameLabel);
	    return val;
	}
	
	@FXML
	void newNameSubmit(ActionEvent event) {
		String val = getnewName();
		model.client.createList(board.boardName, val);
		model.showBoard(board, user);
	    }

}
