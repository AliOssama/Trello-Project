package views;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Random;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import sprint2.ViewTransitionModel;

public class ChooseHostController
{
	ViewTransitionModel model;
	
	public void setModel(ViewTransitionModel newModel)
	{
		model=newModel;
	}
	
	@FXML
	private TextField serverNameLabel;

	private String getServerName()
    {
    	String val= serverNameLabel.textProperty().get();
    	serverNameLabel.textProperty().set("");
    	return val;
    }
	@FXML
	void onClickNext(ActionEvent event) {
		String val = getServerName();
		if(!val.equals(null))
		{
		try
		{
			Random rand= new Random();
		    int rnumber= rand.nextInt(8000)+ 1000;
		    Registry registry= LocateRegistry.createRegistry(rnumber) ;
			registry.rebind(val, model.client.ser);
			model.showLogin();
			try
			{
				registry.unbind(val);
			} catch (NotBoundException e)
			{
				e.printStackTrace();
			}
		} catch (RemoteException e)
		{
			e.printStackTrace();
		}}
		else
		{
			try
			{
				Registry registry= LocateRegistry.createRegistry(1000);
				registry.rebind(getServerName(),model.client.ser);
				model.showLogin();
			} catch (RemoteException e)
			{
				e.printStackTrace();
			}
		}
		
	}
	

}
