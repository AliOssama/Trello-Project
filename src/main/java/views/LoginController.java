package views;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import sprint1.User;
import sprint2.ViewTransitionModel;

public class LoginController {

	ViewTransitionModel model;
	
	public void setModel(ViewTransitionModel newModel)
	{
		model=newModel;
	}
    @FXML
    private TextField passwordLabel;

    @FXML
    private TextField usernameLabel;

    private String getusername()
    {
    	String val= usernameLabel.textProperty().get();
		usernameLabel.textProperty().set("");
    	return val;
    }
    private String getpassword()
    {
    	String val= passwordLabel.textProperty().get();
		passwordLabel.textProperty().set("");
    	return val;
    }
    @FXML
    void onClickLogin(ActionEvent event) {
    	String name= getusername();
    	String password = getpassword();
    	User user = new User(name, password);
    	if (model.client.verifyLogin(user))
    	{
    		model.showBoards(user);
    	}
    	else {
    	System.out.println("Wrong Combination");
    	}
    }
    @FXML
    void onClickGuestSignIn(ActionEvent event)
    {
    	model.vclient.verifyLogin(model.vclient.temp);
    	String name= model.vclient.temp.getUsername();
    	String password = model.vclient.temp.getPassword();
    	User temp= new User(name, password);
    	model.showBoards(temp);	
    }
}