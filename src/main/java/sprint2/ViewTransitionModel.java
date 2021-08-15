package sprint2;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import sprint1.Board;
import sprint1.Card;
import sprint1.List;
import sprint1.User;
import views.BoardViewController;
import views.BoardsViewController;
import views.ChooseHostController;
import views.CreateBoardController;
import views.CreateBoardGuestController;
import views.CreateCardController;
import views.CreateListController;
import views.EditBoardController;
import views.EditCardController;
import views.EditListController;
import views.HelpViewController;
import views.LoginController;
import views.visitorSaveController;

public class ViewTransitionModel implements ViewTransitionModelInterface
{
	public Client client;
	public VisitorClient vclient;
	BorderPane mainview;
	public boolean visitor=false;
	public ViewTransitionModel(BorderPane view, 
			Client newClient, VisitorClient newvclient) 
	{
		mainview = view;
		client=newClient;
		vclient=newvclient;
	}
	
	@Override
	public void showChooseServer()
	{
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(ViewTransitionModel.class
				.getResource("../views/ChooseHostView.fxml"));
		try
		{
			Pane view = loader.load();
			mainview.setCenter(view);	
			ChooseHostController cont=loader.getController();
			cont.setModel(this);
		} catch (IOException e)
		{
			e.printStackTrace();
		}
		
		
	}
	@Override
	public void showBoards(User user)
	{
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(ViewTransitionModel.class
				.getResource("../views/BoardsView.fxml"));
		try
		{
			Pane view = loader.load();
			mainview.setCenter(view);	
			BoardsViewController cont=loader.getController();
			cont.setModel(this, user);
		} catch (IOException e)
		{
			e.printStackTrace();
		}
		

	}
	@Override
	public void showBoard(Board board, User user)
	{
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(ViewTransitionModel.class
				.getResource("../views/BoardView.fxml"));
		try
		{
			Pane view = loader.load();
			mainview.setCenter(view);
			BoardViewController cont=loader.getController();
			cont.setModel(this, board, user);
		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	public void showLogin()
	{
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(ViewTransitionModel.class
				.getResource("../views/LoginView.fxml"));
		try
		{
			Pane view = loader.load();
			mainview.setCenter(view);
			LoginController cont=loader.getController();
			cont.setModel(this);
		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	public void showHelp(User user)
	{
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(ViewTransitionModel.class
				.getResource("../views/HelpView.fxml"));
		try
		{
			Pane view = loader.load();
			mainview.setCenter(view);
			HelpViewController cont=loader.getController();
			cont.setModel(this, user);
		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	public void showEditList(Board board, User user, List list)
	{
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(ViewTransitionModel.class
				.getResource("../views/EditListView.fxml"));
		try
		{
			Pane view = loader.load();
			mainview.setCenter(view);
			EditListController cont=loader.getController();
			cont.setModel(this, board, user, list);
		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	public void showEditCard(Board board, User user, List list, 
			Card card)
	{
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(ViewTransitionModel.class
				.getResource("../views/EditCardView.fxml"));
		try
		{
			Pane view = loader.load();
			mainview.setCenter(view);
			EditCardController cont=loader.getController();
			cont.setModel(this, board, user, list, card);
		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	public void showEditBoard(Board board, User user)
	{
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(ViewTransitionModel.class
				.getResource("../views/EditBoardView.fxml"));
		try
		{
			Pane view = loader.load();
			mainview.setCenter(view);
			EditBoardController cont=loader.getController();
			cont.setModel(this, board, user);
		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	public void showCreateList(Board board, User user)
	{
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(ViewTransitionModel.class
				.getResource("../views/CreateListView.fxml"));
		try
		{
			Pane view = loader.load();
			mainview.setCenter(view);
			CreateListController cont=loader.getController();
			cont.setModel(this, board, user);
		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	public void showCreateCard(Board board, List list, User user){
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(ViewTransitionModel.class
				.getResource("../views/CreateCardView.fxml"));
		try
		{
			Pane view = loader.load();
			mainview.setCenter(view);
			CreateCardController cont=loader.getController();
			cont.setModel(this, board, list, user);
		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
	public void showCreateBoard(User user)
	{
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(ViewTransitionModel.class
				.getResource("../views/CreateBoardView.fxml"));
		try
		{
			Pane view = loader.load();
			mainview.setCenter(view);
			CreateBoardController cont=loader.getController();
			cont.setModel(this, user);
		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	public void showVisitorSave(User user)
	{
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(ViewTransitionModel.class
				.getResource("../views/visitorSave.fxml"));
		try
		{
			Pane view = loader.load();
			mainview.setCenter(view);
			visitorSaveController cont=loader.getController();
			cont.setModel(this, user);
		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	public void showCreateBoardGuest(User user)
	{
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(ViewTransitionModel.class
				.getResource("../views/CreateBoardGuestView.fxml"));
		try
		{
			Pane view = loader.load();
			mainview.setCenter(view);
			CreateBoardGuestController cont=loader.getController();
			cont.setModel(this, user);
		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	


}
