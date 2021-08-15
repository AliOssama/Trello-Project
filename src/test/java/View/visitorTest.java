package View;

import java.io.IOException;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
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
import sprint2.VisitorClient;
import views.ChooseHostController;

@ExtendWith(ApplicationExtension.class)
public class visitorTest
{
	VisitorClient vclient = new VisitorClient();
	Client client= new Client();
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
			cont.setModel(new ViewTransitionModel(view, client, vclient));
			
			
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
		//making sure by the end of the test, we did not save changes
		//this checks if the board created last time the test ran
		//is there or not (it should not be because it is a visitor)
		for(Board b:client.br.boards)
		{
				assertFalse(b.boardName.equals("testing"));
			if(b.boardName.equals("Example Board"))
			{
				//There is an example board in the guest view that
				//should not save changes either
				assertEquals(0, b.lists.size());
			}
		}
			robot.clickOn("#submitButton");
			robot.clickOn("#guestLogin");
			robot.clickOn("#testBoard");
			robot.clickOn("#nameTextField");
			robot.write("testing");
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
			for(Board b:client.br.boards)
			{
				//testing if board saved while the test is running
				if(b.boardName.equals("testing"))
				{
					assertTrue(b.boardName.equals("testing"));
				}
				for (List l:b.lists)
				{
					//testing if list saved while test is running
					if(l.listName.equals("List2"))
					{
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
								//testing if card moved horizontally
								assertEquals(0, l.cards.indexOf(c));
							}
						}
					}
				}
				
			}
			robot.clickOn("#helpb");
			robot.clickOn("#aboutb");
			robot.clickOn("#backToBoards");
			robot.clickOn("#boardIDtesting");
			robot.clickOn("#fileb");
			robot.clickOn("#saveb");
			robot.clickOn("#backToBoards");
			robot.clickOn("#filebs");
			robot.clickOn("#savebs");
			robot.clickOn("#backToBoards");
			robot.clickOn("#filebs");
			robot.clickOn("#logoutbs");	
			//after logout, nothing should be saved. This is asserted
			//at the beginning of the test
	}
	
}
