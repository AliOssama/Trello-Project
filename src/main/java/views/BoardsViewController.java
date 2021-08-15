package views;

import javafx.scene.control.Button;
import java.util.ArrayList;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.layout.HBox;
import sprint1.Board;
import sprint1.User;
import sprint2.ViewTransitionModel;

public class BoardsViewController
{
	ViewTransitionModel model;
	User user;
	Board board;
	
	public void setModel(ViewTransitionModel newModel, User newUser)
	{
		model=newModel;
		user=newUser;
		setData();
	}
	@FXML
	private HBox boardsOwned;

	@FXML
	private HBox boardsMember;
	
	  @FXML
	    void aboutPage(ActionEvent event) {
		  model.showHelp(user);
	    }

	    @FXML
	    void logoutPage(ActionEvent event) {
	    	model.showChooseServer();
	    }

	    @FXML
	    void saveChanges(ActionEvent event) {
	    	if (user.getUsername().equals("tempUserName"))
	    	{
	    		model.showVisitorSave(user);
	    	}
	    	else {
	    	model.client.save();
	    	}
	    }

	@FXML
	void goToCreateBoard(ActionEvent event) {
		if(user.getUsername().equals("tempUserName"))
		{
			model.showCreateBoardGuest(user);
		}
		else {
			model.showCreateBoard(user);
		}
	}
	public void setData()
	{
		ArrayList<Board> members = model.client.br.isMember(user);
		for (Board board:members)
		{
			Button b=new Button(board.boardName);
			b.setId("boardID"+board.boardName);
			b.setOnAction(new EventHandler<ActionEvent>()
			{
				@Override
				public void handle(ActionEvent e)
				{
					model.showBoard(board, user);
				}
				
			});
			boardsMember.getChildren().addAll(b);
					
		}
		ArrayList<Board> owned= model.client.br.isowner(user);
		for (Board board:owned)
		{
			Button b= new Button(board.boardName);
			b.setId("boardID"+board.boardName);
			b.setOnAction(new EventHandler<ActionEvent>()
			{
				@Override
				public void handle(ActionEvent e)
				{
					model.showBoard(board, user);
				}
				
			});
			boardsOwned.getChildren().addAll(b);
		}
		
	}


}
