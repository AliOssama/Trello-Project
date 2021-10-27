package views;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import sprint1.Board;
import sprint1.Card;
import sprint1.List;
import sprint2.ViewTransitionModel;

public class CardViewController
{
	ViewTransitionModel model;
	Board board;
	List list;
	int index;
	public void setModel(ViewTransitionModel newModel, 
			Board newBoard, List newList, int newIndex)
	{
		model=newModel;
		board=newBoard;
		index=newIndex;	
		list=newList;
		setData();
	}
	@FXML
    private Label cardNameLabel;

    @FXML
    void removeCard(ActionEvent event) {
    	if (model.client.user == null) {
    		model.client.removeCard(board.boardName,list.listName,index);
    		model.showBoard(board, model.vclient.user);
    	}
    	else {
    		model.client.removeCard(board.boardName, list.listName, 
    			index);
    		model.showBoard(board, model.client.user);
    	}
    }
    @FXML
    void editCard(ActionEvent event) {
    	model.showEditCard(board, board.getOwner(), list, 
    			list.cards.get(index));
    }

    @FXML
    void moveDown(ActionEvent event) {
    	if(list.cards.size()!=1) {
    	model.client.moveCard(board.boardName, list.listName,
    			index+1,index);}
    	if(board.getOwner().getUsername().equals("tempUserName"))
    	{
    		model.showBoard(board, model.vclient.temp);
    	}
    	else {
    		model.showBoard(board, model.client.user);
    	}
    }

    @FXML
    void moveLeft(ActionEvent event) {
    	int newspot=0;
    	int listIndex= board.lists.indexOf(list);
    	if(listIndex!=0) {
    	model.client.swapCard(board.boardName, 
    			board.lists.get(listIndex).listName, index,
    			board.lists.get(listIndex-1).listName,
    			newspot);
    	}
    	if(board.getOwner().getUsername().equals("tempUserName"))
    	{
    		model.showBoard(board, model.vclient.temp);
    	}
    	else {
    		model.showBoard(board, model.client.user);
    	}
    }

    @FXML
    void moveRight(ActionEvent event) {
    	int newspot=0;
    	int listIndex= board.lists.indexOf(list);
    	if(listIndex!=board.lists.size()-1) {
    	model.client.swapCard(board.boardName, 
    			board.lists.get(listIndex).listName, index,
    			board.lists.get(listIndex+1).listName, newspot);}

    	if(board.getOwner().getUsername().equals("tempUserName"))
    	{
    		model.showBoard(board, model.vclient.temp);
    	}
    	else {
    		model.showBoard(board, model.client.user);
    	}
    }

    @FXML
    void moveUp(ActionEvent event) {
    	if(list.cards.size()!=1 &&index!=0) {
    	model.client.moveCard(board.boardName, list.listName, index,
    			index-1);
    	}
    	if(board.getOwner().getUsername().equals("tempUserName"))
    	{
    		model.showBoard(board, model.vclient.temp);
    	}
    	else {
    		model.showBoard(board, model.client.user);
    	}
    }
    public void setData()
	{
    	Card card = list.cards.get(index);
    	cardNameLabel.setText(card.cardName);
    	
	}
}
