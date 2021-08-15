package views;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import sprint1.Board;
import sprint1.List;
import sprint1.User;
import sprint2.ViewTransitionModel;

public class CreateCardController
{
	ViewTransitionModel model;
	Board board;
	List list;
	User user;
	
	public void setModel(ViewTransitionModel newModel, 
			Board newBoard, List newList, User newUser)
	{
		model=newModel;
		board=newBoard;
		list=newList;
		user=newUser;
	}

    @FXML
    private TextField cardNameLabel;

    @FXML
    private TextField labelLabel;

    private String getCardName()
    {
    	String val= cardNameLabel.textProperty().get();
    	cardNameLabel.textProperty().set("");
    	return val;
    }
    
    private String getLabel()
    {
    	String val= labelLabel.textProperty().get();
    	labelLabel.textProperty().set("");
    	return val;
    }
    @FXML
    void newCardSubmit(ActionEvent event) {
    	String val1=getCardName();
    	String val2=getLabel();
    	model.client.createCard(board.boardName, list.listName, val1);
    	if (val2!=null)
    	{
    		model.client.addLabel(board.boardName, list.listName,
    				val1, val2);
    	}
    	model.showBoard(board, user);
    }

}
