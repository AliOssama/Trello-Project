package sprint1;

import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.Serializable;

public class User implements Serializable
{
	private static final long serialVersionUID = -76898514749491620L;
	String username;
	String password;
	
	public User(String username, String password)
	{
		this.username=username;
		this.password=password;
	}
	
	//Default Constructor
	public User()
	{
		this("username", "password");	
	}
	
	/**
	 * @return the username
	 */
	public String getUsername()
	{
		return username;
	}

	/**
	 * @param username the username to set
	 */
	public void setUsername(String username)
	{
		this.username = username;
	}

	/**
	 * @return the password
	 */
	public String getPassword()
	{
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password)
	{
		this.password = password;
	}

	public boolean login(String username, String password) throws 
	WrongInputException
	{
		if (this.username.equals(username) && 
				(this.password.equals(password)))
		{
			return true;
		}
		else 
		{
			throw new WrongInputException();
		}
		
	}

	public void storeToDisk()
	{
		XMLEncoder encoder=null;
		try{
		encoder=new XMLEncoder(new BufferedOutputStream(
				new FileOutputStream("user.xml")));
		}catch(FileNotFoundException fileNotFound){
			System.out.println("ERROR: While Creating or Opening the File dvd.xml");
		}
		encoder.writeObject(this);
		encoder.close();
	}
	public static User loadFromDisk()
	{
		XMLDecoder decoder=null;
		try {
			decoder=new XMLDecoder(new BufferedInputStream(new 
					FileInputStream("user.xml")));
		} catch (FileNotFoundException e) {
			System.out.println("ERROR: File dvd.xml not found");
		}
		User u= (User) decoder.readObject();
		return u; 
	}
	
	public boolean equals(User that)
	{
		if(!this.username.equals(that.username))
			{
				return false;
			};
		 if (!this.password.equals(that.password))
			{
				return false;
			}
		 return true; 
	}
	

}
