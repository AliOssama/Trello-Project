package views;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import sprint1.Board;
import sprint1.List;
import sprint1.User;
import sprint2.ViewTransitionModel;
public class BoardViewController
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
		setData();
		displayBoardName.setText(board.boardName);
	}
	 @FXML
	 private HBox listsBox;

	 @FXML
	 private Label displayBoardName;

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
	    	if (user.getUsername().equals("tempUserName"))
	    	{
	    		model.showVisitorSave(user);
	    	}
	    	else {
	    	model.client.save();
	    	}
	    }
	 @FXML
	 void addList(ActionEvent event) {
		 model.showCreateList(board, user);
	 }

	 @FXML
	 void goToBoards(ActionEvent event) {
		 model.showBoards(user);
	 }

	 @FXML
	    void goToEditBoard(ActionEvent event) {
		 	model.showEditBoard(board, user);
	    }
	 @FXML
	    void removeB(ActionEvent event) {
		 	model.client.removeboard(board);
	    }
	public void setData()
	{
		int index=0;
		for (List l: board.lists)
		{
			index=board.lists.indexOf(l);
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(ViewTransitionModel.class
					.getResource("../views/ListView.fxml"));
			try
			{
				ScrollPane view= loader.load();
				ListViewController cont= loader.getController();
				cont.setModel(model, board, user, index);
				listsBox.getChildren().add(view);
			} catch (IOException e)
			{
				e.printStackTrace();
			}
		}
	}

	
	
	

}
