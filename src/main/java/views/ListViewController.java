package views;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import sprint1.Board;
import sprint1.Card;
import sprint1.List;
import sprint1.User;
import sprint2.ViewTransitionModel;

public class ListViewController
{
	ViewTransitionModel model;
	Board board;
	User user;
	int index;
	List list;
	public void setModel(ViewTransitionModel newModel, 
			Board newBoard, User newUser, int newIndex)
	{
		model=newModel;
		board=newBoard;
		user=newUser;
		index=newIndex;
		list = board.lists.get(index);
    	listName.setText(list.listName);
		setData();
		
	}
	  @FXML
	    private Label listName;

	    @FXML
	    private VBox cardsvbox;

	    @FXML
	    void help(ActionEvent event) {
	    	model.showHelp(user);
	    }

	    @FXML
	    void logout(ActionEvent event) {
	    	model.showChooseServer();
	    }

	    @FXML
	    void save(ActionEvent event) {
	    	model.client.save();
	    }
	    @FXML
	    void addCard(ActionEvent event) {
	    	model.showCreateCard(board, board.lists.get(index), user);
	    }



	    @FXML
	    void editList(ActionEvent event) {
	    	model.showEditList(board, user, board.lists.get(index));
	    }

	    @FXML
	    void moveLeft(ActionEvent event) {
	    	if (board.lists.size()!=1 && index!=0)
	    	{
		    	model.client.moveList(board.boardName, index, 
		    			index-1);
	    	}
	    	model.showBoard(board, user);
	    }

	    @FXML
	    void moveRight(ActionEvent event) {
	    	if (board.lists.size()!=1 )
	    	{
		    	model.client.moveList(board.boardName, index+1,
		    			index);
	    	}
	    	model.showBoard(board, user);
	    }

	    @FXML
	    void removeList(ActionEvent event) {
	    	board.lists.remove(index);
	    	index--;
	    	model.showBoard(board, user);
	    }
	    public void setData()
		{
			int cardIndex=0;
			for (@SuppressWarnings("unused") Card c: list.cards)
			{
				FXMLLoader loader = new FXMLLoader();
				loader.setLocation(ViewTransitionModel.class
						.getResource("../views/CardView.fxml"));
				try
				{
					BorderPane view= loader.load();
					CardViewController cont= loader.getController();
					cont.setModel(model, board, list, cardIndex);
					cardsvbox.getChildren().add(view);
				} catch (IOException e)
				{
					e.printStackTrace();
				}
				cardIndex++;
			}

}}
