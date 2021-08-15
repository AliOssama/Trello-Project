package mainModel;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import sprint2.Client;
import sprint2.ViewTransitionModel;
import sprint2.VisitorClient;
import views.ChooseHostController;

public class Main extends Application
{

	@Override
	public void start(Stage stage) throws Exception
	{
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource
				("../views/ChooseHostView.fxml"));
		BorderPane view = loader.load();
		ChooseHostController cont= loader.getController();

		
		Client client = new Client();
		VisitorClient vClient = new VisitorClient();

		ViewTransitionModel vm=new 
				ViewTransitionModel(view, client, vClient);
		cont.setModel(vm);
		
		Scene s = new Scene(view);
		stage.setScene(s);
		stage.show();
	}
	
	public static void main(String [] args)
	{
		launch(args);
	}

}
