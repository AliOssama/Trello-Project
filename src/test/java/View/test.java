package View;

import java.io.IOException;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import mainModel.Main;
import sprint1.Board;
import sprint1.Card;
import sprint1.List;
import sprint2.Client;
import sprint2.ViewTransitionModel;
import views.ChooseHostController;

@ExtendWith(ApplicationExtension.class)
public class test
{
	//Buttons testing are together until saving at the 
	//end.Functional test for assertions are after that.
	//Every change/addition is tested.
	Client client = new Client();
	@Start
	private void start(Stage stage)
	{
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource
				("../views/ChooseHostView.fxml"));
		try
		{
			BorderPane view = loader.load();
			ChooseHostController cont= loader.getController();
			//here it does not matter if I pass a visitorClient or not.
			//VisitorClient is not used in this part at all
			cont.setModel(new ViewTransitionModel(view, client, null));
			
			
			Scene s = new Scene(view);
			stage.setScene(s);
			stage.show();
		
		} catch (IOException e)
		{
			e.printStackTrace();
		}
		
	}
	@Test
	public void functionalTest(FxRobot robot)
	{
			robot.clickOn("#submitButton");
			robot.clickOn("#usernameTextField");
			robot.write("anotherone");
			robot.clickOn("#passwordTextField");
			robot.write("nopass");
			robot.clickOn("#submitLogin");
			robot.clickOn("#testBoard");
			robot.clickOn("#nameTextField");
			robot.write("testing");
			robot.clickOn("#usernameTextField");
			robot.write("anotherone");
			robot.clickOn("#passTextField");
			robot.write("nopass");
			robot.clickOn("#createBoardSubmit");
			robot.clickOn("#boardIDtesting");
			robot.clickOn("#addList");
			robot.clickOn("#nameList");
			robot.write("List 1");
			robot.clickOn("#submitNewList");
			robot.clickOn("#removeList");
			robot.clickOn("#addList");
			robot.clickOn("#nameList");
			robot.write("List1");
			robot.clickOn("#submitNewList");
			robot.clickOn("#addList");
			robot.clickOn("#nameList");
			robot.write("List2");
			robot.clickOn("#submitNewList");
			robot.clickOn("#goRight");
			robot.clickOn("#addCard");
			robot.clickOn("#cardName");
			robot.write("card one");
			robot.clickOn("#submitNewCard");
			robot.clickOn("#addCard");
			robot.clickOn("#cardName");
			robot.write("card2");
			robot.clickOn("#submitNewCard");
			robot.clickOn("#editCard");
			robot.clickOn("#newCardName");
			robot.write("card1");
			robot.clickOn("#confirmed");
			robot.clickOn("#moveDown");
			robot.clickOn("#moveRight");
			robot.clickOn("#addCard");
			robot.clickOn("#cardName");
			robot.write("card3");
			robot.clickOn("#submitNewCard");
			robot.clickOn("#helpb");
			robot.clickOn("#aboutb");
			robot.clickOn("#backToBoards");
			robot.clickOn("#boardIDtesting");
			robot.clickOn("#fileb");
			robot.clickOn("#saveb");
			robot.clickOn("#goToBoards");
			robot.clickOn("#filebs");
			robot.clickOn("#savebs");
			robot.clickOn("#filebs");
			robot.clickOn("#logoutbs");
			robot.clickOn("#submitButton");
			robot.clickOn("#usernameTextField");
			robot.write("anotherone");
			robot.clickOn("#passwordTextField");
			robot.write("nopass");
			robot.clickOn("#submitLogin");
			robot.clickOn("#boardIDtesting");
			
			
			for(Board b:client.br.boards)
			{
				if(b.boardName.equals("testing"))
				{
					//testing if the board created up was saved
					assertTrue(b.boardName.equals("testing"));
				}
				for (List l:b.lists)
				{
					if(l.listName.equals("List2"))
					{
						//testing if listed created up was saved
						assertTrue(l.listName.equals("List2"));
						for (Card c:l.cards)
						{
							if(c.cardName.equals("card1"))
							{
								//testing card movement
								assertEquals(0, l.cards.indexOf(c));
							}
							if(c.cardName.equals("card3"))
							{
								//testing card movement
								assertEquals(1, l.cards.indexOf(c));
							}
						}
					}

					if(l.listName.equals("List1"))
					{
						for (Card c:l.cards)
						{
							if(c.cardName.equals("card2"))
							{
								//testing card movement horizontally
								//swapping between lists
								assertEquals(0, l.cards.indexOf(c));
							}
						}
					}
				}
				
			}
			//remove board after assertions so it does not save
			//forever in the XML file
			robot.clickOn("#removeBoard");
			robot.clickOn("#goToBoards");
			robot.clickOn("#filebs");
			robot.clickOn("#savebs");
			
			
	}
	
}
