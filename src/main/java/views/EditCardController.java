package views;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import sprint1.Board;
import sprint1.Card;
import sprint1.List;
import sprint1.User;
import sprint2.ViewTransitionModel;

public class EditCardController
{
	ViewTransitionModel model;
	Board board;
	User user;
	List list;
	Card card;
	public void setModel(ViewTransitionModel newModel, Board newBoard,
			User newUser, List newList, Card newCard)
	{
		model=newModel;
		board=newBoard;
		user=newUser;
		list=newList;
		card=newCard;
	}

    @FXML
    private TextField cardNameLabel;

    @FXML
    private TextField addLabelLabel;

    @FXML
    private TextField removeLabelLabel;
    @FXML
    void goToHelp(ActionEvent event) {
    	model.showHelp(user);
    }
    private String getNewName()
    {
    	if(!cardNameLabel.equals(null)) {
    	String val= cardNameLabel.textProperty().get();
		cardNameLabel.textProperty().set("");
    	return val;}
    	return null;
    }
    private String getNewLabel()
    {	
    	if(!addLabelLabel.equals(null)) {
    	String val= addLabelLabel.textProperty().get();
		addLabelLabel.textProperty().set("");
    	return val;}
    	return null;
    }
    private String removeLabel()
    {
    	if(!removeLabelLabel.equals(null)) {
    	String val= removeLabelLabel.textProperty().get();
    	removeLabelLabel.textProperty().set("");
    	model.showBoard(board, user);
    	return val;}
    	return null;
    }
    
    //set these up
    @FXML
    void onClickChangeCard(ActionEvent event) {
    	String val= getNewName();
    	String val2= getNewLabel();
    	String val3= removeLabel();
    	if (!val.equals(null)) {
    	model.client.editCardName(board.boardName, list.listName, 
    			card.cardName, val);}
    	if(!val2.equals(null)) {
    	model.client.addLabel(board.boardName, list.listName, 
    			card.cardName,val2);}
    	if(!val3.equals(null)) {
    	model.client.removeLabel(board.boardName, list.listName, 
    			card.cardName, val3);}
    	model.showBoard(board,user);
    }
	
}
