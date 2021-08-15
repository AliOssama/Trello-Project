package sprint1;

public class WrongInputException extends Exception
{
	private static final long serialVersionUID = 1327932183537259958L;

	/**
	 * 
	 */
	public WrongInputException()
	{
		
		super("Your username or password is incorrect.");
	}

	/**
	 * @param message
	 */
	public WrongInputException(String message)
	{
		
		super(message);
	}

	 

}
