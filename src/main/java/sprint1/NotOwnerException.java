package sprint1;
public class NotOwnerException extends Exception
{
	private static final long serialVersionUID = -26703827423735233L;


	public NotOwnerException()
	{
		super("You are not the owner.");
	}

	/**
	 * @param message
	 */
	public NotOwnerException(String message)
	{
		super(message);
	}

	

}
